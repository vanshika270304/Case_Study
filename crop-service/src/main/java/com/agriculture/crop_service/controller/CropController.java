package com.agriculture.crop_service.controller;

import com.agriculture.crop_service.model.Crop;
import com.agriculture.crop_service.service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crops")
@Validated
public class CropController {

    @Autowired
    private CropService cropService;

    // Add a crop
    @PostMapping("/add")
    public ResponseEntity<Crop> addCrop(@RequestBody @Validated Crop crop) {
        return ResponseEntity.ok(cropService.addCrop(crop));
    }

    // Get all crops by farmer ID
    @GetMapping("/farmer/{farmerId}")
    public ResponseEntity<List<Crop>> getCropsByFarmerId(@PathVariable String farmerId) {
        return ResponseEntity.ok(cropService.getCropsByFarmerId(farmerId));
    }

    // Get all available crops
    @GetMapping("/available")
    public ResponseEntity<List<Crop>> getAvailableCrops() {
        return ResponseEntity.ok(cropService.getAvailableCrops());
    }
    
    //Get crop by id
    @GetMapping("/{id}")
    public ResponseEntity<Crop> getCropById(@PathVariable String id) {
        return ResponseEntity.ok(cropService.getCropById(id));
    }

    // Update a crop
    @PutMapping("/{id}")
    public ResponseEntity<Crop> updateCrop(
            @PathVariable String id,
            @RequestBody @Validated Crop updatedCrop) {
        return ResponseEntity.ok(cropService.updateCrop(id, updatedCrop));
    }

    // Delete a crop
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCrop(@PathVariable String id) {
        cropService.deleteCrop(id);
        return ResponseEntity.ok("Crop deleted successfully");
    }
}

