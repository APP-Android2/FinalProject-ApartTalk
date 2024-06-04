package kr.co.lion.application.finalproject_aparttalk.model

data class Survey(
    val surveyTitle: String = "",
    val surveyContent: String = "",
    val surveyDate: String = "",
    val surveyIdx: Int = 0,
    val surveyState: Boolean = false,
    val surveyCheck1: Boolean = false,
    val surveyCheck2: Boolean = false,
    val surveyCheck3: Boolean = false,
    val surveyCheck1Text: String = "",
    val surveyCheck2Text: String = "",
    val surveyCheck3Text: String = "",
    val surveyCheck1Count: Int = 0,
    val surveyCheck2Count: Int = 0,
    val surveyCheck3Count: Int = 0
) {
    fun toMap(): Map<String, Any> {
        return mapOf(
            "surveyTitle" to surveyTitle,
            "surveyContent" to surveyContent,
            "surveyDate" to surveyDate,
            "surveyIdx" to surveyIdx,
            "surveyState" to surveyState,
            "surveyCheck1" to surveyCheck1,
            "surveyCheck2" to surveyCheck2,
            "surveyCheck3" to surveyCheck3
        )
    }
}
