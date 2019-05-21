package me.destro.android.gitfav.features.listing;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.paging.PagedList;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import me.destro.android.gitfav.R;
import me.destro.android.gitfav.databinding.FragmentStarredRepositoriesBinding;
import me.destro.android.gitfav.features.listing.adapters.StarredRepositoriesAdapter;
import me.destro.android.libraries.github.model.StarredRepository;


public class StarredRepositoriesFragment extends Fragment {

    private FragmentStarredRepositoriesBinding binding;

    private RecyclerView rvStarredRepositories;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_starred_repositories, container, false);

        rvStarredRepositories = binding.starredRepositories;

        String username = StarredRepositoriesFragmentArgs.fromBundle(getArguments()).getUsername();

        StarredRepositoriesAdapter mAdapter = new StarredRepositoriesAdapter();
        mAdapter.setOnStarredRepositoryClickListener((starredRepository) -> {
            StarredRepositoriesFragmentDirections.ActionStarredRepositoriesFragmentToRepositoryDetailFragment
                    action = StarredRepositoriesFragmentDirections.actionStarredRepositoriesFragmentToRepositoryDetailFragment(starredRepository.name, starredRepository.owner.getLogin());

            NavController navigation = Navigation.findNavController(binding.getRoot());
            navigation.navigate(action);
        });
        rvStarredRepositories.setAdapter(mAdapter);
        rvStarredRepositories.setLayoutManager(new LinearLayoutManager(getContext()));

        StarredRepositoriesViewModel viewModel = ViewModelProviders.of(this).get(StarredRepositoriesViewModel.class);
        viewModel.getStarredRepositories(username).observe(this, new Observer<PagedList<StarredRepository>>() {
            @Override
            public void onChanged(@Nullable PagedList<StarredRepository> starredRepositories) {
                mAdapter.submitList(starredRepositories);
            }
        });

        return binding.getRoot();
    }
}
