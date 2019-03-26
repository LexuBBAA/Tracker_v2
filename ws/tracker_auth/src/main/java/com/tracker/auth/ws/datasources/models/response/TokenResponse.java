package com.tracker.auth.ws.datasources.models.response;

import com.tracker.auth.ws.datasources.models.enums.HttpResponseCode;
import com.tracker.auth.ws.datasources.models.enums.HttpResponseMessage;
import com.tracker.auth.ws.datasources.tokens.dto.TokenDto;
import org.springframework.lang.NonNull;

public class TokenResponse extends ApiResponse<TokenDto> {
    public TokenResponse(@NonNull TokenDto tokenDto) {
        super(tokenDto);

        if(tokenDto.token == null) {
            HttpResponseCode httpResponseCode = HttpResponseCode.HTTP_RESPONSE_INTERNAL_SERVER_ERROR;
            int code = httpResponseCode.getValue();
            String message = HttpResponseMessage.get(httpResponseCode).getValue();

            this.setResponseCode(code);
            this.setResponseMessage(message);
            this.setPayload(null);
        }
    }
}
