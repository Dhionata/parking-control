package com.api.parkingcontrol.dtos

import com.api.parkingcontrol.interfaces.ParkingSpot
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

/*
Created by Dhionatã on 2/9/2022
*/
data class ParkingSpotDto(
    @NotBlank override var block: String = "",

    @NotBlank override var parkingSpotNumber: String = "",

    @NotBlank
    @Size(max = 7) override var licensePlateCar: String = "",

    @NotBlank override var brandCar: String = "",

    @NotBlank override var modelCar: String = "",

    @NotBlank override var colorCar: String = "",

    @NotBlank override var responsibleName: String = "",

    @NotBlank override var apartment: String = "",
) : ParkingSpot