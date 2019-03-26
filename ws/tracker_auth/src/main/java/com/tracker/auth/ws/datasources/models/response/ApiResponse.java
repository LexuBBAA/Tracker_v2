package com.tracker.auth.ws.datasources.models.response;

import com.tracker.auth.ws.datasources.models.enums.HttpResponseCode;
import com.tracker.auth.ws.datasources.models.enums.HttpResponseMessage;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.Column;

public abstract class ApiResponse<T> {
    @NonNull
    private long timestamp;
    @NonNull
    private int responseCode;
    @NonNull
    private String responseMessage;
    @Nullable
    private T payload;

    ApiResponse() {
        this(HttpResponseCode.HTTP_RESPONSE_OK);
    }

    ApiResponse(@NonNull HttpResponseCode httpResponseCode) {
        this(
                httpResponseCode.getValue(),
                HttpResponseMessage.get(httpResponseCode).getValue()
        );
    }

    ApiResponse(@NonNull T payload) {
        this(HttpResponseCode.HTTP_RESPONSE_OK, payload);
    }

    ApiResponse(@NonNull HttpResponseCode httpResponseCode, @NonNull T payload) {
        this(
                httpResponseCode.getValue(),
                HttpResponseMessage.get(httpResponseCode).getValue()
        );
        this.payload = payload;
    }

    ApiResponse(int responseCode, @NonNull String responseMessage) {
        this.timestamp = System.currentTimeMillis();
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.payload = null;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getResponseCode() {
        return responseCode;
    }

    @NonNull
    public String getResponseMessage() {
        return responseMessage;
    }

    @Nullable
    public T getPayload() {
        return payload;
    }

    void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    void setResponseMessage(@NonNull String responseMessage) {
        this.responseMessage = responseMessage;
    }

    void setPayload(@Nullable T payload) {
        this.payload = payload;
    }
}
