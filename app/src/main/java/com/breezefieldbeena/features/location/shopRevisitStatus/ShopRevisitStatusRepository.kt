package com.breezefieldbeena.features.location.shopRevisitStatus

import com.breezefieldbeena.base.BaseResponse
import com.breezefieldbeena.features.location.model.ShopDurationRequest
import com.breezefieldbeena.features.location.model.ShopRevisitStatusRequest
import io.reactivex.Observable

class ShopRevisitStatusRepository(val apiService : ShopRevisitStatusApi) {
    fun shopRevisitStatus(shopRevisitStatus: ShopRevisitStatusRequest?): Observable<BaseResponse> {
        return apiService.submShopRevisitStatus(shopRevisitStatus)
    }
}