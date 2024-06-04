package kr.co.lion.application.finalproject_aparttalk.model

data class UserModel(
    val uid: String = "",             // 고유번호(식별자)
    val idx: Int = 0,                 // 유저 번호
    var name: String = "",            // 유저 이름
    var loginType: String = "",       // 로그인 타입
    var birthYear: Int? = null,       // 생년월일
    var birthMonth: Int? = null,
    var birthDay: Int? = null,
    var gender: String = "",          // 성별
    var email: String = "",           // 이메일
    var phoneNumber: String = "",     // 휴대폰 번호
    var agreementCheck1: Boolean = false, // 서비스 이용 약관 동의 여부
    var agreementCheck2: Boolean = false, // 개인정보 수집 동의 여부
    var agreementCheck3: Boolean = false, // 마케팅 정보 수신 동의 여부
    var apartmentUid: String = "",    // 아파트 정보(고유번호)
    var apartmentDong: Int? = null,   // 아파트 동호수
    var apartmentHo: Int? = null,
    var completeInputUserInfo: Boolean = false, // 유저 정보 입력 여부
    var apartCertification: Boolean = false, // 아파트 인증 여부
    var state: Int = UserState.ACTIVE.num  // 유저 상태
)

enum class UserState(val num: Int, val str: String){
    ACTIVE(0, "활성화"),
    INACTIVE(1, "비활성화"),
}