package me.destro.android.gitfav.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import me.destro.android.gitfav.github.model.StarredRepository;
import me.destro.android.gitfav.paging.StarredRepositoryFactory;

public class MainViewModel extends ViewModel {
    LiveData<PagedList<StarredRepository>> mStarredRepository;

    public LiveData<PagedList<StarredRepository>> getStarredRepositories(String username) {
        this.mStarredRepository = new LivePagedListBuilder<>(new StarredRepositoryFactory(username), 20)
                .build();
        return this.mStarredRepository;
    }
}
