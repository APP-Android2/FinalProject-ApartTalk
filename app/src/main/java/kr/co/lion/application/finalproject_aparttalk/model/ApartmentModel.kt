package kr.co.lion.application.finalproject_aparttalk.model

data class ApartmentModel(
    val uid: String = "",                        // 고유번호(식별자)
    val idx: Int = 0,                            // 순서
    val name: String = "",                       // 아파트 이름
    val address: String = "",                    // 아파트 주소
    val latitude: String = "",                   // 위도
    val longitude: String = "",                  // 경도
    val totalHouseholds: Int = 0,                // 총 세대수
    val totalCarParked: Int = 0,                 // 주차대수
    val sizeOfComplex: String = "",              // 단지규모
    val moveIn: String = "",                     // 입주일
    val managementOfficePhoneNumber: String = "" // 관리사무소 전화번호
)