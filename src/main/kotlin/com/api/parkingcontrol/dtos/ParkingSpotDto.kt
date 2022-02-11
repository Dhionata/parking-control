package com.api.parkingcontrol.dtos

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

/*
Created by Dhionatã on 2/9/2022
*/
open class ParkingSpotDto(
    @NotBlank internal val block: String,

    @NotBlank internal val parkingSpotNumber: String,

    @NotBlank
    @Size(max = 7) internal val licensePlateCar: String,

    @NotBlank internal val brandCar: String,

    @NotBlank internal val modelCar: String,

    @NotBlank internal val colorCar: String,

    @NotBlank internal val responsibleName: String,

    @NotBlank internal val apartment: String,
)