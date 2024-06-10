package kr.co.lion.application.finalproject_aparttalk.ui.login

import android.animation.ObjectAnimator
import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.ActivitySignUpBinding
import kr.co.lion.application.finalproject_aparttalk.db.local.LocalApartmentDataSource
import kr.co.lion.application.finalproject_aparttalk.db.local.LocalUserDataSource
import kr.co.lion.application.finalproject_aparttalk.db.remote.ApartmentDataSource
import kr.co.lion.application.finalproject_aparttalk.db.remote.UserDataSource
import kr.co.lion.application.finalproject_aparttalk.repository.ApartmentRepository
import kr.co.lion.application.finalproject_aparttalk.repository.UserRepository
import kr.co.lion.application.finalproject_aparttalk.ui.login.viewmodel.SignUpViewModel

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    val userRepository: UserRepository by lazy { UserRepository(UserDataSource(), LocalUserDataSource(applicationContext)) }
    val apartmentRepository: ApartmentRepository by lazy { ApartmentRepository(ApartmentDataSource(), LocalApartmentDataSource(applicationContext)) }
    private val viewModel: SignUpViewModel by viewModels { SignUpViewModelFactory(userRepository, apartmentRepository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loading()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.signup_fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        setSupportActionBar(binding.signupToolbar)

        binding.signupToolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            updateProgressBar(destination.id)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.signup_fragmentContainerView) as NavHostFragment
        return navHostFragment.navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun loading(){
        viewModel.isLoading.observe(this) { isLoading ->
            binding.signupProgressBar2.visibility = if (isLoading) View.VISIBLE else View.INVISIBLE
        }
    }

    private fun updateProgressBar(destinationId: Int) {
        val progress = when (destinationId) {
            R.id.signUp1Fragment -> 25
            R.id.signUp2Fragment -> 50
            R.id.signUp3Fragment -> 75
            R.id.signUp4Fragment -> 100
            else -> 0
        }

        val animator = ObjectAnimator.ofInt(binding.signupProgressBar, "progress", binding.signupProgressBar.progress, progress)
        animator.duration = 600 // 애니메이션 지속 시간
        animator.start()
    }

    fun setResultAndFinish() {
        setResult(Activity.RESULT_OK)
    }
}