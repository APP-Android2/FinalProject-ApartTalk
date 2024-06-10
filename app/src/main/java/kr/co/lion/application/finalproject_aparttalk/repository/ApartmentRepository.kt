package kr.co.lion.application.finalproject_aparttalk.repository

import kr.co.lion.application.finalproject_aparttalk.db.local.LocalApartmentDataSource
import kr.co.lion.application.finalproject_aparttalk.db.remote.ApartmentDataSource
import kr.co.lion.application.finalproject_aparttalk.model.ApartmentModel

class ApartmentRepository(
    private val apartmentDataSource: ApartmentDataSource,
    private val localApartmentDataSource: LocalApartmentDataSource
) {

    fun saveApartment(apartment: ApartmentModel) {
        localApartmentDataSource.saveApartment(apartment)
    }

    fun clearApartment(){
        localApartmentDataSource.clearApartment()
    }

    suspend fun getApartment(uid: String): ApartmentModel? {
        return localApartmentDataSource.getApartment() ?: apartmentDataSource.getApartment(uid)?.also { apartment ->
            localApartmentDataSource.saveApartment(apartment)
        }
    }

    suspend fun getApartmentList(): List<ApartmentModel> = apartmentDataSource.getApartmentList()
}