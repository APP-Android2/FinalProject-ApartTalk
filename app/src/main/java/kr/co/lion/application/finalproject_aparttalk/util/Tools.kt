package kr.co.lion.application.finalproject_aparttalk.util

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kr.co.lion.application.finalproject_aparttalk.R

class Tools {
    companion object{

        // 뷰에 포커스를 주고 키보드를 올린다.
        fun showSoftInput(context: Context, view: View) {
            if (view.requestFocus()) {
                view.postDelayed({
                    val inputMethodManager = context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
                }, 200)
            }
        }

        // 키보드를 내려주고 포커스를 제거한다.
        fun hideSoftInput(activity: Activity) {
            activity.currentFocus?.let { currentFocus ->
                val inputMethodManager = activity.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(currentFocus.windowToken, 0)
                currentFocus.clearFocus()
            }
        }

        // 사진의 회전 각도값을 반환하는 메서드
        fun getDegree(context: Context, uri: Uri) : Int {
            // 사진 정보를 가지고 있는 객체 가져온다.
            var exifInterface: ExifInterface? = null


            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                // 이미지 데이터를 가져올 수 있는 Content Provide의 Uri를 추출한다.
                // val photoUri = MediaStore.setRequireOriginal(uri)
                // ExifInterface 정보를 읽어올 스트림을 추출한다.

                val inputStream = context.contentResolver.openInputStream(uri)!!
                // ExifInterface 객체를 생성한다.
                exifInterface = ExifInterface(inputStream)
            } else {
                // ExifInterface 객체를 생성한다.
                exifInterface = ExifInterface(uri.path!!)
            }

            if(exifInterface != null){
                // 반환할 각도값을 담을 변수
                var degree = 0
                // ExifInterface 객체에서 회전 각도값을 가져온다.
                val ori = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1)

                degree = when(ori){
                    ExifInterface.ORIENTATION_ROTATE_90 -> 90
                    ExifInterface.ORIENTATION_ROTATE_180 -> 180
                    ExifInterface.ORIENTATION_ROTATE_270 -> 270
                    else -> 0
                }

                return degree
            }

            return 0
        }

        // 회전시키는 메서드
        fun rotateBitmap(bitmap: Bitmap, degree:Float): Bitmap {
            // 회전 이미지를 생성하기 위한 변환 행렬
            val matrix = Matrix()
            matrix.postRotate(degree)

            // 회전 행렬을 적용하여 회전된 이미지를 생성한다.
            // 첫 번째 : 원본 이미지
            // 두 번째와 세번째 : 원본 이미지에서 사용할 부분의 좌측 상단 x, y 좌표
            // 네번째와 다섯번째 : 원본 이미지에서 사용할 부분의 가로 세로 길이
            // 여기에서는 이미지데이터 전체를 사용할 것이기 때문에 전체 영역으로 잡아준다.
            // 여섯번째 : 변환행렬. 적용해준 변환행렬이 무엇이냐에 따라 이미지 변형 방식이 달라진다.
            val rotateBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, false)

            return rotateBitmap
        }

        // 이미지 사이즈를 조정하는 메서드
        fun resizeBitmap(bitmap: Bitmap, targetWidth:Int): Bitmap {
            // 이미지의 확대/축소 비율을 구한다.
            val ratio = targetWidth.toDouble() / bitmap.width.toDouble()
            // 세로 길이를 구한다.
            val targetHeight = (bitmap.height * ratio).toInt()
            // 크기를 조장한 Bitmap을 생성한다.
            val resizedBitmap = Bitmap.createScaledBitmap(bitmap, targetWidth, targetHeight, false)

            return resizedBitmap
        }
    }
}

fun Context.setImage(imageView: ImageView, url:String?){
    Glide.with(this).load(url)
        .placeholder(R.drawable.image_aparttalk)
        .error(R.drawable.image_aparttalk)
        .into(imageView)
}