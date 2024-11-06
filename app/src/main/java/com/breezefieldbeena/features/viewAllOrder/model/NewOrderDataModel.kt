package com.breezefieldbeena.features.viewAllOrder.model

import com.breezefieldbeena.app.domain.NewOrderColorEntity
import com.breezefieldbeena.app.domain.NewOrderGenderEntity
import com.breezefieldbeena.app.domain.NewOrderProductEntity
import com.breezefieldbeena.app.domain.NewOrderSizeEntity
import com.breezefieldbeena.features.stockCompetetorStock.model.CompetetorStockGetDataDtls

class NewOrderDataModel {
    var status:String ? = null
    var message:String ? = null
    var Gender_list :ArrayList<NewOrderGenderEntity>? = null
    var Product_list :ArrayList<NewOrderProductEntity>? = null
    var Color_list :ArrayList<NewOrderColorEntity>? = null
    var size_list :ArrayList<NewOrderSizeEntity>? = null
}

