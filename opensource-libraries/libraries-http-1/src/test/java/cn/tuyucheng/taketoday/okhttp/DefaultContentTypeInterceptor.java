package cn.tuyucheng.taketoday.okhttp;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class DefaultContentTypeInterceptor implements Interceptor {

	private final String contentType;

	public DefaultContentTypeInterceptor(String contentType) {
		this.contentType = contentType;
	}

	public Response intercept(Interceptor.Chain chain) throws IOException {

		Request originalRequest = chain.request();
		Request requestWithUserAgent = originalRequest.newBuilder().header("Content-Type", contentType).build();

		return chain.proceed(requestWithUserAgent);
	}
}
