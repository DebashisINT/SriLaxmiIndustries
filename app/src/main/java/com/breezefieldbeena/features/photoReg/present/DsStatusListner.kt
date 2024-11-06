package com.breezefieldbeena.features.photoReg.present

import com.breezefieldbeena.app.domain.ProspectEntity
import com.breezefieldbeena.features.photoReg.model.UserListResponseModel

interface DsStatusListner {
    fun getDSInfoOnLick(obj: ProspectEntity)
}