package cn.tuyucheng.taketoday.rxjava.operators;

import org.junit.jupiter.api.Test;
import rx.Observable;
import rx.observables.MathObservable;
import rx.observers.TestSubscriber;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class RxMathematicalOperatorsUnitTest {

	@Test
	void givenRangeNumericObservable_whenCalculatingAverage_ThenSuccessfull() {
		// given
		Observable<Integer> sourceObservable = Observable.range(1, 20);

		TestSubscriber<Integer> subscriber = TestSubscriber.create();

		// when
		MathObservable.averageInteger(sourceObservable)
			.subscribe(subscriber);

		// then
		subscriber.assertCompleted();
		subscriber.assertNoErrors();
		subscriber.assertValueCount(1);
		subscriber.assertValue(10);
	}

	@Test
	void givenRangeNumericObservable_whenCalculatingSum_ThenSuccessfull() {
		// given
		Observable<Integer> sourceObservable = Observable.range(1, 20);
		TestSubscriber<Integer> subscriber = TestSubscriber.create();

		// when
		MathObservable.sumInteger(sourceObservable)
			.subscribe(subscriber);

		// then
		subscriber.assertCompleted();
		subscriber.assertNoErrors();
		subscriber.assertValueCount(1);
		subscriber.assertValue(210);
	}

	@Test
	void givenRangeNumericObservable_whenCalculatingMax_ThenSuccessfullObtainingMaxValue() {
		// given
		Observable<Integer> sourceObservable = Observable.range(1, 20);
		TestSubscriber<Integer> subscriber = TestSubscriber.create();

		// when
		MathObservable.max(sourceObservable)
			.subscribe(subscriber);

		// then
		subscriber.assertCompleted();
		subscriber.assertNoErrors();
		subscriber.assertValueCount(1);
		subscriber.assertValue(20);
	}

	@Test
	void givenRangeNumericObservable_whenCalculatingMin_ThenSuccessfullObtainingMinValue() {
		// given
		Observable<Integer> sourceObservable = Observable.range(1, 20);
		TestSubscriber<Integer> subscriber = TestSubscriber.create();

		// when
		MathObservable.min(sourceObservable)
			.subscribe(subscriber);

		// then
		subscriber.assertCompleted();
		subscriber.assertNoErrors();
		subscriber.assertValueCount(1);
		subscriber.assertValue(1);
	}

	@Test
	void givenItemObservable_whenCalculatingMaxWithComparator_ThenSuccessfullObtainingMaxItem() {
		// given
		Item five = new Item(5);
		List<Item> list = Arrays.asList(new Item(1), new Item(2), new Item(3), new Item(4), five);
		Observable<Item> itemObservable = Observable.from(list);

		TestSubscriber<Item> subscriber = TestSubscriber.create();

		// when
		MathObservable.from(itemObservable)
			.max(Comparator.comparing(Item::getId))
			.subscribe(subscriber);

		// then
		subscriber.assertCompleted();
		subscriber.assertNoErrors();
		subscriber.assertValueCount(1);
		subscriber.assertValue(five);
	}

	@Test
	void givenItemObservable_whenCalculatingMinWithComparator_ThenSuccessfullObtainingMinItem() {
		// given
		Item one = new Item(1);
		List<Item> list = Arrays.asList(one, new Item(2), new Item(3), new Item(4), new Item(5));
		TestSubscriber<Item> subscriber = TestSubscriber.create();
		Observable<Item> itemObservable = Observable.from(list);

		// when
		MathObservable.from(itemObservable)
			.min(Comparator.comparing(Item::getId))
			.subscribe(subscriber);

		// then
		subscriber.assertCompleted();
		subscriber.assertNoErrors();
		subscriber.assertValueCount(1);
		subscriber.assertValue(one);
	}

	class Item {
		private Integer id;

		Item(Integer id) {
			this.id = id;
		}

		Integer getId() {
			return id;
		}
	}
}