package me.destro.android.gitfav.features.login;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.navigation.Navigation;
import io.reactivex.annotations.NonNull;
import me.destro.android.gitfav.R;
import me.destro.android.gitfav.databinding.FragmentGithubHandleBinding;


public class GithubHandleFragment extends Fragment {

    private Button githubHandleContinue;
    private TextView githubHandle;

    private FragmentGithubHandleBinding binding;

    public GithubHandleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_github_handle, container, false);

        githubHandle = binding.githubHandle;
        githubHandleContinue = binding.githubHandleContinue;

        githubHandleContinue.setOnClickListener(view -> {
            String username = githubHandle.getText().toString();

            GithubHandleFragmentDirections.OpenStarredRepositories action = GithubHandleFragmentDirections.openStarredRepositories(username);
            Navigation.findNavController(view).navigate(action);
        });

        return binding.getRoot();
    }

    private void hideSoftKeyBoard(@NonNull Activity activity) {
        Context context = activity;

        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

        if(imm.isAcceptingText()) {
            imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    @Override
    public void onPause() {
        hideSoftKeyBoard(getActivity());
        super.onPause();
    }
}
