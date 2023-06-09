package com.codev.recruitment.ralphemersonmanzano.di

import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.codev.recruitment.ralphemersonmanzano.R
import com.codev.recruitment.ralphemersonmanzano.favorites.navigation.FavoritesNavigation
import com.codev.recruitment.ralphemersonmanzano.home.navigation.HomeNavigation
import com.codev.recruitment.ralphemersonmanzano.navigation.FavoritesNavigator
import com.codev.recruitment.ralphemersonmanzano.navigation.HomeNavigator
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object NavControllerModule {

    @Provides
    fun navController(activity: FragmentActivity): NavController {
        return (activity.supportFragmentManager.findFragmentById(R.id.nav_host_fragment)!! as NavHostFragment).navController
    }
}

@Module
@InstallIn(ActivityComponent::class)
abstract class NavigatorModule {

    @Binds
    abstract fun homeNavigation(navigator: HomeNavigator): HomeNavigation

    @Binds
    abstract fun favoritesNavigation(navigator: FavoritesNavigator): FavoritesNavigation
    // add other navigators here
}