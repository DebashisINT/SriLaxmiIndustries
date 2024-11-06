package com.breezefieldbeena.features.stockAddCurrentStock.api

import com.breezefieldbeena.features.location.shopRevisitStatus.ShopRevisitStatusApi
import com.breezefieldbeena.features.location.shopRevisitStatus.ShopRevisitStatusRepository

object ShopAddStockProvider {
    fun provideShopAddStockRepository(): ShopAddStockRepository {
        return ShopAddStockRepository(ShopAddStockApi.create())
    }
}