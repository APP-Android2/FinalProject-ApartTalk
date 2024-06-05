package kr.co.lion.application.finalproject_aparttalk.ui.location

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.common.collect.MapMaker
import com.kakao.vectormap.CurveType
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.KakaoMapSdk
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.MapType
import com.kakao.vectormap.MapView
import com.kakao.vectormap.MapViewInfo
import com.kakao.vectormap.RoadViewRequest.Marker
import com.kakao.vectormap.camera.CameraUpdateFactory
import com.kakao.vectormap.label.Label
import com.kakao.vectormap.label.LabelLayer
import com.kakao.vectormap.label.LabelManager
import com.kakao.vectormap.label.LabelOptions
import com.kakao.vectormap.label.LabelStyle
import com.kakao.vectormap.label.LabelStyles
import com.kakao.vectormap.label.LabelTextStyle
import com.kakao.vectormap.route.RouteLineOptions
import com.kakao.vectormap.route.RouteLineSegment
import com.kakao.vectormap.route.RouteLineStyle
import com.kakao.vectormap.route.RouteLineStyles
import com.kakao.vectormap.route.RouteLineStylesSet
import com.kakao.vectormap.shape.MapPoints
import kr.co.lion.application.finalproject_aparttalk.App
import kr.co.lion.application.finalproject_aparttalk.BuildConfig
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.ActivityLocationShowBinding
import java.lang.Exception




class LocationShowActivity : AppCompatActivity() {

    lateinit var binding: ActivityLocationShowBinding
    lateinit var kakaoMap: KakaoMap

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

    // 툴바 설정
    private fun settingToolbar() {
        with(binding) {
            toolbarLocationShow.apply {
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    finish()
                }
            }
        }
    }

    // 화면 구현
    private fun initView() {
        binding.apply {
            // 데이터 받아오기
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

    private fun showMap() {
        binding.mapView.start(object : MapLifeCycleCallback() {
            override fun onMapDestroy() {}

            override fun onMapError(p0: Exception?) {
                Log.e("test1234", "${p0}")
            }

        }, object : KakaoMapReadyCallback() {

            override fun onMapReady(kMap: KakaoMap) {
                kakaoMap = kMap
                setupMap()
            }

        })
    }

    private fun setupMap() {
        // 인증 후 API 가 정상적으로 실행될 때 호출됨
        // 좌표값은 추후 수정 예정
        val y = App.prefs.getLatitude("").toDoubleOrNull()
        val x = App.prefs.getLongitude("").toDoubleOrNull()

        val position = LatLng.from(y?:0.0, x?:0.0)
        val cameraUpdate = CameraUpdateFactory.newCenterPosition(position, 15)
        kakaoMap.moveCamera(cameraUpdate)


        // 라벨 추가
        settingLabel()

        //현재 위치 라벨 추가
        myLocationLabel()

    }

    //주변 위치
    private fun settingLabel() {
        val x = intent?.getStringExtra("x")?.toDoubleOrNull()
        val y = intent?.getStringExtra("y")?.toDoubleOrNull()
        val name = intent?.getStringExtra("name")

        //주변 위치
        val styles = kakaoMap.labelManager?.addLabelStyles(LabelStyles.from(LabelStyle.from(R.drawable.location_map_label)
            .setAnchorPoint(0.5f, 0.5f)
            .setTextStyles(25, Color.BLACK)))


        val options = LabelOptions.from(LatLng.from(y?:0.0, x?:0.0))
            .setStyles(styles)

        //Log.d("test1234", "${x},${y}")

        val labelManager = kakaoMap.labelManager
        val layer = labelManager?.layer

        //Log.d("test1234", "${layer!!}")

        if (layer != null) {
            // 레이어가 null이 아니라면
            val label = layer.addLabel(options)
            //label.show()
            label.changeText("${name}")

            Log.d("test1234", "${label}")
        } else {
            // 레이어가 null이면
        }

    }

    //내 위치
    private fun myLocationLabel(){

        val styles = kakaoMap.labelManager?.addLabelStyles(LabelStyles.from(LabelStyle.from(R.drawable.icon_my_location)
            .setAnchorPoint(0.5f, 0.5f)
            .setTextStyles(25, Color.BLACK)))


        val y = App.prefs.getLatitude("").toDoubleOrNull()
        val x = App.prefs.getLongitude("").toDoubleOrNull()
        val options = LabelOptions.from(LatLng.from(y?:0.0, x?:0.0))
            .setStyles(styles)


        //Log.d("test1234", "${x},${y}")

        val labelManager = kakaoMap.labelManager
        val layer = labelManager?.layer

        //Log.d("test1234", "${layer!!}")

        if (layer != null) {
            // 레이어가 null이 아니라면
            val label = layer.addLabel(options)
            label.changeText("내 위치")
            //label.show()

            Log.d("test1234", "${label}")
        } else {
            // 레이어가 null이면
        }
    }
}
