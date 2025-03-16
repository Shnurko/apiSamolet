package com.example.apiSamolet.controllers

import com.example.apiSamolet.models.House
import com.example.apiSamolet.models.Users
import com.example.apiSamolet.repositories.HouseRepository
import com.example.apiSamolet.repositories.UsersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@RestController
@RequestMapping("/samolet")
class Controller(
    @Autowired
    private val rep: HouseRepository,
    private val userRep: UsersRepository
    //private val service: Service
) {
    @GetMapping("/{id}")
    fun getHouse(@PathVariable("id") id: Int): Mono<House> {
        return rep.getHouse(id)
    }

    @GetMapping
    fun getHouses(
        @RequestParam(required = false, defaultValue = "%") type: String,
        @RequestParam(required = false, defaultValue = "%") article: String,
        @RequestParam(required = false, defaultValue = "%") technology: String
    ): Flux<House> {
        return rep.getHouses(type, article, technology)
    }

    @GetMapping("/types")//, produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getTypes(@RequestParam(required = false, defaultValue = "%") article: String): Flux<House> {
        return rep.getTypes(article)
    }

    @GetMapping("/{id}/history")
    fun getHouseHistory(@PathVariable("id") id: Int): Flux<House> {
        return rep.getHouseHistory(id)
    }

    @PostMapping("/user")
    fun saveUser(@RequestBody user: Users): Mono<Users> {
        return userRep.save(user)
    }
}