package com.api.parkingcontrol.repositories

import com.api.parkingcontrol.models.ParkingSpotModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

/*
Created by Dhionatã on 2/9/2022
*/

@Repository
interface ParkingSpotRepository : JpaRepository<ParkingSpotModel, UUID> {
    fun existsByParkingSpotNumber(parkingParkingSpotNumber: String): Boolean
    fun existsByLicensePlateCar(licensePlateCar: String): Boolean
    fun existsByApartmentAndBlock(apartment: String, block: String): Boolean
    fun save(parkingSpotModel: ParkingSpotModel): ParkingSpotModel
}