package com.bit68.paymob.dagger.module

import android.content.Context
import com.example.notesapp.di.AppModule
import com.example.task.di.ApplicationModule
 import com.example.task.di.diInterfaces.AppContext

import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit


@Module(includes = [AppModule::class])
class OkHttpClientModule {

    @Provides
    fun cache(@AppContext context: Context): Cache {
        val cacheSize = (5 * 1024 * 1024).toLong()
        val myCache = Cache(context.cacheDir, cacheSize)
        return myCache
    }




    @Provides
    fun okHttpClient(@AppContext context: Context, httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val httpCacheDirectory = File(context.cacheDir, "responses")
        val cacheSize = 10 * 1024 * 1024 // 10 MiB
        val cache = Cache(httpCacheDirectory, cacheSize.toLong())
        return OkHttpClient()
            .newBuilder()
            .cache(cache)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
//                .addNetworkInterceptor (REWRITE_CACHE_CONTROL_INTERCEPTOR)
            .addInterceptor{ chain ->
                val original = chain.request()
                val request = original.newBuilder()
//                if (preference.getToken() != null) {
//                request.header("Authorization", "bearer ${preference.getToken()!!}")
//            }
                request.header("Content-Type", "application/json")
                    .method(original.method, original.body)

                chain.proceed(request.build()) }
            .addInterceptor(httpLoggingInterceptor)
             .addNetworkInterceptor { chain ->
                var request = chain.request()

                val response = chain.proceed(chain.request())

                if (NetworkUtils.isConnected()) { //Todo make network util
                    // re-write response header to force use of cache
                    val cacheControl = CacheControl.Builder()
                        .maxAge(1, TimeUnit.SECONDS)
                        .build()


                    response.newBuilder()
                        .header("Cache-Control", cacheControl.toString())
                        .build()
                }else{
                    // re-write response header to force use of cache
                    val cacheControl = CacheControl.Builder()
                        .maxAge(2, TimeUnit.MINUTES)
                        .build()


                    response.newBuilder()
                        .header("Cache-Control", cacheControl.toString())
                        .build()
                }
            }
            .build()
    }







    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }}