package com.example.apiSamolet.repositories

import com.example.apiSamolet.models.House
import com.example.apiSamolet.models.Types
import org.springframework.data.r2dbc.repository.Modifying
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface HouseRepository : ReactiveCrudRepository<House, Long> {
    @Modifying
    @Query("update house set image = :image " +
            "where id = :id")
    fun updateImage(id: Int?, image: String?): Mono<Int>

    @Modifying
    @Query("delete from house " +
            "where id = :id")
    fun delete(id: Int): Mono<Int>

    @Query(
        value = "select id, date, land_area, area, price, url, " +
                "technology, status, article, type, completion_date, land_price, image " +
                "from house " +
                "where id = :id " +
                "order by date desc " +
                "limit 1"
    )
    fun getHouse(id: Int): Mono<House>

    @Query(
        value = "select distinct on(id) id, date, land_area, area, price, url, " +
                "technology, status, article, type, completion_date, land_price, image " +
                "from house " +
                "where type like :type " +
                "and article like :article " +
                "and ( technology like :technology " +
                "    or technology is null )" +
                //"and price between :priceLow and :priceHigh " +
                "order by id, date desc"
    )
    fun getHouses(type: String, article: String,
                  technology: String): Flux<House>

    @Query(
        value = "select distinct type " +
                "from house " +
                "where article like :article " +
                "order by type"
    )
    fun getTypes(article: String): Flux<Types>

    @Query(
        value = "select id, date, land_area, area, price, url, " +
                "technology, status, article, type, completion_date, land_price, image " +
                "from house " +
                "where id = :id " +
                "order by date"
    )
    fun getHouseHistory(id: Int): Flux<House>
}