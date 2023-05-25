package com.api.parkingcontrol.controllers

import com.api.parkingcontrol.dtos.ParkingSpotDto
import com.api.parkingcontrol.models.ParkingSpotModel
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Pageable
import org.springframework.transaction.annotation.Transactional

/*
Created by Dhionatã on 2/21/2022
*/

@SpringBootTest
@Transactional
internal class ParkingSpotControllerTest(@Autowired private val parkingSpotController: ParkingSpotController) {

    private val parkingSpotDto = ParkingSpotDto(
        parkingSpotNumber = "1",
        brandCar = "Fiat",
        modelCar = "Uno",
        colorCar = "Black",
        block = "A",
        responsibleName = "Dhionatã",
        apartment = "A",
        licensePlateCar = "ABC1234",
    )

    @BeforeEach
    @Test
    fun saveParkingSpot() {
        parkingSpotController.saveParkingSpot(parkingSpotDto)
    }

    @Test
    fun getAllParkingSpots() {
        repeat(5) {
            parkingSpotController.saveParkingSpot(
                ParkingSpotDto(
                    parkingSpotNumber = "$it",
                    apartment = "$it",
                    block = "$it",
                    brandCar = "$it",
                    colorCar = "$it",
                    modelCar = "$it",
                    licensePlateCar = "$it",
                    responsibleName = "Dhionatã"
                )
            )
        }
        parkingSpotController.getAllParkingSpots(pageable = Pageable.unpaged()).body?.content?.stream()
            ?.forEach { println(it) }
    }

    @Test
    fun getById() {
        val id = parkingSpotController.getAllParkingSpots(pageable = Pageable.unpaged()).body?.stream()
            ?.filter { it.parkingSpotNumber == parkingSpotDto.parkingSpotNumber }?.findFirst()?.get()?.id
        if (id != null) {
            val a = parkingSpotController.getById(id).body
            if (a is ParkingSpotModel) {
                assertTrue(a.parkingSpotNumber == parkingSpotDto.parkingSpotNumber)
            }
        }
    }

    @Test
    fun deleteOne() {
        val id = parkingSpotController.getAllParkingSpots(pageable = Pageable.unpaged()).body?.stream()
            ?.filter { it.parkingSpotNumber == parkingSpotDto.parkingSpotNumber }?.findFirst()?.get()?.id
        if (id != null) {
            parkingSpotController.deleteOne(id)
        }
    }

    @Test
    fun putOne() {
        val b = ParkingSpotDto(
            parkingSpotNumber = "2",
            apartment = "2",
            block = "2",
            brandCar = "2",
            colorCar = "2",
            modelCar = "2",
            licensePlateCar = "2",
            responsibleName = "Dhionatã"
        )
        val id = parkingSpotController.getAllParkingSpots(pageable = Pageable.unpaged()).body?.stream()
            ?.filter { it.parkingSpotNumber == parkingSpotDto.parkingSpotNumber }?.findFirst()?.get()?.id
        if (id != null) {
            println(parkingSpotController.putOne(id, b).toString())
        }
    }
}