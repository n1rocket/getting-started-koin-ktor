package org.koin.sample

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.gson.gson
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.commandLineEnvironment
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.koin.Logger.slf4jLogger
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.inject
import java.text.DateFormat

fun main(args: Array<String>) {
    // Start Ktor
    embeddedServer(Netty, commandLineEnvironment(args)).start()
}

fun Application.main() {
    // Install Ktor features
    install(DefaultHeaders)
    install(CallLogging)
    install(ContentNegotiation) {
        gson {
            // Configure Gson here
            setPrettyPrinting()
            setDateFormat(DateFormat.LONG)
        }
    }
    install(Koin) {
        slf4jLogger()
        modules(appModule)
    }

    // Lazy inject HelloService
    val helloService by inject<HelloService>()
    val modelService by inject<ModelService>()

    // Routing section
    routing {
        get("/") {
            call.respondText(helloService.sayWelcome())
        }
        get("/hello") {
            call.respondText(helloService.sayHello())
        }
        get("/model") {
            call.respond(modelService.getModels())
        }
        get("/model/{key}") {
            val item = call.parameters["key"]?.let { it1 -> modelService.getItemByKey(it1) }
            if (item == null)
                call.respond(HttpStatusCode.NotFound)
            else
                call.respond(item)
        }
    }
}