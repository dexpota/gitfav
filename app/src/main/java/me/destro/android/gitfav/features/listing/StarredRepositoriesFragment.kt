package me.destro.android.gitfav.features.listing

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.paging.PagedList
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.destro.android.gitfav.R
import me.destro.android.gitfav.databinding.FragmentStarredRepositoriesBinding
import me.destro.android.gitfav.features.listing.adapters.StarredRepositoriesAdapter
import me.destro.android.libraries.github.model.StarredRepository
import org.koin.androidx.viewmodel.ext.android.viewModel


class StarredRepositoriesFragment : Fragment() {

    private lateinit var binding: FragmentStarredRepositoriesBinding

    private val viewModel by viewModel<StarredRepositoriesViewModel>()



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_starred_repositories, container, false)

        val rvStarredRepositories = binding.starredRepositories

        val username = StarredRepositoriesFragmentArgs.fromBundle(arguments!!).username

        val mAdapter = StarredRepositoriesAdapter()
        mAdapter.setOnStarredRepositoryClickListener { starredRepository ->
            val action = StarredRepositoriesFragmentDirections.actionStarredRepositoriesFragmentToRepositoryDetailFragment(starredRepository.name, starredRepository.owner.login)

            val navigation = Navigation.findNavController(binding.root)
            navigation.navigate(action)
        }
        rvStarredRepositories.adapter = mAdapter
        rvStarredRepositories.layoutManager = LinearLayoutManager(context)

        viewModel.getStarredRepositories(username).observe(this, Observer<PagedList<StarredRepository>> { starredRepositories -> mAdapter.submitList(starredRepositories) })

        return binding!!.root
    }
}
