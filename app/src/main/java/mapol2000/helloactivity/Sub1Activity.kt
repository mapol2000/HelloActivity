package mapol2000.helloactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Sub1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub1)
        title = "Sub1Activity"
    }

    fun goBack(v: View) {
        finish()
        // 현재 실행중인 액티비티를 종료시킴
    }

}