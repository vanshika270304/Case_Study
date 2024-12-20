package com.agriculture.crop_service.service;

import com.agriculture.crop_service.model.Crop;
import com.agriculture.crop_service.repository.CropRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CropService {

    @Autowired
    private CropRepository cropRepository;

    // Add a new crop
    public Crop addCrop(Crop crop) {
        return cropRepository.save(crop);
    }

    // Get all crops by a farmer
    public List<Crop> getCropsByFarmerId(String farmerId) {
        return cropRepository.findByFarmerId(farmerId);
    }
    
    //Get crop by id
    public Crop getCropById(String id) {
		// TODO Auto-generated method stub
		return cropRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Crop not found with ID: " + id));
	}

    // Get all available crops
    public List<Crop> getAvailableCrops() {
        return cropRepository.findByStatus("Available");
    }

    // Update crop details
    public Crop updateCrop(String id, Crop updatedCrop) {
        Optional<Crop> existingCropOpt = cropRepository.findById(id);

        if (existingCropOpt.isPresent()) {
            Crop existingCrop = existingCropOpt.get();
            existingCrop.setName(updatedCrop.getName());
            existingCrop.setQuantity(updatedCrop.getQuantity());
            existingCrop.setPricePerUnit(updatedCrop.getPricePerUnit());
            existingCrop.setSoilType(updatedCrop.getSoilType());
            existingCrop.setDateOfHarvest(updatedCrop.getDateOfHarvest());
            existingCrop.setStatus(updatedCrop.getStatus());

            return cropRepository.save(existingCrop);
        } else {
            throw new IllegalArgumentException("Crop not found with ID: " + id);
        }
    }

    // Delete a crop
    public void deleteCrop(String id) {
        if (cropRepository.existsById(id)) {
            cropRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Crop not found with ID: " + id);
        }
    }

	
}
