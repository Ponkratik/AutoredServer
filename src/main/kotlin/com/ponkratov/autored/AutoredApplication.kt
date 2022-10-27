package com.ponkratov.autored

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class AutoredApplication

fun main(args: Array<String>) {
    runApplication<AutoredApplication>(*args)
}
