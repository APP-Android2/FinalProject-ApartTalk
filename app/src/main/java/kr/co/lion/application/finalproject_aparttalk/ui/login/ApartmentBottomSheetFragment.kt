package kr.co.lion.application.finalproject_aparttalk.ui.login

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentApartmentBottomSheetBinding

class ApartmentBottomSheetFragment(private val onApartmentSelected: (apartmentName: String, apartmentAddress: String) -> Unit) : BottomSheetDialogFragment() {

    private var _binding: FragmentApartmentBottomSheetBinding? = null
    private val binding get() = _binding!!

    private lateinit var apartments: List<Pair<String, String>>
    private lateinit var adapter: ApartmentAdapter

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener {
            val bottomSheet = dialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
            bottomSheet.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.white))
        }
        return dialog
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentApartmentBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getTempData()
        recyclerViewAdapter()
        searchEditTextListener()
    }

    private fun getTempData(){
        apartments = listOf(
            Pair("한강신도시 반도유보라 2차 아파트", "경기도 김포시 김포한강 11로 276"),
            Pair("송파꿈에그린아파트", "서울시 송파구 위례광장로121(장지동)"),
            Pair("문정래미안", "서울특별시 송파구 문정로 83 (문정동)"),
            Pair("북한산래미안", "서울 은평구 불광동 진흥로 267번[1단지], 256번지[2단지]"),
            Pair("대구혁신도시서한이다음", "대구광역시 동구 이노밸리로 29길 56(각산동 1029)"),
            Pair("e편한세상범어아파트", "대구광역시 수성구 들안로 78길 45 (범어동)"),
            Pair("대신센트럴자이", "대구시 중구 달구벌대로1955(대신동)"),
            Pair("대연힐스테이트푸르지오", "부산광역시 남구 수영로 345 (대연동)"),
            Pair("구포동원로얄듀크비스타", "부산시 북구 낙동대로 1738번길 10 (구포동 1296)"),
            Pair("부산센텀푸르지오", "[47571] 부산시 연제구 고분로 280 (연산동 2307)"),
            Pair("해운대힐스테이트위브", "부산광역시 해운대구 좌동순환로 433번길 30(중동)"),
            Pair("동래럭키아파트", "부산광역시 동래구 충렬대로 107번길 54(구,온천2동707번지)"),
            Pair("금강엑슬루타워", "대전광역시 대덕구 대덕대로 1555 (석봉동 770)"),
            Pair("죽동예미지아파트", "대전광역시 유성구 죽동로 321"),
        )
    }

    private fun recyclerViewAdapter(){
        adapter = ApartmentAdapter(apartments) { apartmentName, apartmentAddress ->
            onApartmentSelected(apartmentName, apartmentAddress)
            SystemClock.sleep(200)
            dismiss()
        }
        binding.apartmentRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.apartmentRecyclerView.adapter = adapter
    }

    private fun searchEditTextListener(){
        binding.searchEditText.addTextChangedListener {
            val query = it.toString()
            filterApartments(query)
        }

        binding.searchEditText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)
                true
            } else {
                false
            }
        }
    }

    private fun filterApartments(query: String) {
        val filteredApartments = apartments.filter {
            it.first.contains(query, ignoreCase = true) || it.second.contains(query, ignoreCase = true)
        }
        if (filteredApartments.isEmpty()) {
            binding.noResults.visibility = View.VISIBLE
            binding.apartmentRecyclerView.visibility = View.GONE
        } else {
            binding.noResults.visibility = View.GONE
            binding.apartmentRecyclerView.visibility = View.VISIBLE
            adapter.updateData(filteredApartments)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}