package mapol2000.helloactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun goNext(v: View) {

        var intent: Intent = Intent(this, Sub1Activity::class.java)
        // 다른 액티비티를 실행하기 위해 인텐트 객체 생성

        startActivity(intent)
        // 생성한 인텐트를 실행함

    }

}