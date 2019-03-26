package com.tracker.auth.ws.datasources.models.response;

import com.tracker.auth.ws.datasources.models.enums.HttpResponseCode;
import org.springframework.lang.NonNull;

public class ErrorResponse extends ApiResponse<Void> {
    public ErrorResponse(@NonNull HttpResponseCode httpResponseCode) {
        super(httpResponseCode);
    }
}
