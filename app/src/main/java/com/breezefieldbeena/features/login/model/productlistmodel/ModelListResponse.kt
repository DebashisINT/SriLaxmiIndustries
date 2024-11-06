package com.breezefieldbeena.features.login.model.productlistmodel

import com.breezefieldbeena.app.domain.ModelEntity
import com.breezefieldbeena.app.domain.ProductListEntity
import com.breezefieldbeena.base.BaseResponse

class ModelListResponse: BaseResponse() {
    var model_list: ArrayList<ModelEntity>? = null
}