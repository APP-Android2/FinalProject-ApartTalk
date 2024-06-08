package kr.co.lion.application.finalproject_aparttalk.model

data class OperationInfoModel (

    // 운영정보_인덱스
    val OperationInfoIdx: Int = 0,
    // 운영정보_종류
    val OperationInfoType: String = "",
    // 운영정보_작성자
    val OperationInfoWriter: String = "",
    // 운영정보_제목
    val OperationInfoSubject: String = "",
    // 운영정보_내용
    val OperationInfoContent: String = "",
    // 운영정보_작성날짜
    val OperationInfoDate: String = ""
)