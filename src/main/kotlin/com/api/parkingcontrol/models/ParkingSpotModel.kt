package com.api.parkingcontrol.models

import org.hibernate.Hibernate
import java.io.Serializable
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Size

/*
Created by Dhionatã on 2/9/2022
*/

@Entity
data class ParkingSpotModel(
    val serialVersionUID: Long = 1L,

    @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: UUID? = null,

    @Column(nullable = false, unique = true, length = 10) var parkingSpotNumber: String? = null,

    @Column(nullable = false, unique = true, length = 7) @Size(max = 7) var licensePlateCar: String? = null,

    @Column(nullable = false, length = 70) var brandCar: String? = null,

    @Column(nullable = false, length = 70) var modelCar: String? = null,

    @Column(nullable = false, length = 70) var colorCar: String? = null,

    @Column(nullable = false) val registrationDate: LocalDateTime = LocalDateTime.now(ZoneId.of("UTC")),

    @Column(nullable = false, length = 130) var responsibleName: String? = null,

    @Column(nullable = false, length = 30) var apartment: String? = null,

    @Column(nullable = false, length = 30) var block: String? = null
) : Serializable {

    override fun toString(): String {
        return "\nid: $id\nParking Spot Number: $parkingSpotNumber\nLicense Plate Car: $licensePlateCar\nBrand Car: $brandCar\nModel Car: $modelCar\nColor Car: $colorCar" +
                "\nRegistrationDate: $registrationDate\nReponsible Name: $responsibleName\nApartment: $apartment\nBlock: $block"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as ParkingSpotModel

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()
}