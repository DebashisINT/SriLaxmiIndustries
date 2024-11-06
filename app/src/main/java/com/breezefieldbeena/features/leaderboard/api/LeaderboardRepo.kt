package com.breezefieldbeena.features.leaderboard.api

import android.content.Context
import android.net.Uri
import android.text.TextUtils
import com.fasterxml.jackson.databind.ObjectMapper
import com.breezefieldbeena.app.FileUtils
import com.breezefieldbeena.app.Pref
import com.breezefieldbeena.base.BaseResponse
import com.breezefieldbeena.features.addshop.model.AddLogReqData
import com.breezefieldbeena.features.addshop.model.AddShopRequestData
import com.breezefieldbeena.features.addshop.model.AddShopResponse
import com.breezefieldbeena.features.addshop.model.LogFileResponse
import com.breezefieldbeena.features.addshop.model.UpdateAddrReq
import com.breezefieldbeena.features.contacts.CallHisDtls
import com.breezefieldbeena.features.contacts.CompanyReqData
import com.breezefieldbeena.features.contacts.ContactMasterRes
import com.breezefieldbeena.features.contacts.SourceMasterRes
import com.breezefieldbeena.features.contacts.StageMasterRes
import com.breezefieldbeena.features.contacts.StatusMasterRes
import com.breezefieldbeena.features.contacts.TypeMasterRes
import com.breezefieldbeena.features.dashboard.presentation.DashboardActivity
import com.breezefieldbeena.features.login.model.WhatsappApiData
import com.breezefieldbeena.features.login.model.WhatsappApiFetchData
import com.google.gson.Gson
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

/**
 * Created by Puja on 10-10-2024.
 */
class LeaderboardRepo(val apiService: LeaderboardApi) {

    fun branchlist(session_token: String): Observable<LeaderboardBranchData> {
        return apiService.branchList(session_token)
    }
    fun ownDatalist(user_id: String,activitybased: String,branchwise: String,flag: String): Observable<LeaderboardOwnData> {
        return apiService.ownDatalist(user_id,activitybased,branchwise,flag)
    }
    fun overAllAPI(user_id: String,activitybased: String,branchwise: String,flag: String): Observable<LeaderboardOverAllData> {
        return apiService.overAllDatalist(user_id,activitybased,branchwise,flag)
    }
}