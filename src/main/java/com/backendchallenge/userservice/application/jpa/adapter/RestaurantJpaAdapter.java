package com.backendchallenge.userservice.application.jpa.adapter;

import com.backendchallenge.userservice.application.feign.IFeignRestaurantClient;
import com.backendchallenge.userservice.domain.spi.IRestaurantPersistentPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestaurantJpaAdapter implements IRestaurantPersistentPort {
    private final IFeignRestaurantClient feignRestaurantClient;


    @Override
    public void createEmployee(Long userId,Long restaurantId) {
        feignRestaurantClient.createEmployee(userId,restaurantId);
    }
}
