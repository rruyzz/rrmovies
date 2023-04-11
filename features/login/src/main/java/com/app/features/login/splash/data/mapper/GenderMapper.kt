package com.app.features.login.splash.data.mapper

import com.app.commons.models.GenderResponse
import com.app.commons.models.GendersList
import com.app.commons.models.GenresItem

class GenderMapper {

    operator fun invoke(response: GenderResponse?) : GendersList {
        val list = response?.genres?.map {
            GenresItem(
                name = it.name,
                id = it.id
            )
        }.orEmpty()
        return GendersList(
            genres = list
        )
    }
}