package com.tracker.users.ws.utils;

import org.springframework.lang.NonNull;

public enum HttpResponseMessage {

    HTTP_RESPONSE_OK("Success"),
    HTTP_RESPONSE_CREATED("Created"),
    HTTP_RESPONSE_NO_CONTENT("No content"),
    HTTP_RESPONSE_REDIRECT("Redirected"),
    HTTP_RESPONSE_BAD_REQUEST("Bad request"),
    HTTP_RESPONSE_UNAUTHORIZED("Insufficient rights"),
    HTTP_RESPONSE_FORBIDDEN("Missing mandatory headers"),
    HTTP_RESPONSE_UNSUPPORTED_MEDIA_TYPE("Could not parse request"),
    HTTP_RESPONSE_INTERNAL_SERVER_ERROR("Unknown server error");

    @NonNull
    private String value;

    HttpResponseMessage(String value) {
        this.value = value;
    }

    @NonNull
    public String getValue() {
        return this.value;
    }

    public static HttpResponseMessage get(HttpResponseCode responseCode) {
        switch (responseCode) {
            case HTTP_RESPONSE_CREATED:
                return HTTP_RESPONSE_CREATED;
            case HTTP_RESPONSE_NO_CONTENT:
                return HTTP_RESPONSE_NO_CONTENT;
            case HTTP_RESPONSE_REDIRECT:
                return HTTP_RESPONSE_REDIRECT;
            case HTTP_RESPONSE_BAD_REQUEST:
                return HTTP_RESPONSE_BAD_REQUEST;
            case HTTP_RESPONSE_UNAUTHORIZED:
                return HTTP_RESPONSE_UNAUTHORIZED;
            case HTTP_RESPONSE_FORBIDDEN:
                return HTTP_RESPONSE_FORBIDDEN;
            case HTTP_RESPONSE_UNSUPPORTED_MEDIA_TYPE:
                return HTTP_RESPONSE_UNSUPPORTED_MEDIA_TYPE;
            case HTTP_RESPONSE_INTERNAL_SERVER_ERROR:
                return HTTP_RESPONSE_INTERNAL_SERVER_ERROR;
            case HTTP_RESPONSE_OK:
            default:
                return HTTP_RESPONSE_OK;
        }
    }

}
