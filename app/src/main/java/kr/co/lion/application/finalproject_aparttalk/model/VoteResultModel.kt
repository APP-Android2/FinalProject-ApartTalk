package kr.co.lion.application.finalproject_aparttalk.model

data class VoteResultModel(
    val electionName: String = "",
    val electionDate: String = "",
    val results: String = "",
    val userVotes: Map<String, Boolean> = emptyMap()
)
