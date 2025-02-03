package com.backendchallenge.userservice.application.jpa.adapter;

import com.backendchallenge.userservice.application.feign.IFeignRestaurantClient;
import com.backendchallenge.userservice.domain.until.ConstTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RestaurantJpaAdapterTest {

    @Mock
    private IFeignRestaurantClient feignRestaurantClient;

    @InjectMocks
    private RestaurantJpaAdapter restaurantJpaAdapter;

    @Test
    void createEmployee_withValidIds_shouldCallFeignClient() {
        restaurantJpaAdapter.createEmployee(ConstTest.USER_ID, ConstTest.RESTAURANT_ID);

        verify(feignRestaurantClient).createEmployee(ConstTest.USER_ID, ConstTest.RESTAURANT_ID);
    }

}