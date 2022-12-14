package cn.tuyucheng.taketoday.backoff.jitter;

import io.github.resilience4j.retry.IntervalFunction;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.function.Function;

import static cn.tuyucheng.taketoday.backoff.jitter.BackoffWithJitterUnitTest.RetryProperties.INITIAL_INTERVAL;
import static cn.tuyucheng.taketoday.backoff.jitter.BackoffWithJitterUnitTest.RetryProperties.MAX_RETRIES;
import static cn.tuyucheng.taketoday.backoff.jitter.BackoffWithJitterUnitTest.RetryProperties.MULTIPLIER;
import static cn.tuyucheng.taketoday.backoff.jitter.BackoffWithJitterUnitTest.RetryProperties.RANDOMIZATION_FACTOR;
import static java.util.Collections.nCopies;
import static java.util.concurrent.Executors.newFixedThreadPool;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BackoffWithJitterUnitTest {

	static Logger log = LoggerFactory.getLogger(BackoffWithJitterUnitTest.class);

	interface PingPongService {
		String call(String ping) throws PingPongServiceException;
	}

	class PingPongServiceException extends RuntimeException {
		PingPongServiceException(String reason) {
			super(reason);
		}
	}

	private PingPongService service;
	private static final int NUM_CONCURRENT_CLIENTS = 8;

	@BeforeEach
	void setUp() {
		service = mock(PingPongService.class);
	}

	@Test
	void whenRetryExponentialBackoff_thenRetriedConfiguredNoOfTimes() {
		IntervalFunction intervalFn = IntervalFunction.ofExponentialBackoff(INITIAL_INTERVAL, MULTIPLIER);
		Function<String, String> pingPongFn = getRetryablePingPongFn(intervalFn);

		when(service.call(anyString())).thenThrow(PingPongServiceException.class);
		try {
			pingPongFn.apply("Hello");
		} catch (PingPongServiceException e) {
			verify(service, times(MAX_RETRIES)).call(anyString());
		}
	}

	@Test
	void whenRetryExponentialBackoffWithoutJitter_thenThunderingHerdProblemOccurs() throws InterruptedException {
		IntervalFunction intervalFn = IntervalFunction.ofExponentialBackoff(INITIAL_INTERVAL, MULTIPLIER);
		test(intervalFn);
	}

	@Test
	void whenRetryExponentialBackoffWithJitter_thenRetriesAreSpread() throws InterruptedException {
		IntervalFunction intervalFn = IntervalFunction.ofExponentialRandomBackoff(INITIAL_INTERVAL, MULTIPLIER, RANDOMIZATION_FACTOR);
		test(intervalFn);
	}

	private void test(IntervalFunction intervalFn) throws InterruptedException {
		Function<String, String> pingPongFn = getRetryablePingPongFn(intervalFn);
		ExecutorService executors = newFixedThreadPool(NUM_CONCURRENT_CLIENTS);
		List<Callable<String>> tasks = nCopies(NUM_CONCURRENT_CLIENTS, () -> pingPongFn.apply("Hello"));

		when(service.call(anyString())).thenThrow(PingPongServiceException.class);

		executors.invokeAll(tasks);
	}

	private Function<String, String> getRetryablePingPongFn(IntervalFunction intervalFn) {
		RetryConfig retryConfig = RetryConfig.custom()
			.maxAttempts(MAX_RETRIES)
			.intervalFunction(intervalFn)
			.retryExceptions(PingPongServiceException.class)
			.build();
		Retry retry = Retry.of("pingpong", retryConfig);
		return Retry.decorateFunction(retry, ping -> {
			log.info("Invoked at {}", LocalDateTime.now());
			return service.call(ping);
		});
	}

	static class RetryProperties {
		static final Long INITIAL_INTERVAL = 1000L;
		static final Double MULTIPLIER = 2.0D;
		static final Double RANDOMIZATION_FACTOR = 0.6D;
		static final Integer MAX_RETRIES = 4;
	}
}