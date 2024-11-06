package com.breezefieldbeena.features.viewAllOrder.interf

import com.breezefieldbeena.app.domain.NewOrderProductEntity
import com.breezefieldbeena.app.domain.NewOrderSizeEntity

interface SizeListNewOrderOnClick {
    fun sizeListOnClick(size: NewOrderSizeEntity)
}