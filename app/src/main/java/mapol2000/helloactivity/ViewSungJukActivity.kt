package mapol2000.helloactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast

class ViewSungJukActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_sung_juk)



        var intent = intent
        var sj = intent.getParcelableExtra<SungJuk>("sj")!!

        var textViews = arrayOfNulls<TextView>(7)
        var textViewIDs = arrayOf(R.id.tvName1, R.id.tvKor1, R.id.tvEng1, R.id.tvMat1, R.id.tvTot1, R.id.tvAvg1, R.id.tvGrd1)

        SungJukService.computeSungJuk(sj)
        var sjOne = arrayOf(sj.name, sj.kor, sj.eng, sj.mat, sj.tot, sj.avg, sj.grd)

        for (i in textViewIDs.indices) {
            textViews[i] = findViewById(textViewIDs[i])
            textViews[i]?.text = sjOne[i]
        }







        Toast.makeText(applicationContext, "${sj.name}", Toast.LENGTH_SHORT).show()
        Log.d("view", "${sj.name} ${sj.kor} ${sj.mat}")

    }

    fun goMain(v:View) {
        finish()
    }

}