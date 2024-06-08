package kr.co.lion.application.finalproject_aparttalk.model

data class ServiceModel(
    val serviceTitle: String = "",
    val serviceUserId: String = "",
    val serviceContent: String = "",
    val serviceDate: String = "",
    val serviceIdx: Int = 0,
    val serviceState: Boolean = false,
    val serviceAnsContent: String = ""
)