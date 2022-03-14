package com.api.parkingcontrol.services

import com.api.parkingcontrol.models.ParkingSpotModel
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Pageable
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

    @BeforeEach
    @Test
    fun save() {
        parkingSpotService.save(parkingSpotModel)
        findById()
    }

    @Test
    fun existByParkingSpotNumber() {
        assertTrue(parkingSpotService.existByParkingSpotNumber(parkingSpotModel.parkingSpotNumber!!))
    }

    @Test
    fun existsByLicensePlateCar() {
        assertTrue(parkingSpotService.existsByLicensePlateCar(parkingSpotModel.licensePlateCar!!))
    }

    @Test
    fun existByApartmentAndBlock() {
        assertTrue(parkingSpotService.existByApartmentAndBlock(parkingSpotModel.apartment!!, parkingSpotModel.block!!))
    }

    @Test
    fun findAll() {
        assertEquals(1, parkingSpotService.findAll(Pageable.unpaged()).size)
    }

    @Test
    fun findById() {
        assertEquals(parkingSpotModel, parkingSpotService.findById(parkingSpotModel.id!!).get())
    }

    @Test
    fun deleteById() {
        parkingSpotService.deleteById(parkingSpotModel.id!!)
        assertTrue(parkingSpotService.findById(parkingSpotModel.id!!).isEmpty)
    }
}