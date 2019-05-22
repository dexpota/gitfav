package me.destro.android.gitfav.features.login


import android.app.Activity
import android.content.Context
import android.os.Bundle

import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView

import androidx.navigation.Navigation
import io.reactivex.annotations.NonNull
import me.destro.android.gitfav.R
import me.destro.android.gitfav.databinding.FragmentGithubHandleBinding


class GithubHandleFragment : Fragment() {

    private lateinit var binding: FragmentGithubHandleBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_github_handle, container, false)

        binding.githubHandleContinue.setOnClickListener { view ->
            val username = binding.githubHandle.text.toString()

            val action = GithubHandleFragmentDirections.openStarredRepositories(username)
            Navigation.findNavController(view).navigate(action)
        }

        return binding.root
    }

    private fun hideSoftKeyBoard(@NonNull activity: Activity?) {

        val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        if (imm.isAcceptingText) {
            imm.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
        }
    }

    override fun onPause() {
        hideSoftKeyBoard(activity)
        super.onPause()
    }
}
