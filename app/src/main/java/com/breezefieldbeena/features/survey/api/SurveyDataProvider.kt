package com.breezefieldbeena.features.survey.api

import com.breezefieldbeena.features.photoReg.api.GetUserListPhotoRegApi
import com.breezefieldbeena.features.photoReg.api.GetUserListPhotoRegRepository

object SurveyDataProvider{

    fun provideSurveyQ(): SurveyDataRepository {
        return SurveyDataRepository(SurveyDataApi.create())
    }

    fun provideSurveyQMultiP(): SurveyDataRepository {
        return SurveyDataRepository(SurveyDataApi.createImage())
    }
}