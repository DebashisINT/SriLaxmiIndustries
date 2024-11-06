package com.breezefieldbeena.features.stockAddCurrentStock.api

import com.breezefieldbeena.base.BaseResponse
import com.breezefieldbeena.features.location.model.ShopRevisitStatusRequest
import com.breezefieldbeena.features.location.shopRevisitStatus.ShopRevisitStatusApi
import com.breezefieldbeena.features.stockAddCurrentStock.ShopAddCurrentStockRequest
import com.breezefieldbeena.features.stockAddCurrentStock.model.CurrentStockGetData
import com.breezefieldbeena.features.stockCompetetorStock.model.CompetetorStockGetData
import io.reactivex.Observable

class ShopAddStockRepository (val apiService : ShopAddStockApi){
    fun shopAddStock(shopAddCurrentStockRequest: ShopAddCurrentStockRequest?): Observable<BaseResponse> {
        return apiService.submShopAddStock(shopAddCurrentStockRequest)
    }

    fun getCurrStockList(sessiontoken: String, user_id: String, date: String): Observable<CurrentStockGetData> {
        return apiService.getCurrStockListApi(sessiontoken, user_id, date)
    }

}