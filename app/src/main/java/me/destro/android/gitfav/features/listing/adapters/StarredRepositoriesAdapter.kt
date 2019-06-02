package me.destro.android.gitfav.features.listing.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import me.destro.android.gitfav.R
import me.destro.android.gitfav.databinding.ItemGithubRepositoryBinding
import me.destro.android.gitfav.domain.model.Repository
import me.destro.android.gitfav.features.listing.paging.StarredRepositoryDiffCallback
import java.io.IOException

class StarredRepositoriesAdapter : PagedListAdapter<Repository, StarredRepositoriesAdapter.StarredRepositoryHolder>(StarredRepositoryDiffCallback()) {

    private var onStarredRepositoryClickListener: ((Repository) -> Unit)? = null

    fun setOnStarredRepositoryClickListener(onRepositoryClickListener: (Repository) -> Unit) {
        this.onStarredRepositoryClickListener = onRepositoryClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): StarredRepositoryHolder {
        val root = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_github_repository, viewGroup, false)
        return StarredRepositoryHolder(root)
    }

    override fun onBindViewHolder(viewHolder: StarredRepositoryHolder, i: Int) {
        val item = getItem(i)
        if (item != null)
            viewHolder.bind(item)
    }

    inner class StarredRepositoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding: ItemGithubRepositoryBinding = DataBindingUtil.bind(itemView)!!

        private val fullProjectName: TextView
        private val description: TextView
        private val stargazersCount: TextView
        private val topicsChipGroup: ChipGroup
        private val languagesChipGroup: ChipGroup
        private val userTagsChipGroup: ChipGroup

        init {
            fullProjectName = binding.fullProjectName
            description = binding.description
            stargazersCount = binding.stargazersCount
            topicsChipGroup = binding.topics
            languagesChipGroup = binding.languages
            userTagsChipGroup = binding.userTags
        }

        fun bind(starredRepository: Repository) {
            fullProjectName.text = starredRepository.name

            if (starredRepository.description != null) {
                description.text = starredRepository.description
            }else {
                description.visibility = View.GONE
            }

            stargazersCount.text = starredRepository.starsCount.toString()

            if (starredRepository.topics.isNotEmpty()) {
                buildTopicsChips(starredRepository.topics)
                topicsChipGroup.visibility = View.VISIBLE
            }else {
                topicsChipGroup.visibility = View.GONE
            }

            if (starredRepository.languages.isNotEmpty()) {
                languagesChipGroup.visibility = View.VISIBLE
            }else {
                languagesChipGroup.visibility = View.GONE
            }

            if (starredRepository.userTags.isNotEmpty()) {
                userTagsChipGroup.visibility = View.VISIBLE
            }else {
                userTagsChipGroup.visibility = View.GONE
            }

            if (onStarredRepositoryClickListener != null) {
                binding.root.setOnClickListener { _ -> onStarredRepositoryClickListener?.invoke(starredRepository) }
            }
        }

        private fun buildTopicsChips(topics: Array<String>) {
            // Create new child as needed.
            val childCountBefore = topicsChipGroup.childCount
            for (i in 0 until topics.size - childCountBefore) {
                topicsChipGroup.addView(Chip(topicsChipGroup.context))
            }

            for (i in topics.indices) {
                val topic = topics[i]
                (topicsChipGroup.getChildAt(i) as Chip).text = topic
            }

            // Hide those not-used chips.
            for (i in topics.size until topicsChipGroup.childCount) {
                topicsChipGroup.getChildAt(i).visibility = View.GONE
            }
        }
    }
}