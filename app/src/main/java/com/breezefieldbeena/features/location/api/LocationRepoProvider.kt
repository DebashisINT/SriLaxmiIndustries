package com.breezefieldbeena.features.location.api

import com.breezefieldbeena.features.location.shopdurationapi.ShopDurationApi
import com.breezefieldbeena.features.location.shopdurationapi.ShopDurationRepository


object LocationRepoProvider {
    fun provideLocationRepository(): LocationRepo {
        return LocationRepo(LocationApi.create())
    }
}