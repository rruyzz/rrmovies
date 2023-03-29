package com.app.commons.gender

import com.app.commons.models.GenresItem

class GenderListMapper{

    operator fun invoke(listInt: List<Int>?
    ) : String {
        val listString = mutableListOf<String>()
        Genders.getGender().genres.forEach {
            if(listInt?.contains(it.id) == true) {
                listString.add(it.name)
            }
        }
        return listString.joinToString()
    }
}