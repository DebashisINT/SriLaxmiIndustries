package com.breezefieldbeena.features.viewAllOrder.orderOptimized

import com.breezefieldbeena.app.domain.ProductOnlineRateTempEntity
import com.breezefieldbeena.base.BaseResponse
import com.breezefieldbeena.features.login.model.productlistmodel.ProductRateDataModel
import java.io.Serializable

class ProductRateOnlineListResponseModel: BaseResponse(), Serializable {
    var product_rate_list: ArrayList<ProductOnlineRateTempEntity>? = null
}