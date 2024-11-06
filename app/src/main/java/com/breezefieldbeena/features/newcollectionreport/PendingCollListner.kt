package com.breezefieldbeena.features.newcollectionreport

import com.breezefieldbeena.features.photoReg.model.UserListResponseModel

interface PendingCollListner {
    fun getUserInfoOnLick(obj: PendingCollData)
}