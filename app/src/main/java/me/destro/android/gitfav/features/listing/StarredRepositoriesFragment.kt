package me.destro.android.gitfav.features.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import me.destro.android.gitfav.R
import me.destro.android.gitfav.databinding.FragmentStarredRepositoriesBinding
import me.destro.android.gitfav.domain.model.Repository
import me.destro.android.gitfav.features.listing.adapters.StarredRepositoriesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class StarredRepositoriesFragment : Fragment() {

    private lateinit var binding: FragmentStarredRepositoriesBinding

    private val viewModel by viewModel<StarredRepositoriesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_starred_repositories, container, false)

        val rvStarredRepositories = binding.starredRepositories

        val username = StarredRepositoriesFragmentArgs.fromBundle(arguments!!).username

        val mAdapter = StarredRepositoriesAdapter()
        mAdapter.setOnStarredRepositoryClickListener { repository ->
            val action = StarredRepositoriesFragmentDirections.actionStarredRepositoriesFragmentToRepositoryDetailFragment(repository.name, repository.owner)

            val navigation = Navigation.findNavController(binding.root)
            navigation.navigate(action)
        }

        rvStarredRepositories.adapter = mAdapter
        rvStarredRepositories.layoutManager = LinearLayoutManager(context)

        viewModel.getStarredRepositories(username).observe(this, Observer<PagedList<Repository>> { starredRepositories -> mAdapter.submitList(starredRepositories) })

        return binding!!.root
    }
}
