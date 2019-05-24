package com.tracker.firebase.ws.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;

@Configuration
public class FcmInterceptorConfig {

    private static final String FCM_SERVER_KEY = "AIzaSyBRqQu-KAoRg1a_3kLbPCx2pDV6RgB6fzs";
    public static final String FCM_API_URL = "https://fcm.googleapis.com/fcm/send";
    //senderId : 215727644562

    @Bean
    public ClientHttpRequestInterceptor clientHttpRequestInterceptor() {
        return new FcmRequestInterceptor(FCM_SERVER_KEY);
    }

}
