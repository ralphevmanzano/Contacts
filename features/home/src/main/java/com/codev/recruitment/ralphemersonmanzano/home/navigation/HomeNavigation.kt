package com.codev.recruitment.ralphemersonmanzano.home.navigation

interface HomeNavigation {
    fun navigateToDetails(id: Int)

    fun navigateToForm()

    companion object Key {
        const val KEY_ID = "KEY_ID"
    }
}