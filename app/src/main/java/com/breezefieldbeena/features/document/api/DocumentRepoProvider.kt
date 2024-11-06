package com.breezefieldbeena.features.document.api

import com.breezefieldbeena.features.dymanicSection.api.DynamicApi
import com.breezefieldbeena.features.dymanicSection.api.DynamicRepo

object DocumentRepoProvider {
    fun documentRepoProvider(): DocumentRepo {
        return DocumentRepo(DocumentApi.create())
    }

    fun documentRepoProviderMultipart(): DocumentRepo {
        return DocumentRepo(DocumentApi.createImage())
    }
}