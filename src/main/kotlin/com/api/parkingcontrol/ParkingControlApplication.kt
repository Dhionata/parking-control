package com.api.parkingcontrol

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
class ParkingControlApplication {
    @GetMapping("/")
    fun index(): String {
        return "Olá mundo!"
    }
}

fun main(args: Array<String>) {
    runApplication<ParkingControlApplication>(*args)
}
