package kr.co.lion.application.finalproject_aparttalk.model

data class PostData (
    var postId: String,
    var postIdx: Int,
    var postUserId: String,
    var postApartId: String,
    var postType: String,
    var postTitle: String,
    var postContent: String,
    var postLikeCnt: Int,
    var postCommentCnt: Int,
    var postAddDate: String,
    var postModifyDate: String,
    var postImages: MutableList<String>?,
    var postState: Int
) {
    constructor(): this("",0, "", "", "", "", "",
        0, 0, "", "", mutableListOf(), 0)
}