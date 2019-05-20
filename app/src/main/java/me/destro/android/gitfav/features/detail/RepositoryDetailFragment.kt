package me.destro.android.gitfav.features.detail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import me.destro.android.gitfav.R
import me.destro.android.gitfav.databinding.FragmentRepositoryDetailBinding

class RepositoryDetailFragment : Fragment() {

    private lateinit var binding: FragmentRepositoryDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_repository_detail, container, false)

        return binding.root
    }


}
