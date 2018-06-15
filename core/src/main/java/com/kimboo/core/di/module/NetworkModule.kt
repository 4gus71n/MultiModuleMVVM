package com.kimboo.core.di.module

import android.content.Context
import android.util.Log
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.moczul.ok2curl.CurlInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Agustin on 01/11/2016.
 */
@Module
class NetworkModule() {

    companion object {
        private val ENABLE_OKHTTP_CACHE = true
        private val SIZE_OF_CACHE = (10 * 1024 * 1024).toLong() // 10 MB
    }

    @Provides
    @Singleton
    fun provideCache(context: Context) = okhttp3.Cache(File(context.cacheDir, "http"), SIZE_OF_CACHE)


    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create()
    }

    @Provides
    @Named("curlInterceptor")
    protected fun provideCurlInterceptor(): Interceptor {
        return CurlInterceptor { curlMessage ->
            Log.d("CURL", curlMessage)
        }
    }

    @Provides
    @Named("authInterceptor")
    protected fun providesAuthInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            request = request.newBuilder()
                    .header("Authorization", "Client-ID 6ea78556ea84b48")
                    .build()
            chain.proceed(request)
        }
    }

    @Provides
    @Named("cacheInterceptor")
    protected fun provideCacheInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            request = request.newBuilder()
                    .header("Cache-Control", "public, max-age=60")
                    .build()
            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    protected fun provideOkHttpClient(cache: okhttp3.Cache,
                                      @Named("curlInterceptor") curlInterceptor: Interceptor,
                                      @Named("cacheInterceptor") cacheInterceptor: Interceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (ENABLE_OKHTTP_CACHE) {
            builder.cache(cache)
        }
        builder.addInterceptor(curlInterceptor)
        builder.connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS)
        return builder.build()
    }

    @Provides
    @Singleton
    protected fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://api.imgur.com/3/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
    }

}
