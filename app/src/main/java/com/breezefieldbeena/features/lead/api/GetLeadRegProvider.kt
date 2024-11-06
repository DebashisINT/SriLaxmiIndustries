package com.breezefieldbeena.features.lead.api

import com.breezefieldbeena.features.NewQuotation.api.GetQuotListRegRepository
import com.breezefieldbeena.features.NewQuotation.api.GetQutoListApi


object GetLeadRegProvider {
    fun provideList(): GetLeadListRegRepository {
        return GetLeadListRegRepository(GetLeadListApi.create())
    }
}