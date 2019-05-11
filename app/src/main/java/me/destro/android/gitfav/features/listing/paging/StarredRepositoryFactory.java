package me.destro.android.gitfav.features.listing.paging;

import androidx.paging.DataSource;

import me.destro.android.libraries.github.model.StarredRepository;

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
