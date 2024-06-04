package kr.co.lion.application.finalproject_aparttalk.model

data class CommentData(
    var commentIdx:Int,
    var commentUserIdx:Int,
    var commentPostIdx:Int,
    var commentContent:String,
    var commentCnt:Int,
    var commentAddDate:String,
    var commentModifyDate:String,
    var commentState:Int
) {
    constructor(): this(0,0,0,"",0,"","",0)
}
