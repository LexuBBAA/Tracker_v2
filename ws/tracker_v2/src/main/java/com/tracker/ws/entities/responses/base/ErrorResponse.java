package com.tracker.ws.entities.responses.base;

import com.tracker.ws.entities.responses.enums.HttpResponseCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;

//  TODO: research creating a shared library with this
public class ErrorResponse extends ApiResponse<Void> {
    public ErrorResponse(@NonNull HttpResponseCode httpResponseCode) {
        super(httpResponseCode);
    }

    public ResponseEntity<String> toResponseEntity() {
        return new ResponseEntity<>(getResponseMessage(), HttpUtils.getHttpStatus(getResponseCode()));
    }
}
