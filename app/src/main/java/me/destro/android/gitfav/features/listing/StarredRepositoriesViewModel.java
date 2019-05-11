package me.destro.android.gitfav.features.listing;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import me.destro.android.libraries.github.model.StarredRepository;
import me.destro.android.gitfav.features.listing.paging.StarredRepositoryFactory;

public class StarredRepositoriesViewModel extends ViewModel {
    LiveData<PagedList<StarredRepository>> mStarredRepository;

    public LiveData<PagedList<StarredRepository>> getStarredRepositories(String username) {
        this.mStarredRepository = new LivePagedListBuilder<>(new StarredRepositoryFactory(username), 20)
                .build();
        return this.mStarredRepository;
    }
}
