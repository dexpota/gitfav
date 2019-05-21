package me.destro.android.gitfav.features.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import me.destro.android.gitfav.R
import me.destro.android.gitfav.databinding.FragmentRepositoryDetailBinding

class RepositoryDetailFragment : Fragment() {

    private lateinit var viewModel: RepositoryDetailViewModel
    private lateinit var binding: FragmentRepositoryDetailBinding

    private val args : RepositoryDetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_repository_detail, container, false)

        viewModel = ViewModelProviders.of(this).get(RepositoryDetailViewModel::class.java)

        viewModel.getRepository(args.owner, args.repository)
                .subscribe { response ->
                    if (response.isSuccessful) {
                        binding.repository = response.body()
                    }
                }

        return binding.root
    }

}
