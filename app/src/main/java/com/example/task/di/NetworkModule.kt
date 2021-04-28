

import com.bit68.paymob.dagger.module.OkHttpClientModule
import com.example.notesapp.di.AppModule
 import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.example.task.di.diInterfaces.AppRemoteUrl

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
 import retrofit2.converter.gson.GsonConverterFactory


@Module(includes = [OkHttpClientModule::class, AppModule::class])
class NetworkModule {


    @Provides
    fun retrofit(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory
                 , @AppRemoteUrl baseUrl:String
    ): Retrofit {
        return Retrofit.Builder().client(okHttpClient).baseUrl(baseUrl)
                .addConverterFactory(gsonConverterFactory)
                 .build()
    }

    @Provides
    fun gson(): Gson {
        val gsonBuilder = GsonBuilder()
                .setLenient()
        return gsonBuilder.create()
    }

    @Provides
    fun gsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }


}