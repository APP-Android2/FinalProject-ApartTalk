package kr.co.lion.application.finalproject_aparttalk.ui.service

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentServiceBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentSingleServiceBinding
import kr.co.lion.application.finalproject_aparttalk.model.ServiceModel
import kr.co.lion.application.finalproject_aparttalk.util.ServiceFragmentName


class SingleServiceFragment : Fragment() {

    lateinit var fragmentSingleServiceBinding: FragmentSingleServiceBinding
    lateinit var serviceActivity: ServiceActivity
    private val viewModel: ServiceViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentSingleServiceBinding = FragmentSingleServiceBinding.inflate(layoutInflater)
        serviceActivity = activity as ServiceActivity

        settingToolbar()
        setupSaveButton()

        return fragmentSingleServiceBinding.root
    }

    fun settingToolbar() {
        fragmentSingleServiceBinding.apply {
            singleServiceToolbar.apply {
                textViewSingleServiceToolbarTitle.text = "고객센터"
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    serviceActivity.finish()
                }
            }
        }
    }

    private fun setupSaveButton() {
        fragmentSingleServiceBinding.apply {
            singleServiceButtonQ.setOnClickListener {
                val title = singleServiceInputQTitle.text.toString()
                val content = singleServiceInputQContent.text.toString()
                val service = ServiceModel(
                    serviceTitle = title,
                    serviceUserId = "userId",
                    serviceContent = content,
                    serviceDate = "2024-06-08",
                    serviceIdx = 0,
                    serviceState = false,
                    serviceAnsContent = ""
                )
                viewModel.addService(service)
                serviceActivity.replaceFragment(ServiceFragmentName.SERVICE_FRAGMENT, true, true, null)
            }
        }
    }
}