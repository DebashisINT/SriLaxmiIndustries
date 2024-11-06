package com.breezefieldbeena.features.activities.api

import com.breezefieldbeena.features.member.api.TeamApi
import com.breezefieldbeena.features.member.api.TeamRepo

object ActivityRepoProvider {
    fun activityRepoProvider(): ActivityRepo {
        return ActivityRepo(ActivityApi.create())
    }

    fun activityImageRepoProvider(): ActivityRepo {
        return ActivityRepo(ActivityApi.createImage())
    }
}