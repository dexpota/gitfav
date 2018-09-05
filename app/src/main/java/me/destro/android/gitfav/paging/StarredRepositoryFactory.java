package me.destro.android.gitfav.paging;

import android.arch.paging.DataSource;

import me.destro.android.gitfav.github.model.StarredRepository;

public class StarredRepositoryFactory extends DataSource.Factory<String, StarredRepository>{

    private String username;

    public StarredRepositoryFactory(String username) {
        this.username = username;
    }

    @Override
    public DataSource<String, StarredRepository> create() {
        return new StarredRepositoryDataSource(username);
    }
}
