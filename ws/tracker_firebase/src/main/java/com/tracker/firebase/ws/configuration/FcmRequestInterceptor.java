package com.tracker.firebase.ws.configuration;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.lang.NonNull;

import java.io.IOException;

class FcmRequestInterceptor implements ClientHttpRequestInterceptor {

    private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    private static final String CONTENT_TYPE_HEADER_KEY = "Content-Type";
    private static final String CONTENT_TYPE_HEADER_VALUE = "application/json";

    @NonNull
    private String apiKey;

    FcmRequestInterceptor(@NonNull String apiKey) {
        this.apiKey = apiKey;
    }

    @NonNull
    @Override
    public ClientHttpResponse intercept(@NonNull HttpRequest request, @NonNull byte[] body, @NonNull ClientHttpRequestExecution execution) throws IOException {
        HttpRequest wrapper = new HttpRequestWrapper(request);
        wrapper.getHeaders().set(AUTHORIZATION_HEADER_KEY, "key=" + this.apiKey);
        wrapper.getHeaders().set(CONTENT_TYPE_HEADER_KEY, CONTENT_TYPE_HEADER_VALUE);
        return execution.execute(wrapper, body);
    }

}
