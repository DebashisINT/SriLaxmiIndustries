package com.breezefieldbeena.features.weather.api

import com.breezefieldbeena.base.BaseResponse
import com.breezefieldbeena.features.task.api.TaskApi
import com.breezefieldbeena.features.task.model.AddTaskInputModel
import com.breezefieldbeena.features.weather.model.ForeCastAPIResponse
import com.breezefieldbeena.features.weather.model.WeatherAPIResponse
import io.reactivex.Observable

class WeatherRepo(val apiService: WeatherApi) {
    fun getCurrentWeather(zipCode: String): Observable<WeatherAPIResponse> {
        return apiService.getTodayWeather(zipCode)
    }

    fun getWeatherForecast(zipCode: String): Observable<ForeCastAPIResponse> {
        return apiService.getForecast(zipCode)
    }
}