package com.backendchallenge.userservice.domain.spi;

public interface IRestaurantPersistentPort {
    void createEmployee(Long userId,Long restaurantId);
}
