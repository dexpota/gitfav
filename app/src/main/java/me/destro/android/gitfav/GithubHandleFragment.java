package me.destro.android.gitfav;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;


public class GithubHandleFragment extends Fragment {

    @BindView(R.id.github_handle_continue)
    Button githubHandleContinue;

    @BindView(R.id.github_handle)
    TextView githubHandle;

    public GithubHandleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_github_handle, container, false);
        ButterKnife.bind(this, root);

        githubHandleContinue.setOnClickListener(view -> {
            String username = githubHandle.getText().toString();

            GithubHandleFragmentDirections.OpenStarredRepositories action = GithubHandleFragmentDirections.openStarredRepositories(username);
            Navigation.findNavController(view).navigate(action);
        });

        return root;
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
