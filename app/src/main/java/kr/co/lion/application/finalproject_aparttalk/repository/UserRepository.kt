package kr.co.lion.application.finalproject_aparttalk.repository

import kr.co.lion.application.finalproject_aparttalk.db.local.LocalUserDataSource
import kr.co.lion.application.finalproject_aparttalk.db.remote.UserDataSource
import kr.co.lion.application.finalproject_aparttalk.model.UserModel

class UserRepository(
    private val userDataSource: UserDataSource,
    private val localUserDataSource: LocalUserDataSource
) {
    suspend fun createUser(user: UserModel) {
        userDataSource.createUser(user)
        localUserDataSource.saveUser(user)
    }

    suspend fun getUser(uid: String): UserModel? {
        return localUserDataSource.getUser() ?: userDataSource.getUser(uid)?.also { user ->
            localUserDataSource.saveUser(user)
        }
    }

    fun clearUser(){
        localUserDataSource.clearUser()
    }

    suspend fun updateUser(uid: String, updates: Map<String, Any?>) {
        userDataSource.updateUser(uid, updates)
        val user = getUser(uid)
        user?.let {
            val updatedUser = it.copyFromMap(updates)
            localUserDataSource.saveUser(updatedUser)
        }
    }

    suspend fun updateUser(user: UserModel) {
        userDataSource.updateUser(user)
        localUserDataSource.saveUser(user)
    }

    suspend fun getApartmentUserList(apartmentUid: String): List<UserModel?> = userDataSource.getApartmentUserList(apartmentUid)

    suspend fun isExistUser(uid: String): Boolean = userDataSource.isExistUser(uid)

    suspend fun getUserSequence(): Int? = userDataSource.getUserSequence()

    suspend fun incrementUserSequence() = userDataSource.incrementUserSequence()

    private fun UserModel.copyFromMap(updates: Map<String, Any?>): UserModel {
        return this.copy(
            name = updates["name"] as? String ?: this.name,
            loginType = updates["loginType"] as? String ?: this.loginType,
            birthYear = updates["birthYear"] as? Int ?: this.birthYear,
            birthMonth = updates["birthMonth"] as? Int ?: this.birthMonth,
            birthDay = updates["birthDay"] as? Int ?: this.birthDay,
            gender = updates["gender"] as? String ?: this.gender,
            email = updates["email"] as? String ?: this.email,
            phoneNumber = updates["phoneNumber"] as? String ?: this.phoneNumber,
            agreementCheck1 = updates["agreementCheck1"] as? Boolean ?: this.agreementCheck1,
            agreementCheck2 = updates["agreementCheck2"] as? Boolean ?: this.agreementCheck2,
            agreementCheck3 = updates["agreementCheck3"] as? Boolean ?: this.agreementCheck3,
            apartmentUid = updates["apartmentUid"] as? String ?: this.apartmentUid,
            apartmentDong = updates["apartmentDong"] as? Int ?: this.apartmentDong,
            apartmentHo = updates["apartmentHo"] as? Int ?: this.apartmentHo,
            completeInputUserInfo = updates["completeInputUserInfo"] as? Boolean ?: this.completeInputUserInfo,
            apartCertification = updates["apartCertification"] as? Boolean ?: this.apartCertification,
            state = updates["state"] as? Int ?: this.state
        )
    }
}