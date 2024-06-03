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

    suspend fun updateUser(uid: String, updates: Map<String, Any?>) {
        userDataSource.updateUser(uid, updates)
        val user = getUser(uid)
        user?.let {
            val updatedUser = it.copy(
                name = updates["name"] as? String ?: it.name,
                loginType = updates["loginType"] as? String ?: it.loginType,
                birthYear = updates["birthYear"] as? Int ?: it.birthYear,
                birthMonth = updates["birthMonth"] as? Int ?: it.birthMonth,
                birthDay = updates["birthDay"] as? Int ?: it.birthDay,
                gender = updates["gender"] as? String ?: it.gender,
                email = updates["email"] as? String ?: it.email,
                phoneNumber = updates["phoneNumber"] as? String ?: it.phoneNumber,
                agreementCheck1 = updates["agreementCheck1"] as? Boolean ?: it.agreementCheck1,
                agreementCheck2 = updates["agreementCheck2"] as? Boolean ?: it.agreementCheck2,
                agreementCheck3 = updates["agreementCheck3"] as? Boolean ?: it.agreementCheck3,
                apartmentUid = updates["apartmentUid"] as? String ?: it.apartmentUid,
                apartmentDong = updates["apartmentDong"] as? Int ?: it.apartmentDong,
                apartmentHo = updates["apartmentHo"] as? Int ?: it.apartmentHo,
                completeInputUserInfo = updates["completeInputUserInfo"] as? Boolean ?: it.completeInputUserInfo,
                apartCertification = updates["apartCertification"] as? Boolean ?: it.apartCertification,
                state = updates["state"] as? Int ?: it.state
            )
            localUserDataSource.saveUser(updatedUser)
        }
    }

    suspend fun updateUser(user: UserModel) {
        userDataSource.updateUser(user)
        localUserDataSource.saveUser(user)
    }

    suspend fun getApartmentUserList(apartmentUid: String): List<UserModel?> = userDataSource.getApartmentUserList(apartmentUid)

    suspend fun isExistUser(uid: String): Boolean = userDataSource.isExistUser(uid)
}