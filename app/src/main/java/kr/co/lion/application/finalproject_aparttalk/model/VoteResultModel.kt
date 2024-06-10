package kr.co.lion.application.finalproject_aparttalk.model

data class VoteResultModel(
    val electionName: String = "",
    val electionDate: String = "",
    val results: String = "",
    val userVotes: Map<String, Boolean> = emptyMap(),
    val title: String = "",
    val checkBox1Text: String = "",
    val checkBox2Text: String = "",
    val checkBox3Text: String = "",
    val checkBox1Desc: String = "",
    val checkBox2Desc: String = "",
    val checkBox3Desc: String = ""
)
