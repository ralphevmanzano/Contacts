package com.codev.recruitment.ralphemersonmanzano.navigation

import androidx.navigation.NavController
import com.codev.recruitment.ralphemersonmanzano.NavigationMainDirections
import com.codev.recruitment.ralphemersonmanzano.details.R
import com.codev.recruitment.ralphemersonmanzano.favorites.navigation.FavoritesNavigation
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class FavoritesNavigator @Inject constructor(
    private val navController: NavController
): FavoritesNavigation {

    override fun navigateToDetails(id: Long) {
        navController.navigate(NavigationMainDirections.actionToDetails(id, R.id.fragment_details))
    }
}