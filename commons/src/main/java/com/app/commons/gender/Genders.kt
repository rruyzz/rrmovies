package com.app.commons.gender

import com.app.commons.models.GendersList

object Genders {

    private var genderList: GendersList = GendersList(listOf())

    fun getGender(): GendersList  = genderList

    fun setList(_gendersList: GendersList) {
        this.genderList = _gendersList
    }
}