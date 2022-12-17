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

import io.ktor.http.*
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File
import kotlinx.html.*

fun Application.textEditor() {
    install(ContentNegotiation) { gson { setPrettyPrinting() } }

    routing {
        get("/") {
            call.respondHtml {
                head {
                    // Include the CodeMirror library in the head of the HTML page
                    script(
                        src =
                            "https://cdn.jsdelivr.net/npm/codemirror@6.0.1/lib/codemirror.min.js") {
                        }
                    link(
                        rel = "stylesheet",
                        href =
                            "https://cdn.jsdelivr.net/npm/codemirror@6.0.1/lib/codemirror.min.css")
                }
                body {
                    // Create a form with dropdown menus to choose the code language and theme, and
                    // buttons to save and download the code
                    form {
                        select {
                            id = "language"
                            option { +"Plain Text" }
                            option { +"JavaScript" }
                            option { +"HTML" }
                            option { +"CSS" }
                            option { +"Java" }
                            option { +"Python" }
                        }
                        select {
                            id = "theme"
                            option { +"default" }
                            option { +"dracula" }
                            option { +"cobalt" }
                            option { +"solarized light" }
                            option { +"solarized dark" }
                            option { +"monokai" }
                        }
                        input(type = InputType.button, name = "save") {
                            value = "Save"
                            onClick = "save()"
                        }
                        input(type = InputType.button, name = "download") {
                            value = "Download"
                            onClick = "download()"
                        }
                    }
                    textArea {
                        id = "code"
                        rows = 40.toString()
                        cols = 120.toString()
                    }
                    script {
                        // Initialize CodeMirror with the desired options
                        unsafe {
                            +"""
                            var editor = CodeMirror.fromTextArea(document.getElementById("code"), {
                                lineNumbers: true,
                                mode: "python",
                                theme: "dracula"
                            });
                            """
                        }
                    }
                    // Add a script to handle the save and download
                    val scriptsFolder = File("src/main/kotlin/io/github/realyusufismail/js")
                    for (file in scriptsFolder.listFiles()!!) {
                        script { src = "/js/${file.name}" }
                    }
                }
            }
        }
        post("/save") {
            val code = call.receiveText()
            // Save the code to the server (e.g., to a database or a file)
        }
        get("/download") {
            val code = call.receiveText()
            call.respond(HttpStatusCode.OK, code)
        }
    }
}
