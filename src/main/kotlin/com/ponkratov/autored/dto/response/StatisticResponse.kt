package com.ponkratov.autored.dto.response

import com.ponkratov.autored.dto.MapObjectStatistic

class StatisticResponse(
    val totalUsers: Long,
    val totalAdvertisements: Long,
    val qtyCarsByMake: List<MapObjectStatistic>,
    val qtyAdvertisementsByLocation: List<MapObjectStatistic>
)