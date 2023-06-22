package com.adolfo.android.moviesapp.di

import android.app.Application
import androidx.room.Room
import com.adolfo.android.moviesapp.data.api.MovieService
import com.adolfo.android.moviesapp.data.api.MovieService.Companion.API_KEY
import com.adolfo.android.moviesapp.data.api.MovieService.Companion.BASE_URL
import com.adolfo.android.moviesapp.data.local.cart.MovieCartDatabase
import com.adolfo.android.moviesapp.data.repository.MovieRepositoryImpl
import com.adolfo.android.moviesapp.domain.repository.MovieRepository
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain: Interceptor.Chain ->
                val originalHttpUrl: HttpUrl = chain.request().url
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", API_KEY)
                    .build()
                val requestBuilder: Request.Builder = chain.request().newBuilder()
                    .url(url)
                chain.proceed(requestBuilder.build())
            })
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()
    }

    @Provides
    @Singleton
    fun providesRetrofitMovieService(okHttpClient: OkHttpClient): MovieService {
        val moshi = Moshi.Builder()
            .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun providesDatabase(application: Application): MovieCartDatabase {
        return Room.databaseBuilder(
            context = application,
            name = "movie_db",
            klass = MovieCartDatabase::class.java
        ).build()
    }

    @Provides
    @Singleton
    fun providesMovieRepository(
        movieService: MovieService,
        movieDatabase: MovieCartDatabase
    ): MovieRepository {
        return MovieRepositoryImpl(
            movieService = movieService,
            movieDatabase = movieDatabase.movieCartDao()
        )
    }

}