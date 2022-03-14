package com.api.parkingcontrol.dtos

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size
import java.io.Serializable

/*
Created by Dhionatã on 2/9/2022
*/

data class ParkingSpotDto(
    @field:NotBlank val block: String,

    @field:NotBlank val parkingSpotNumber: String,

    @field:NotBlank @Size(max = 7) val licensePlateCar: String,

    @field:NotBlank val brandCar: String,

    @field:NotBlank val modelCar: String,

    @field:NotBlank val colorCar: String,

    @field:NotBlank val responsibleName: String,

    @field:NotBlank val apartment: String,
) : Serializable