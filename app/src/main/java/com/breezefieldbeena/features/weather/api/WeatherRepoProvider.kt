package com.breezefieldbeena.features.weather.api

import com.breezefieldbeena.features.task.api.TaskApi
import com.breezefieldbeena.features.task.api.TaskRepo

object WeatherRepoProvider {
    fun weatherRepoProvider(): WeatherRepo {
        return WeatherRepo(WeatherApi.create())
    }
}