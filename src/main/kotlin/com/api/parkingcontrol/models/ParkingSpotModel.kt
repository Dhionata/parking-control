package com.api.parkingcontrol.models

import com.api.parkingcontrol.dtos.ParkingSpotDto
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

/*
Created by Dhionatã on 2/9/2022
*/
@Entity
class ParkingSpotModel(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(
        name = "id",
        nullable = false
    ) private val id: UUID = UUID.randomUUID(),

    @Column(nullable = false, unique = true, length = 10) private var parkingSpotNumber: String? = null,

    @Column(nullable = false, unique = true, length = 7) private var licensePlateCar: String? = null,

    @Column(nullable = false, length = 70) private var brandCar: String? = null,

    @Column(nullable = false, length = 70) private var modelCar: String? = null,

    @Column(nullable = false, length = 70) private var colorCar: String? = null,

    @Column(nullable = false) internal var registrationDate: LocalDateTime? = null,

    @Column(nullable = false, length = 130) private var responsibleName: String? = null,

    @Column(nullable = false, length = 30) private var apartment: String? = null,

    @Column(nullable = false, length = 30) private var block: String? = null
) : Serializable {

    fun copyProperties(parkingSpotDto: ParkingSpotDto) {
        this.parkingSpotNumber = parkingSpotDto.parkingSpotNumber
        this.licensePlateCar = parkingSpotDto.licensePlateCar
        this.brandCar = parkingSpotDto.brandCar
        this.modelCar = parkingSpotDto.modelCar
        this.colorCar = parkingSpotDto.colorCar
        this.responsibleName = parkingSpotDto.responsibleName
        this.apartment = parkingSpotDto.apartment
        this.block = parkingSpotDto.block
    }

    fun show() {
        println(
            "\nid: $id" +
                    "\nParking Spot Number: $parkingSpotNumber" +
                    "\nLicense Plate Car: $licensePlateCar" +
                    "\nBrand Car: $brandCar" +
                    "\nModel Car: $modelCar" +
                    "\nColor Car: $colorCar" +
                    "\nRegistration Date: $registrationDate" +
                    "\nReponsible Name: $responsibleName" +
                    "\nApartment: $apartment" +
                    "\nBlock: $block"
        )
    }
}