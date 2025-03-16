package com.example.apiSamolet.repositories

import com.example.apiSamolet.models.Users
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UsersRepository : ReactiveCrudRepository<Users, Long>