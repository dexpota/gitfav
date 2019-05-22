package me.destro.android.gitfav.features.listing.paging;

import androidx.paging.DataSource;

import me.destro.android.libraries.github.GithubService;
import me.destro.android.libraries.github.model.StarredRepository;

public class StarredRepositoryFactory extends DataSource.Factory<String, StarredRepository>{

    private String username;
    private GithubService githubService;

    public StarredRepositoryFactory(String username, GithubService githubService) {
        this.username = username;
        this.githubService = githubService;
    }

    @Override
    public DataSource<String, StarredRepository> create() {
        return new StarredRepositoryDataSource(username, githubService);
    }
}
