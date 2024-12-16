package com.agriculture.crop_service.repository;

import com.agriculture.crop_service.model.Crop;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CropRepository extends MongoRepository<Crop, String> {
    List<Crop> findByFarmerId(String farmerId);
    List<Crop> findByStatus(String status);
}
