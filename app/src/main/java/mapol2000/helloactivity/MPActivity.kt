package mapol2000.helloactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast

class MPActivity : AppCompatActivity() {

    var vote = IntArray(9) // 선호도 배열
    var imgName = arrayOf("독서하는 소녀", "꽃장식 모자 소녀", "부채를 든 소녀",
        "이레느깡 단 베르양", "잠자는 소녀", "테라스의 두 자매", "피아노 레슨", "피아노 앞의 소녀들",
        "해변에서") // 이미지 이름 배열

    override fun onCreate(savedInstanceState: Bundle?) { // 액티비티가 생성되면 다음 코드 실행
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_m_p)
        title = "명화 선호도 조사"

        // 이미지 클릭시 선호도 정보를 저장할 배열 선언

        var iv = arrayOfNulls<ImageView>(9) // 이미지뷰 객체 배열
        var ivId = arrayOf(R.id.iv1, R.id.iv2, R.id.iv3, R.id.iv4, R.id.iv5,
            R.id.iv6, R.id.iv7, R.id.iv8, R.id.iv9) // 이미지뷰 ID 배열

        // 이미지 클릭시 클릭한 이미지의 투표수를 증가시킴
        // 또한, 해당 이미지의 이름과 누적 투표수를 Toast로 출력함
        // 따라서, 각 이미지 버튼에 이러한 처리를 하는 클릭이벤트를 추가함
        for (i in ivId.indices) {
            iv[i] = findViewById(ivId[i])
            iv[i]!!.setOnClickListener {
                vote[i]++ // 투표수 증가
                Toast.makeText(applicationContext, "${imgName[i]} 투표수: ${vote[i]}", Toast.LENGTH_SHORT).show()
            } // 투표 결과 출력

        }

    }

    fun goResult(v: View) {
        var intent: Intent = Intent(this, MP2Activity::class.java)
        intent.putExtra("vote", vote)
        intent.putExtra("imgName", imgName)
        startActivity(intent)
    }

}