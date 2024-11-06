package com.breezefieldbeena.features.viewAllOrder.interf

import com.breezefieldbeena.app.domain.NewOrderGenderEntity
import com.breezefieldbeena.features.viewAllOrder.model.ProductOrder
import java.text.FieldPosition

interface NewOrderSizeQtyDelOnClick {
    fun sizeQtySelListOnClick(product_size_qty: ArrayList<ProductOrder>)
    fun sizeQtyListOnClick(product_size_qty: ProductOrder,position: Int)
}