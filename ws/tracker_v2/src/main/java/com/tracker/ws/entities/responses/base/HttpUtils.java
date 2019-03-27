package com.tracker.ws.entities.responses.base;

import com.tracker.ws.entities.responses.enums.HttpResponseCode;
import org.springframework.http.HttpStatus;

import static com.tracker.ws.entities.responses.enums.HttpResponseCode.get;

//  TODO: research creating a shared library with this
abstract class HttpUtils {

    static HttpStatus getHttpStatus(int responseCode) {
        HttpResponseCode httpResponseCode = get(responseCode);
        switch (httpResponseCode) {
            case HTTP_RESPONSE_OK: return HttpStatus.OK;
            case HTTP_RESPONSE_CREATED: return HttpStatus.CREATED;
            case HTTP_RESPONSE_NO_CONTENT: return HttpStatus.NO_CONTENT;
            case HTTP_RESPONSE_REDIRECT: return HttpStatus.NOT_MODIFIED;
            case HTTP_RESPONSE_BAD_REQUEST: return HttpStatus.BAD_REQUEST;
            case HTTP_RESPONSE_UNAUTHORIZED: return HttpStatus.UNAUTHORIZED;
            case HTTP_RESPONSE_FORBIDDEN: return HttpStatus.FORBIDDEN;
            case HTTP_RESPONSE_UNSUPPORTED_MEDIA_TYPE: return HttpStatus.UNSUPPORTED_MEDIA_TYPE;
            case HTTP_RESPONSE_INTERNAL_SERVER_ERROR:
            default: return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

}
