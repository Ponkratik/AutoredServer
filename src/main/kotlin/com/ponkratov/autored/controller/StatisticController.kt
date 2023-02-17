package com.ponkratov.autored.controller

import com.ponkratov.autored.dto.response.StatisticResponse
import com.ponkratov.autored.service.StatisticService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/statistic")
class StatisticController {

    @Autowired
    private var _statisticService: StatisticService? = null
    private val statisticService get() = requireNotNull(_statisticService)

    @GetMapping("/get")
    fun getStatistics(): ResponseEntity<StatisticResponse> {
        val result = statisticService.getStatisticResponse()
        return ResponseEntity.ok(result)
    }
}