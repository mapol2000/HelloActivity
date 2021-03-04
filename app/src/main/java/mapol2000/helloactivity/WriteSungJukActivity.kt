package mapol2000.helloactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class WriteSungJukActivity : AppCompatActivity() {

    var et = arrayOfNulls<EditText>(4)
    var etId = arrayOf(R.id.name, R.id.kor, R.id.eng, R.id.mat)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_sung_juk)

        // editText 객체 초기화
        for (i in et.indices) {
            et[i] = findViewById(etId[i])
        }

    }

    fun saveSungJuk(v: View) {

        var sj = SungJuk(et[0]!!.text.toString(), et[1]!!.text.toString(), et[2]!!.text.toString(), et[3]!!.text.toString())

        SungJukService.computeSungJuk(sj)
        SungJukService.writeSungJuk(sj, this)

    }

    fun goReadAll(v: View) {
        var intent = Intent(this, ListSungJukActivity::class.java)
        startActivity(intent)
    }

}