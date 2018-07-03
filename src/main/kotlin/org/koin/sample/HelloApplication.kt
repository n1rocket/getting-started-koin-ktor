package org.koin.sample

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.commandLineEnvironment
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.koin.ktor.ext.inject
import org.koin.log.Logger.SLF4JLogger
import org.koin.standalone.StandAloneContext

fun main(args: Array<String>) {
    // Start Koin
    StandAloneContext.startKoin(listOf(helloAppModule), logger = SLF4JLogger())
    // Start Ktor
    embeddedServer(Netty, commandLineEnvironment(args)).start()
}

fun Application.main() {
    // Install Ktor features
    install(DefaultHeaders)
    install(CallLogging)

    // Lazy inject HelloService
    val service by inject<HelloService>()

    // Routing section
    routing {
        get("/hello") {
            call.respondText(service.sayHello())
        }
    }
}