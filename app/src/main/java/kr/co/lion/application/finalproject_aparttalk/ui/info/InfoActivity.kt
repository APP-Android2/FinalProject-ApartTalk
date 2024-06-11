package kr.co.lion.application.finalproject_aparttalk.ui.info

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.db.local.LocalUserDataSource
import kr.co.lion.application.finalproject_aparttalk.db.remote.UserDataSource
import kr.co.lion.application.finalproject_aparttalk.repository.UserRepository
import kr.co.lion.application.finalproject_aparttalk.util.InfoFragmentName

class InfoActivity : AppCompatActivity() {

    lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        // ViewModel 초기화
        val userRepository = UserRepository(UserDataSource(), LocalUserDataSource(applicationContext))
        userViewModel = ViewModelProvider(this, UserViewModelFactory(userRepository)).get(UserViewModel::class.java)

        // 초기 프래그먼트 설정
        if (savedInstanceState == null) {
            replaceFragment(InfoFragmentName.INFO_FRAGMENT, false, false, null)
        }
        userViewModel.isUserUpdated.observe(this) { isUserUpdated ->
            if (isUserUpdated) {
                userViewModel.loadUser()
                Log.d("test1111", "${userViewModel.user.value}")
            }
        }
    }

    fun replaceFragment(
        fragmentName: InfoFragmentName,
        addToBackStack: Boolean,
        clearBackStack: Boolean,
        args: Bundle?
    ) {
        val fragment: Fragment = when (fragmentName) {
            InfoFragmentName.INFO_FRAGMENT -> InfoFragment()
            InfoFragmentName.EDIT_USER_INFO_FRAGMENT -> EditUserInfoFragment()
        }

        if (args != null) {
            fragment.arguments = args
        }

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.infoFragmentContainerView, fragment)

        if (addToBackStack) {
            transaction.addToBackStack(null)
        }

        if (clearBackStack) {
            supportFragmentManager.popBackStack(null, androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        transaction.commit()
    }
}