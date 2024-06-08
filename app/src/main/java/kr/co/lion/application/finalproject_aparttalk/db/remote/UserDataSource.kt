package kr.co.lion.application.finalproject_aparttalk.db.remote

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await
import kr.co.lion.application.finalproject_aparttalk.model.UserModel

class UserDataSource {

    private val db: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }
    private val userCollection = db.collection("Users")
    private val userSequenceDocRef = db.collection("Sequence").document("UserSequence")

    suspend fun createUser(user: UserModel) {
        userCollection.document(user.uid).set(user).await()
    }

    suspend fun getUser(uid: String): UserModel? {
        val snapshot = userCollection.document(uid).get().await()
        val user = snapshot.toObject<UserModel>()
        return user
    }

    suspend fun getApartmentUserList(apartmentUid: String): List<UserModel?> {
        val snapshot = userCollection.whereEqualTo("apartmentUid", apartmentUid).get().await()
        val userList = snapshot.documents.mapNotNull { it.toObject<UserModel>() }
        return userList
    }

    suspend fun updateUser(user: UserModel) {
        userCollection.document(user.uid).set(user).await()
    }

    suspend fun updateUser(uid: String, updates: Map<String, Any?>) {
        userCollection.document(uid).update(updates).await()
    }

    suspend fun isExistUser(uid: String): Boolean {
        val snapshot = userCollection.document(uid).get().await()
        val exists = snapshot.exists()
        return exists
    }

    suspend fun getUserSequence(): Int? {
        val snapshot = userSequenceDocRef.get().await()
        return snapshot.getLong("count")?.toInt()
    }

    suspend fun incrementUserSequence() {
        userSequenceDocRef.update("count", FieldValue.increment(1)).await()
    }
}