package com.agriculture.order_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.agriculture.order_service.model.Crop;

import java.util.List;

@FeignClient(name ="CROP-SERVICE",path="/crops")
public interface CropServiceClient {

	 @GetMapping("/available")
	 List<Crop> getAvailableCrops();

	 @GetMapping("/{id}")
	 Crop getCropById(@PathVariable String id);
	 
	 @PutMapping("/{id}")
	 void updateCrop(@PathVariable String id, @RequestBody Crop crop);
}
