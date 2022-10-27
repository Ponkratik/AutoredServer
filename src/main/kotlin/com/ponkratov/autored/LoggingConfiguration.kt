package com.ponkratov.autored

import org.slf4j.*
import org.springframework.beans.factory.InjectionPoint
import org.springframework.context.annotation.*

@Configuration
class LoggingConfiguration {
    @Bean
    @Scope("prototype")
    fun logger(injectionPoint: InjectionPoint): Logger {
        return LoggerFactory.getLogger(
            injectionPoint.methodParameter?.containingClass
                ?:injectionPoint.field?.declaringClass)
    }
}