package me.destro.android.gitfav.paging;

import android.arch.paging.DataSource;

import me.destro.android.gitfav.github.model.StarredRepository;

public class StarredRepositoryFactory extends DataSource.Factory<String, StarredRepository>{

    @Override
    public DataSource<String, StarredRepository> create() {
        return new StarredRepositoryDataSource();
    }
}
