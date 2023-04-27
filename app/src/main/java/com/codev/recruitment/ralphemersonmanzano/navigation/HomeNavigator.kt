package com.codev.recruitment.ralphemersonmanzano.navigation

import androidx.navigation.NavController
import com.codev.recruitment.ralphemersonmanzano.NavigationMainDirections
import com.codev.recruitment.ralphemersonmanzano.details.R
import com.codev.recruitment.ralphemersonmanzano.home.navigation.HomeNavigation
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class HomeNavigator @Inject constructor(
    private val navController: NavController
): HomeNavigation {

    override fun navigateToDetails(id: Long) {
        navController.navigate(NavigationMainDirections.actionToDetails(id, R.id.fragment_details))
    }

    override fun navigateToForm() {
        navController.navigate(NavigationMainDirections.actionToDetails(-1, R.id.fragment_form))
    }
}