package com.breezefieldbeena.features.viewAllOrder.interf

import com.breezefieldbeena.app.domain.NewOrderGenderEntity
import com.breezefieldbeena.features.viewAllOrder.model.ProductOrder

interface ColorListOnCLick {
    fun colorListOnCLick(size_qty_list: ArrayList<ProductOrder>, adpPosition:Int)
}