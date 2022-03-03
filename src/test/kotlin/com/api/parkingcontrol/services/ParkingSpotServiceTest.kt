package com.api.parkingcontrol.services

import com.api.parkingcontrol.models.ParkingSpotModel
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

/*
Created by Dhionatã on 2/25/2022
*/

@SpringBootTest
@Transactional
internal class ParkingSpotServiceTest(@Autowired private val parkingSpotService: ParkingSpotService) {

    private val parkingSpotModel = ParkingSpotModel(
        parkingSpotNumber = "1", licensePlateCar = "ABC1234", brandCar = "Fiat", modelCar = "Uno", colorCar = "Black", responsibleName = "Dhionatã", apartment = "A", block = "B"
    )

    @Test
    fun save() {
        assertEquals(parkingSpotModel, parkingSpotService.save(parkingSpotModel))
    }

    @Test
    fun existByParkingSpotNumber() {
        parkingSpotService.save(parkingSpotModel)
        assertTrue(parkingSpotService.existByParkingSpotNumber(parkingSpotModel.parkingSpotNumber ?: return))
    }

    @Test
    fun existsByLicensePlateCar() {
    }

    @Test
    fun existByApartmentAndBlock() {
    }

    @Test
    fun findAll() {

    }

    @Test
    fun findById() {
    }

    @Test
    fun deleteById() {
    }

    @Test
    fun getParkingSpotRepository() {
    }
}