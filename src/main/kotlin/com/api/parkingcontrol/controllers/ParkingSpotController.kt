package com.api.parkingcontrol.controllers

import com.api.parkingcontrol.dtos.ParkingSpotDto
import com.api.parkingcontrol.models.ParkingSpotModel
import com.api.parkingcontrol.services.ParkingSpotService
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID
import javax.validation.Valid

/*
Created by Dhionatã on 2/9/2022
*/
@RestController
@CrossOrigin(origins = ["*"], maxAge = 3600)
@RequestMapping("/parking-spot")
class ParkingSpotController(@Autowired private val parkingSpotService: ParkingSpotService) {

    @PostMapping
    fun saveParkingSpot(@RequestBody @Valid parkingSpotDto: ParkingSpotDto): ResponseEntity<Any> {
        if (parkingSpotService.existsByLicensePlateCar(parkingSpotDto.licensePlateCar)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: License Plate Car is already in use!")
        }
        if (parkingSpotService.existByParkingSpotNumber(parkingSpotDto.parkingSpotNumber)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Spot Number is already in use!")
        }
        if (parkingSpotService.existByApartmentAndBlock(parkingSpotDto.apartment, parkingSpotDto.block)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot already registered for this apartment/block!")
        }

        val parkingSpotModel = ParkingSpotModel()
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel)
        println(parkingSpotModel.toString())
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel))
    }

    @GetMapping
    fun getAllParkingSpots(
        @PageableDefault(
            page = 0, size = 10, sort = ["id"], direction = Sort.Direction.ASC
        ) pageable: Pageable
    ): ResponseEntity<Page<ParkingSpotModel>> {
        //a.body?.stream()?.forEach { println(it.toString()) }
        return ResponseEntity.status(HttpStatus.OK).body(
            parkingSpotService.findAll(pageable)
        )
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable(value = "id") id: UUID): ResponseEntity<Any?> {
        val parkingSpotModelptional = ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findById(id))
        if (!parkingSpotModelptional.hasBody()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.")
        }
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotModelptional.body!!.get())
    }

    @DeleteMapping("/{id}")
    fun deletOne(@PathVariable(value = "id") id: UUID): ResponseEntity<Any?> {
        val parkingSpotModelptional = ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findById(id))
        if (!parkingSpotModelptional.hasBody()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.")
        }
        parkingSpotService.deleteById(id)
        return ResponseEntity.status(HttpStatus.OK).body("Parking Spot deleted.")
    }

    @PutMapping("/{id}")
    fun putOne(
        @PathVariable(value = "id") id: UUID, @RequestBody @Valid parkingSpotDto: ParkingSpotDto
    ): ResponseEntity<Any?> {
        return try {
            val parkingSpotModelOptional = ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findById(id))
            if (!parkingSpotModelOptional.hasBody()) {
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.")
            } else {
                val parkingSpotModel = parkingSpotModelOptional.body!!.get()
                BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel)
                println(parkingSpotModel.toString())
                parkingSpotService.save(parkingSpotModel)
                ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.save(parkingSpotModel))
            }
        } catch (e: Exception) {
            println(e.message)
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }
    }
}