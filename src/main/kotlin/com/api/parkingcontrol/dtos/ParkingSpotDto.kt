package com.api.parkingcontrol.dtos

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size
import java.io.Serializable

/*
Created by Dhionatã on 2/9/2022
*/
data class ParkingSpotDto(
    @field:NotBlank var block: String,

    @field:NotBlank var parkingSpotNumber: String,

    @field:NotBlank @Size(max = 7) var licensePlateCar: String,

    @field:NotBlank var brandCar: String,

    @field:NotBlank var modelCar: String,

    @field:NotBlank var colorCar: String,

    @field:NotBlank var responsibleName: String,

    @field:NotBlank var apartment: String,
) : Serializable