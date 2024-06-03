package kr.co.lion.application.finalproject_aparttalk.util

// 게시판 종류
enum class PostType(var str: String, var number: Int) {
    TYPE_NOTIFICATION("공지", 0),
    TYPE_QUESTION("질문", 1),
    TYPE_TRADE("거래", 2),
    TYPE_ETC("기타", 3),
}

// 게시판 상태
enum class PostState(var str: String, var number: Int) {
    POST_STATE_NORMAL("정상", 1),
    POST_STATE_REMOVE("삭제", 2),
    POST_STATE_MODIFY("수정", 3),
}