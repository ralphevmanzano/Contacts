package com.codev.recruitment.ralphemersonmanzano.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.codev.recruitment.ralphemersonmanzano.home.databinding.FragmentHomeBinding
import com.codev.recruitment.ralphemersonmanzano.home.navigation.HomeNavigation
import com.codev.recruitment.ralphemersonmanzano.home.presentation.adapter.ContactsAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment: Fragment() {

    private val viewModel by viewModels<HomeViewModel>()

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: ContactsAdapter

    @Inject
    lateinit var homeNavigator: HomeNavigation

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupUI()
        setupObservers()
    }

    private fun setupUI() = with(binding) {
        adapter = ContactsAdapter()
        adapter.onItemClick = {contact ->
            contact.id?.let {
                homeNavigator.navigateToDetails(it)
            }
        }
        recyclerView.adapter = adapter
        fab.setOnClickListener {
            homeNavigator.navigateToForm()
        }
    }


    private fun setupObservers() = with(binding) {
        viewModel.contacts.observe(viewLifecycleOwner) {
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