package com.tracker.users.ws.utils;

import org.springframework.http.HttpStatus;

public enum HttpResponseCode {

    HTTP_RESPONSE_OK(200, HttpStatus.OK),
    HTTP_RESPONSE_CREATED(201, HttpStatus.CREATED),
    HTTP_RESPONSE_NO_CONTENT(204, HttpStatus.NO_CONTENT),
    HTTP_RESPONSE_REDIRECT(304, HttpStatus.NOT_MODIFIED),
    HTTP_RESPONSE_BAD_REQUEST(400, HttpStatus.BAD_REQUEST),
    HTTP_RESPONSE_UNAUTHORIZED(401, HttpStatus.UNAUTHORIZED),
    HTTP_RESPONSE_FORBIDDEN(403, HttpStatus.FORBIDDEN),
    HTTP_RESPONSE_UNSUPPORTED_MEDIA_TYPE(415, HttpStatus.UNSUPPORTED_MEDIA_TYPE),
    HTTP_RESPONSE_INTERNAL_SERVER_ERROR(500, HttpStatus.INTERNAL_SERVER_ERROR);

    private int value;
    private HttpStatus httpStatus;

    HttpResponseCode(int value, HttpStatus httpStatus) {
        this.value = value;
        this.httpStatus = httpStatus;
    }

    public int getValue() {
        return this.value;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    public static HttpResponseCode get(int code) {
        for(HttpResponseCode responseCode: values()) {
            if(responseCode.getValue() == code) {
                return responseCode;
            }
        }

        return HTTP_RESPONSE_INTERNAL_SERVER_ERROR;
    }
}
