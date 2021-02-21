package com.dyckster.data.source.api.service

import com.dyckster.data.source.api.response.ChartResponseModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface BitcoinService {

    @GET
    fun getChart(
        @Url url: String,
        @Query("timespan") timespan: String
    ): Single<ChartResponseModel>
}