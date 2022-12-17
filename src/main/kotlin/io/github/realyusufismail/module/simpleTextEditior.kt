/*
 * Copyright 2022 RealYusufIsmail.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
package io.github.realyusufismail.module

import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.routing.*
import kotlinx.html.*

fun Application.simple() {
    routing {
        get("/") {
            call.respondHtml {
                head {
                    title { +"Simple Text Editor" }
                    link(rel = "stylesheet", href = "/css/styles.css")
                    // Add a script tag to include the JavaScript file
                    script(src = "/js/dark-mode.js") {}
                }
                body {
                    // Add a toggle switch to the user interface
                    label {
                        for_ = "dark-mode-toggle"
                        +"Dark Mode"
                    }
                    input(type = InputType.checkBox, name = "dark-mode-toggle") {
                        id = "dark-mode-toggle"
                        onClick = "toggleDarkMode()"
                    }
                    h1 { +"Simple Text Editor" }
                    form {
                        textArea {
                            name = "text"
                            rows = 25.toString()
                            cols = 80.toString()
                        }
                        br()
                        input(type = InputType.submit) { value = "Save" }
                    }
                }
            }
        }
    }
}
