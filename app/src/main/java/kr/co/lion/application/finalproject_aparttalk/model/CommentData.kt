package kr.co.lion.application.finalproject_aparttalk.model

data class CommentData(
    var commentId: String,
    var commentPostId: String,
    var commentIdx:Int,
    var commentUserIdx:Int,
    var commentUserId: String,
    var commentPostIdx:Int,
    var commentContent:String,
    var commentCnt:Int,
    var commentAddDate:String,
    var commentModifyDate:String,
    var commentState:Int
) {
    constructor(): this("", "",0,0, "",0,"",0,"","",0)
}
