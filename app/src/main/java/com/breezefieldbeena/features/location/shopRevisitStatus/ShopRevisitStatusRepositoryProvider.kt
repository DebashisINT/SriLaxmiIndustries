package com.breezefieldbeena.features.location.shopRevisitStatus

import com.breezefieldbeena.features.location.shopdurationapi.ShopDurationApi
import com.breezefieldbeena.features.location.shopdurationapi.ShopDurationRepository

object ShopRevisitStatusRepositoryProvider {
    fun provideShopRevisitStatusRepository(): ShopRevisitStatusRepository {
        return ShopRevisitStatusRepository(ShopRevisitStatusApi.create())
    }
}