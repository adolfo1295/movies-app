package com.adolfo.android.moviesapp.di

import com.adolfo.android.moviesapp.data.api.MovieService
import com.adolfo.android.moviesapp.data.api.MovieService.Companion.API_KEY
import com.adolfo.android.moviesapp.data.api.MovieService.Companion.BASE_URL
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
    fun providesMovieRepository(movieService: MovieService): MovieRepository {
        return MovieRepositoryImpl(movieService = movieService)
    }

}

/**
 * Request{method=GET, url=https://api.themoviedb.org/3/movie/now_playing, headers=[api_key:e9ba2bff1c00cd46a61c1896636fa978, language:es-M], tags={class retrofit2.Invocation=com.adolfo.android.moviesapp.data.api.MovieService.getHomeMovies() []}}
 */