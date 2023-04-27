package com.codev.recruitment.ralphemersonmanzano.favorites.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.codev.recruitment.ralphemersonmanzano.favorites.databinding.FragmentFavoritesBinding
import com.codev.recruitment.ralphemersonmanzano.favorites.navigation.FavoritesNavigation
import com.codev.recruitment.ralphemersonmanzano.favorites.presentation.adapter.FavoritesAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoritesFragment: Fragment() {

    private val viewModel by viewModels<FavoritesViewModel>()

    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var adapter: FavoritesAdapter

    @Inject
    lateinit var favoritesNavigation: FavoritesNavigation

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupUI()
        setupObservers()
    }

    private fun setupUI() = with(binding) {
        adapter = FavoritesAdapter()
        adapter.onItemClick = { contact ->
            contact.id?.let {
                favoritesNavigation.navigateToDetails(it)
            }
        }
        recyclerView.adapter = adapter
    }

    private fun setupObservers() = with(binding) {
        viewModel.favorites.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                adapter.submitList(it)
                viewSwitcher.showNext()
            } else {
                if (viewSwitcher.displayedChild == 1) {
                    viewSwitcher.showPrevious()
                }
            }
        }
    }
}