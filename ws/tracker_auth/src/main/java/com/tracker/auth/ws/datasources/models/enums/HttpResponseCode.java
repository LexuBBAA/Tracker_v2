package com.tracker.auth.ws.datasources.models.enums;

public enum HttpResponseCode {

    HTTP_RESPONSE_OK(200),
    HTTP_RESPONSE_CREATED(201),
    HTTP_RESPONSE_NO_CONTENT(204),
    HTTP_RESPONSE_REDIRECT(304),
    HTTP_RESPONSE_BAD_REQUEST(400),
    HTTP_RESPONSE_UNAUTHORIZED(401),
    HTTP_RESPONSE_FORBIDDEN(403),
    HTTP_RESPONSE_UNSUPPORTED_MEDIA_TYPE(415),
    HTTP_RESPONSE_INTERNAL_SERVER_ERROR(500);

    private int value;

    HttpResponseCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
