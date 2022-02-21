package com.api.parkingcontrol.models

import com.api.parkingcontrol.interfaces.ParkingSpot
import org.hibernate.Hibernate
import java.io.Serializable
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import javax.persistence.*

/*
Created by Dhionatã on 2/9/2022
*/
@Entity
data class ParkingSpotModel(
    val serialVersionUID: Long = 1L,

    @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: UUID? = null,

    @Column(nullable = false, unique = true, length = 10) override var parkingSpotNumber: String = "",

    @Column(nullable = false, unique = true, length = 7) override var licensePlateCar: String = "",

    @Column(nullable = false, length = 70) override var brandCar: String = "",

    @Column(nullable = false, length = 70) override var modelCar: String = "",

    @Column(nullable = false, length = 70) override var colorCar: String = "",

    @Column(nullable = false) var registrationDate: LocalDateTime = LocalDateTime.now(ZoneId.of("UTC")),

    @Column(nullable = false, length = 130) override var responsibleName: String = "",

    @Column(nullable = false, length = 30) override var apartment: String = "",

    @Column(nullable = false, length = 30) override var block: String = ""
) : Serializable, ParkingSpot {

    override fun toString(): String {
        return "\nid: $id\nParking Spot Number: $parkingSpotNumber\nLicense Plate Car: $licensePlateCar\nBrand Car: $brandCar\nModel Car: $modelCar\nColor Car: $colorCar" +
                "\nRegistrationDate: $registrationDate\nReponsible Name: $responsibleName\nApartment: $apartment\nBlock: $block"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as ParkingSpotModel

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()
}