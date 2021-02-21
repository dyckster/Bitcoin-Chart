package com.dyckster.data.di

import com.dyckster.data.source.api.service.BitcoinService
import com.dyckster.domain.di.PerApplication
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.blockchain.info/charts/"

@Module
class NetworkProvider {

    @Provides
    @PerApplication
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Provides
    @PerApplication
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(httpLoggingInterceptor)
        return httpClient.build()
    }

    @Provides
    @PerApplication
    internal fun provideApplicationApi(
        httpClient: OkHttpClient
    ): BitcoinService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(httpClient)
            .baseUrl(BASE_URL)
            .build()
            .create(BitcoinService::class.java)
    }
}