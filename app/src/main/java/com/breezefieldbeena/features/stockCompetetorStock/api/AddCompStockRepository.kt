package com.breezefieldbeena.features.stockCompetetorStock.api

import com.breezefieldbeena.base.BaseResponse
import com.breezefieldbeena.features.orderList.model.NewOrderListResponseModel
import com.breezefieldbeena.features.stockCompetetorStock.ShopAddCompetetorStockRequest
import com.breezefieldbeena.features.stockCompetetorStock.model.CompetetorStockGetData
import io.reactivex.Observable

class AddCompStockRepository(val apiService:AddCompStockApi){

    fun addCompStock(shopAddCompetetorStockRequest: ShopAddCompetetorStockRequest): Observable<BaseResponse> {
        return apiService.submShopCompStock(shopAddCompetetorStockRequest)
    }

    fun getCompStockList(sessiontoken: String, user_id: String, date: String): Observable<CompetetorStockGetData> {
        return apiService.getCompStockList(sessiontoken, user_id, date)
    }
}