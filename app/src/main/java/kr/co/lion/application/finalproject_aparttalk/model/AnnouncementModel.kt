package kr.co.lion.application.finalproject_aparttalk.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class AnnouncementModel(
    val AnnouncementTitle: String,
    val AnnouncementIdx: Int,
    val AnnouncementDate: String,
    val AnnouncementContent: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(AnnouncementTitle)
        parcel.writeInt(AnnouncementIdx)
        parcel.writeString(AnnouncementDate)
        parcel.writeString(AnnouncementContent)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AnnouncementModel> {
        override fun createFromParcel(parcel: Parcel): AnnouncementModel {
            return AnnouncementModel(parcel)
        }

        override fun newArray(size: Int): Array<AnnouncementModel?> {
            return arrayOfNulls(size)
        }
    }
}

