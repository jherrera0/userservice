package com.backendchallenge.userservice.infrastructure.configuration.feign;

import com.backendchallenge.userservice.domain.until.ConstTest;
import com.backendchallenge.userservice.domain.until.JwtConst;
import com.backendchallenge.userservice.domain.until.TokenHolder;
import com.backendchallenge.userservice.infrastructure.configuration.exceptionhandler.FeignExceptionHandler;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.codec.ErrorDecoder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FeignClientsConfigTest {

    private MockedStatic<TokenHolder> mockedTokenHolder;

    @BeforeEach
    void setUp() {
        mockedTokenHolder = mockStatic(TokenHolder.class);
    }

    @AfterEach
    void tearDown() {
        mockedTokenHolder.close();
    }

    @Test
    void requestInterceptor_shouldAddAuthorizationHeaderWhenTokenIsPresent() {
        mockedTokenHolder.when(TokenHolder::getToken).thenReturn(ConstTest.VALID_TOKEN);
        RequestInterceptor interceptor = FeignClientsConfig.requestInterceptor();
        RequestTemplate requestTemplate = new RequestTemplate();

        interceptor.apply(requestTemplate);

        assertTrue(requestTemplate.headers().containsKey(JwtConst.HEADER_STRING));
        assertEquals(ConstTest.VALID_TOKEN, requestTemplate.headers().get(JwtConst.HEADER_STRING).iterator().next());
    }

    @Test
    void requestInterceptor_shouldNotAddAuthorizationHeaderWhenTokenIsNull() {
        mockedTokenHolder.when(TokenHolder::getToken).thenReturn(null);
        RequestInterceptor interceptor = FeignClientsConfig.requestInterceptor();
        RequestTemplate requestTemplate = new RequestTemplate();

        interceptor.apply(requestTemplate);

        assertFalse(requestTemplate.headers().containsKey(JwtConst.HEADER_STRING));
    }

    @Test
    void requestInterceptor_shouldNotAddAuthorizationHeaderWhenTokenIsEmpty() {
        mockedTokenHolder.when(TokenHolder::getToken).thenReturn("");
        RequestInterceptor interceptor = FeignClientsConfig.requestInterceptor();
        RequestTemplate requestTemplate = new RequestTemplate();

        interceptor.apply(requestTemplate);

        assertFalse(requestTemplate.headers().containsKey(JwtConst.HEADER_STRING));
    }

    @Test
    void errorDecoder_shouldReturnFeignExceptionHandler() {
        FeignClientsConfig config = new FeignClientsConfig();
        ErrorDecoder errorDecoder = config.errorDecoder();

        assertNotNull(errorDecoder);
        assertEquals(FeignExceptionHandler.class, errorDecoder.getClass());
    }
}