package mapol2000.helloactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RatingBar

class MP2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_m_p2)
        title = "선호도 조사 결과"

        // MPActivity에서 넘겨준 데이터를 담아둘 배열 변수 선언
        var rbId = arrayOf(R.id.rbar1, R.id.rbar2, R.id.rbar3, R.id.rbar4,
            R.id.rbar5, R.id.rbar6, R.id.rbar7, R.id.rbar8, R.id.rbar9)
        // MPActivity에서 넘겨준 투표수를 반영할 레이팅바 객체를 저장할 배열 변수 선언
        var rBar = arrayOfNulls<RatingBar>(9)

        // 배열 변수에 실제 레이팅바 객체를 초기화함
        for (i in rbId.indices) {
            rBar[i] = findViewById(rbId[i])
            rBar[i]!!.isEnabled = false
            rBar[i]!!.numStars = 5
            rBar[i]!!.setIsIndicator(true)

        }

        // MPActivity에서 넘겨준 객체를 받아서 적절한 변수에 대입
        var intent: Intent = intent
        var vote = intent.getIntArrayExtra("vote")
        var imgName = intent.getStringArrayExtra("imgName")

        // 초기화한 레이팅바 객체에 투표수를 반영함
        for (i in rbId.indices) {
            rBar[i]!!.rating = vote!![i].toFloat()
        }

    }

    fun goMain(v: View) {
        finish()
    }

}