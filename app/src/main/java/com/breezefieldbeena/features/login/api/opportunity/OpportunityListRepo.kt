package com.breezefieldbeena.features.login.api.opportunity

import com.breezefieldbeena.app.Pref
import com.breezefieldbeena.app.utils.AppUtils
import com.breezefieldbeena.base.BaseResponse
import com.breezefieldbeena.features.addshop.model.AudioFetchDataCLass
import com.breezefieldbeena.features.addshop.model.StockAllResponse
import com.breezefieldbeena.features.dashboard.presentation.DashboardActivity
import com.breezefieldbeena.features.login.model.opportunitymodel.OpportunityStatusListResponseModel
import com.breezefieldbeena.features.login.model.productlistmodel.ProductListResponseModel
import com.breezefieldbeena.features.orderITC.SyncDeleteOppt
import com.breezefieldbeena.features.orderITC.SyncEditOppt
import com.breezefieldbeena.features.orderITC.SyncOppt
import com.breezefieldbeena.features.orderList.model.OpportunityListResponseModel
import io.reactivex.Observable
import timber.log.Timber

/**
 * Created by Saikat on 20-11-2018.
 */
class OpportunityListRepo(val apiService: OpportunityListApi) {
    fun getOpportunityStatus(session_token: String): Observable<OpportunityStatusListResponseModel> {
        return apiService.getOpportunityStatusList(session_token)
    }

    fun saveOpportunity(syncOppt: SyncOppt): Observable<BaseResponse> {
        return apiService.saveOpportunity(syncOppt)
    }

    fun editOpportunity(syncEditOppt: SyncEditOppt): Observable<BaseResponse> {
        return apiService.editOpportunity(syncEditOppt)
    }
    fun deleteOpportunity(syncDeleteOppt: SyncDeleteOppt): Observable<BaseResponse> {
        return apiService.deleteOpportunity(syncDeleteOppt)
    }
    fun getOpportunityL(user_id: String): Observable<OpportunityListResponseModel> {
        return apiService.getOpportunityL(user_id)
    }

    fun getAudioL(user_id: String,data_limit_in_days:String): Observable<AudioFetchDataCLass> {
        return apiService.getAudioLApi(user_id,data_limit_in_days)
    }


    fun saveLMSModuleInfo(obj: DashboardActivity.LMSModule): Observable<BaseResponse> {
        return apiService.saveLMSModuleInfoApi(obj)
    }

    fun getAllStock(user_id: String): Observable<StockAllResponse> {
        return apiService.getAllStockApi(user_id)
    }
}