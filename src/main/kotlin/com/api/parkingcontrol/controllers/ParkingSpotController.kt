package com.api.parkingcontrol.controllers

import com.api.parkingcontrol.dtos.ParkingSpotDto
import com.api.parkingcontrol.models.ParkingSpotModel
import com.api.parkingcontrol.services.ParkingSpotService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.time.ZoneId
import javax.validation.Valid

/*
Created by Dhionatã on 2/9/2022
*/
@RestController
@CrossOrigin(origins = ["*"], maxAge = 3600)
@RequestMapping("/parking-spot")
class ParkingSpotController(val parkingSpotService: ParkingSpotService) {

    @PostMapping
    fun saveParkingSpot(@RequestBody @Valid parkingSpotDto: ParkingSpotDto): ResponseEntity<Any> {
        if (parkingSpotService.existsByLicensePlateCar(parkingSpotDto.licensePlateCar)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: License Plate Car is already in use!")
        }
        if (parkingSpotService.existByParkingSpotNumber(parkingSpotDto.parkingSpotNumber)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Spot Number is already in use!")
        }
        if (parkingSpotService.existByApartmentAndBlock(parkingSpotDto.apartment, parkingSpotDto.block)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                "Conflict: Parking Spot already registered for " +
                        "this apartment/block!"
            )
        }

        val parkingSpotModel = ParkingSpotModel()
        parkingSpotModel.copyProperties(parkingSpotDto)
        parkingSpotModel.registrationDate = LocalDateTime.now(ZoneId.of("UTC"))
        println(parkingSpotModel.toString())
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(parkingSpotService.save(parkingSpotModel))
    }

    @GetMapping
    fun getAllParkingSpots(): ResponseEntity<List<ParkingSpotModel>> {
        val a = ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAll())
        println(a.body?.stream()?.forEach { it.show() })
        return a
    }
}
