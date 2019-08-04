package me.destro.android.gitfav.features.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import me.destro.android.gitfav.R
import me.destro.android.gitfav.databinding.FragmentRepositoryDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoryDetailFragment : Fragment() {

    private lateinit var binding: FragmentRepositoryDetailBinding

    private val viewModel by viewModel<RepositoryDetailViewModel>()

    private val args: RepositoryDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_repository_detail, container, false)

        viewModel.getRepository(args.owner, args.repository)
                .subscribe { result ->

                    result.fold({ project ->
                        binding.repository = project
                    }, {
                        Log.e("Error", "Error")
                    })
                }

        return binding.root
    }
}
