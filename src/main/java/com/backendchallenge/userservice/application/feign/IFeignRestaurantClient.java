package com.backendchallenge.userservice.application.feign;

import com.backendchallenge.userservice.infrastructure.configuration.feign.FeignClientsConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "restaurant-service", url = "http://localhost:8081", configuration = FeignClientsConfig.class)
public interface IFeignRestaurantClient {
    @PostMapping("/restaurant/CREATE_EMPLOYEE")
    void createEmployee(@RequestParam Long userId, @RequestParam Long restaurantId);
}
