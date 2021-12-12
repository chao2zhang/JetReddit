package com.chaozhang.jetreddit.di

import android.content.Context
import androidx.work.Configuration
import coil.Coil
import coil.ImageLoader
import com.chaozhang.jetreddit.data.service.RedditService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.asCoroutineDispatcher
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.SynchronousQueue
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun threadPoolExecutor(): ThreadPoolExecutor {
        val index = AtomicInteger()
        return ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors().coerceAtLeast(2),
            Int.MAX_VALUE,
            60,
            TimeUnit.SECONDS,
            SynchronousQueue(),
            ThreadFactory { runnable ->
                Thread(runnable, "Shared Thread Pool ${index.incrementAndGet()}")
            }
        )
    }

    @Provides
    @Singleton
    fun okhttpClient(
        threadPoolExecutor: ThreadPoolExecutor,
        @ApplicationContext applicationContext: Context
    ): OkHttpClient {
        val client = OkHttpClient.Builder()
            .dispatcher(Dispatcher(threadPoolExecutor))
            .build()
        Coil.setImageLoader {
            ImageLoader.Builder(applicationContext).okHttpClient(client).build()
        }
        return client
    }

    @Provides
    @Singleton
    fun retrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://www.reddit.com")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .build()


    @Provides
    @Singleton
    fun redditService(retrofit: Retrofit): RedditService = retrofit.create(RedditService::class.java)

    @Provides
    @Singleton
    fun workManagerConfig(threadPoolExecutor: ThreadPoolExecutor): Configuration {
        return Configuration.Builder()
            .setExecutor(threadPoolExecutor)
            .build()
    }

    @CoDispatcher(DispatcherType.SHARED)
    @Singleton
    @Provides
    fun sharedDispatcher(threadPoolExecutor: ThreadPoolExecutor): CoroutineDispatcher {
        return threadPoolExecutor.asCoroutineDispatcher()
    }
}
