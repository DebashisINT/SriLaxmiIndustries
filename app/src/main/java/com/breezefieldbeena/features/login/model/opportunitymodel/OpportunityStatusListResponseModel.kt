package com.breezefieldbeena.features.login.model.opportunitymodel

import com.breezefieldbeena.app.domain.OpportunityStatusEntity
import com.breezefieldbeena.app.domain.ProductListEntity
import com.breezefieldbeena.base.BaseResponse

/**
 * Created by Puja on 30.05.2024
 */
class OpportunityStatusListResponseModel : BaseResponse() {
    var status_list: ArrayList<OpportunityStatusEntity>? = null
}