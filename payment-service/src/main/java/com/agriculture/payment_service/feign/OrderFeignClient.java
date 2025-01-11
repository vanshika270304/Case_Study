package com.agriculture.payment_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.agriculture.payment_service.model.Order;


@FeignClient(name="ORDER-SERVICE", path="/orders")
public interface OrderFeignClient {

	@GetMapping("/{orderId}")
    Order getOrderById(@PathVariable String orderId);

    @PutMapping("/{orderId}/status")
    Order updateOrderStatus(
        @PathVariable String orderId, 
        @RequestParam String status
    );
}
