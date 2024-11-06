package com.breezefieldbeena.features.viewAllOrder.interf

import com.breezefieldbeena.app.domain.NewOrderGenderEntity
import com.breezefieldbeena.app.domain.NewOrderProductEntity

interface ProductListNewOrderOnClick {
    fun productListOnClick(product: NewOrderProductEntity)
}