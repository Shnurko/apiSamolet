package com.example.apiSamolet.repositories

import com.example.apiSamolet.models.Favorites
import org.springframework.data.r2dbc.repository.Modifying
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface FavoritesRepository : ReactiveCrudRepository<Favorites, Long>{
    @Modifying
    @Query(
        value = "insert into favorites " +
                "values( :userid, :id )"
    )
    fun insert(userid: Long?, id: Int?): Mono<Int>

    @Modifying
    @Query("delete from favorites " +
            "where id = :id " +
            "and userid =:userid")
    fun delete(userid: Long?, id: Int?): Mono<Int>

    @Query(
        value = "select userid, id " +
                "from favorites " +
                "where id = :id " +
                "and userid = :userid"
    )
    fun select(userid: Long?, id: Int?): Mono<Favorites>

    @Query(
        value = "select userid, id " +
                "from favorites " +
                "where userid = :userid"
    )
    fun selectall(userid: Long?): Flux<Favorites>
}