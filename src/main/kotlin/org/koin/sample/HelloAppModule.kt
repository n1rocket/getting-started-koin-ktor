package org.koin.sample

import org.koin.dsl.module.module

val helloAppModule = module(createOnStart = true) {
    single { HelloServiceImpl(get()) as HelloService }
    single { HelloRepository() }
}