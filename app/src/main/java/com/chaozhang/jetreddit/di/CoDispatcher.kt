package com.chaozhang.jetreddit.di

import javax.inject.Qualifier

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.SOURCE)
annotation class CoDispatcher(val type: DispatcherType)

enum class DispatcherType {
    SHARED
}
