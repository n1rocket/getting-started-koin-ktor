package org.koin.sample

import org.koin.dsl.module.module
import org.koin.experimental.builder.single
import org.koin.experimental.builder.singleBy

val helloAppModule = module(createOnStart = true) {
    singleBy<HelloServiceImpl, HelloService>()
    single<HelloRepository>()
}

// or
//val helloAppModule = module(createOnStart = true) {
//    single<HelloService> { HelloServiceImpl(get()) }
//    single { HelloRepository() }
//}