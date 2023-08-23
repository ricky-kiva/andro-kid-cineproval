package com.rickyslash.kidcineproval.core.di

import androidx.room.Room
import com.rickyslash.kidcineproval.core.data.MovieRepository
import com.rickyslash.kidcineproval.core.data.source.local.LocalDataSource
import com.rickyslash.kidcineproval.core.data.source.local.room.MovieDatabase
import com.rickyslash.kidcineproval.core.data.source.remote.RemoteDataSource
import com.rickyslash.kidcineproval.core.data.source.remote.network.ApiService
import com.rickyslash.kidcineproval.core.domain.repository.IMovieRepository
import com.rickyslash.kidcineproval.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.BuildConfig
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MovieDatabase>().movieDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("eclipse".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java,
            "Movie.db"
        )
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "api.themoviedb.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/5VLcahb6x4EvvFrCF2TePZulWqrLHS2jCg9Ywv6JHog=")
            .add(hostname, "sha256/vxRon/El5KuI4vx5ey1DgmsYmRY0nDd5Cg4GfJ8S+bg=")
            .add(hostname, "sha256/KwccWaCgrnaw6tsrrSO61FgLacNgG2MMLq8GE6+oP5I=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            .addInterceptor { chain ->
                val req = chain.request()
                val reqHeaders = req.newBuilder()
                    .header("Authorization", "Bearer {please put your own token}")
                    .build()
                chain.proceed(reqHeaders)
            }
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMovieRepository> { MovieRepository(get(), get(), get()) }
}