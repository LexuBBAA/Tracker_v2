package com.tracker.ws.entities.responses.base;

import com.tracker.ws.entities.responses.enums.HttpResponseCode;
import com.tracker.ws.entities.responses.enums.HttpResponseMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

//  TODO:   remove this so that all apps use the ResponseEntity<T> (base Http responses) class
public abstract class ApiResponse<T> {
    @NonNull
    private long timestamp;
    @NonNull
    private int responseCode;
    @NonNull
    private String responseMessage;
    @Nullable
    private T payload;

    public ApiResponse() {
        this(HttpResponseCode.HTTP_RESPONSE_OK);
    }

    public ApiResponse(@NonNull HttpResponseCode httpResponseCode) {
        this(
                httpResponseCode.getValue(),
                HttpResponseMessage.get(httpResponseCode).getValue()
        );
    }

    public ApiResponse(@NonNull T payload) {
        this(HttpResponseCode.HTTP_RESPONSE_OK, payload);
    }

    public ApiResponse(@NonNull HttpResponseCode httpResponseCode, @NonNull T payload) {
        this(
                httpResponseCode.getValue(),
                HttpResponseMessage.get(httpResponseCode).getValue()
        );
        this.payload = payload;
    }

    public ApiResponse(int responseCode, @NonNull String responseMessage) {
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