package com.api.parkingcontrol.services

import com.api.parkingcontrol.models.ParkingSpotModel
import com.api.parkingcontrol.repositories.ParkingSpotRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional

/*
Created by Dhionatã on 2/9/2022
*/

@Service
class ParkingSpotService(val parkingSpotRepository: ParkingSpotRepository) {

    @Transactional
    fun save(parkingSpotModel: ParkingSpotModel): ParkingSpotModel {
        return parkingSpotRepository.save(parkingSpotModel)
    }

    fun existByParkingSpotNumber(parkingSpotNumber: String): Boolean {
        return parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber)
    }

    fun existsByLicensePlateCar(licensePlateCar: String): Boolean {
        return parkingSpotRepository.existsByLicensePlateCar(licensePlateCar)
    }

    fun existByApartmentAndBlock(apartment: String, block: String): Boolean {
        return parkingSpotRepository.existsByApartmentAndBlock(apartment, block)
    }

    fun findAll(): List<ParkingSpotModel> {
        return parkingSpotRepository.findAll()
    }

}