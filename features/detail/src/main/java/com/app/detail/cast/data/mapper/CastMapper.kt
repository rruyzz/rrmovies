package com.app.detail.cast.data.mapper

import com.app.detail.cast.data.model.ActorResponse
import com.app.detail.cast.domain.model.Actor

class CastMapper {
    operator fun invoke(
        response: ActorResponse?
    ): List<Actor> {
        return response?.cast?.toMutableList()?.map {
            Actor(
                image = it.profilePath.orEmpty(),
                name = it.originalName.orEmpty()
            )
        }?.filter {
            it.image.isNotEmpty() && it.name.isNotEmpty()
        }.orEmpty().subList(0,6)
    }
}