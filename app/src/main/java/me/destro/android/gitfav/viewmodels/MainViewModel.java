package me.destro.android.gitfav.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.arch.paging.RxPagedListBuilder;
import android.support.annotation.NonNull;

import me.destro.android.gitfav.github.model.StarredRepository;
import me.destro.android.gitfav.paging.StarredRepositoryFactory;

public class MainViewModel extends ViewModel {

    public LiveData<PagedList<StarredRepository>> mStarredRepository;

    public MainViewModel() {
        this.mStarredRepository = new LivePagedListBuilder<>(new StarredRepositoryFactory(), 20)
                .build();
    }
}
