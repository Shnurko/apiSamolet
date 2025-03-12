package com.example.apiSamolet.controllers

import com.example.apiSamolet.models.House
import com.example.apiSamolet.repositories.HouseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@RestController
@RequestMapping("/samolet")
class Controller(
    @Autowired
    private val rep: HouseRepository
    //private val service: Service
) {
    @GetMapping("/{id}")
    fun getHouse(@PathVariable("id") id: Int): Flux<House> {
        return rep.getHouse(id)
    }

    @GetMapping
    fun getHouses(): Flux<House> {
        return rep.getHouses(type = "%", article = "%", technology = "%")
    }
}