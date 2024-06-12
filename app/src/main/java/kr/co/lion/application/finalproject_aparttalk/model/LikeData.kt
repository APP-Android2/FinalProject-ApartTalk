package kr.co.lion.application.finalproject_aparttalk.model

data class LikeData(
    var likeId: String,
    var likePostId: String,
    var likeUserId: String,
) {
    constructor(): this("", "", "")
}