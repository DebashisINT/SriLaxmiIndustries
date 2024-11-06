package com.breezefieldbeena.features.mylearning.apiCall

import com.breezefieldbeena.features.login.api.opportunity.OpportunityListApi
import com.breezefieldbeena.features.login.api.opportunity.OpportunityListRepo

object LMSRepoProvider {
    fun getTopicList(): LMSRepo {
        return LMSRepo(LMSApi.create())
    }
}