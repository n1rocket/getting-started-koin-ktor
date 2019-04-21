package org.koin.sample

interface HelloService {
    fun sayHello(): String
    fun sayWelcome(): String
}

class HelloServiceImpl(val helloRepository: HelloRepository) : HelloService {
    override fun sayWelcome() = "Bienvenido!"

    override fun sayHello() = "Hello ${helloRepository.getHello()} !"
}