package kr.co.lion.application.finalproject_aparttalk.ui.location

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.KakaoMapSdk
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.MapType
import com.kakao.vectormap.MapView
import com.kakao.vectormap.MapViewInfo
import com.kakao.vectormap.camera.CameraUpdateFactory
import com.kakao.vectormap.label.LabelOptions
import com.kakao.vectormap.label.LabelStyle
import com.kakao.vectormap.label.LabelStyles
import kr.co.lion.application.finalproject_aparttalk.BuildConfig
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.ActivityLocationShowBinding
import java.lang.Exception




class LocationShowActivity : AppCompatActivity() {

    lateinit var binding:ActivityLocationShowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationShowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        KakaoMapSdk.init(this@LocationShowActivity, BuildConfig.KAKAO_API_KEY)
        settingToolbar()
        initView()
        showMap()
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.resume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapView.finish()
    }


    //툴바 설정
    private fun settingToolbar(){
        with(binding){
            toolbarLocationShow.apply {
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    finish()
                }
            }
        }
    }

    //화면 구현
    private fun initView(){
        binding.apply {
            //데이터 받아오기
            val name = intent?.getStringExtra("name")
            val category = intent?.getStringExtra("category")
            val address = intent?.getStringExtra("address")
            val number = intent?.getStringExtra("number")
            val distance = intent?.getStringExtra("distance")

            textLocationShowTitle.text = name
            textLocationShowCategory.text = category
            textLocationShowAddress.text = address
            textLocationShowNumber.text = number
            textLocationShowDistance.text = "우리집에서 부터 ${distance}m 떨어져있어요"
        }
    }

    private fun showMap(){
        binding.apply {
            mapView.start(object : MapLifeCycleCallback() {
                override fun onMapDestroy() {

                }

                override fun onMapError(p0: Exception?) {
                    Log.e("test1234", "${p0}")
                }

            }, object : KakaoMapReadyCallback(){

                val x = intent?.getStringExtra("x")
                val y = intent?.getStringExtra("y")

                override fun onMapReady(kakaoMap: KakaoMap) {

                    //Log.d("test1234","${x!!.toDouble()}")

                    // 인증 후 API 가 정상적으로 실행될 때 호출됨
                    val position = LatLng.from(y!!.toDouble(), x!!.toDouble())
                    val cameraUpdate = CameraUpdateFactory.newCenterPosition(position, 15)
                    kakaoMap.moveCamera(cameraUpdate)
                }

                override fun getPosition(): LatLng {
                    // 지도 시작 시 위치 좌표를 설정
                    return LatLng.from(y!!.toDouble(), x!!.toDouble())
                }

                override fun isVisible(): Boolean {
                    // 지도 시작 시 visible 여부를 설정
                    return true
                }

            }
            )
        }
    }
}