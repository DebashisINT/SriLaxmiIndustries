package com.breezefieldbeena.features.dashboard.presentation.api.dayStartEnd

import com.breezefieldbeena.features.stockCompetetorStock.api.AddCompStockApi
import com.breezefieldbeena.features.stockCompetetorStock.api.AddCompStockRepository

object DayStartEndRepoProvider {
    fun dayStartRepositiry(): DayStartEndRepository {
        return DayStartEndRepository(DayStartEndApi.create())
    }

}