package com.breezefieldbeena.features.newcollection.model

import com.breezefieldbeena.app.domain.CollectionDetailsEntity
import com.breezefieldbeena.base.BaseResponse
import com.breezefieldbeena.features.shopdetail.presentation.model.collectionlist.CollectionListDataModel

/**
 * Created by Saikat on 15-02-2019.
 */
class NewCollectionListResponseModel : BaseResponse() {
    //var collection_list: ArrayList<CollectionListDataModel>? = null
    var collection_list: ArrayList<CollectionDetailsEntity>? = null
}