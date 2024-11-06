package com.breezefieldbeena.features.nearbyuserlist.api

import com.breezefieldbeena.app.Pref
import com.breezefieldbeena.features.nearbyuserlist.model.NearbyUserResponseModel
import com.breezefieldbeena.features.newcollection.model.NewCollectionListResponseModel
import com.breezefieldbeena.features.newcollection.newcollectionlistapi.NewCollectionListApi
import io.reactivex.Observable

class NearbyUserRepo(val apiService: NearbyUserApi) {
    fun nearbyUserList(): Observable<NearbyUserResponseModel> {
        return apiService.getNearbyUserList(Pref.session_token!!, Pref.user_id!!)
    }
}