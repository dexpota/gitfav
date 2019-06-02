package me.destro.android.gitfav.domain.model

data class Repository(
        val name: String,
        val owner: String,
        val description: String?,
        val starsCount: Int,
        val userTags: Array<String>,
        val topics: Array<String>,
        val languages: Array<String>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Repository

        if (name != other.name) return false
        if (owner != other.owner) return false
        if (description != other.description) return false
        if (starsCount != other.starsCount) return false
        if (!userTags.contentEquals(other.userTags)) return false
        if (!topics.contentEquals(other.topics)) return false
        if (!languages.contentEquals(other.languages)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + owner.hashCode()
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + starsCount
        result = 31 * result + userTags.contentHashCode()
        result = 31 * result + topics.contentHashCode()
        result = 31 * result + languages.contentHashCode()
        return result
    }
}