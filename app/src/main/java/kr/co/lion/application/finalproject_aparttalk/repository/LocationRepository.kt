package kr.co.lion.application.finalproject_aparttalk.repository

import kr.co.lion.application.finalproject_aparttalk.db.remote.LocationDataSource

class LocationRepository() {

    private val locationDataSource = LocationDataSource()

    suspend fun getSearchFacility(categoryGroupCode: String, x: String, y: String, radius: Int) =
        locationDataSource.getSearchKeyword(categoryGroupCode, x, y, radius)

}