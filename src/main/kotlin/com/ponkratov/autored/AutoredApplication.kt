package com.ponkratov.autored

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AutoredApplication

fun main(args: Array<String>) {
    runApplication<AutoredApplication>(*args)
}
