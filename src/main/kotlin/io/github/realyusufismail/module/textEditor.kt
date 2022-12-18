package io.github.realyusufismail.module

import io.github.realyusufismail.plugins.configureRouting
import io.ktor.server.application.*

fun Application.textEditor() {
    configureRouting()
}