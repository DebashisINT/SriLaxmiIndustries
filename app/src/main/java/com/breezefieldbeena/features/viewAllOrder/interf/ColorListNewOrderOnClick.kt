package com.breezefieldbeena.features.viewAllOrder.interf

import com.breezefieldbeena.app.domain.NewOrderColorEntity
import com.breezefieldbeena.app.domain.NewOrderProductEntity

interface ColorListNewOrderOnClick {
    fun productListOnClick(color: NewOrderColorEntity)
}