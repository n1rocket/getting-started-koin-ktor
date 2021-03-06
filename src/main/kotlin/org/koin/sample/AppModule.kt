package org.koin.sample

import org.koin.dsl.module
import org.koin.experimental.builder.single
import org.koin.experimental.builder.singleBy

val appModule = module(createdAtStart = true) {
    singleBy<HelloService, HelloServiceImpl>()
    single<HelloRepository>()
    singleBy<ModelService, ModelServiceImpl>()
    single<ModelRepository>()
}
