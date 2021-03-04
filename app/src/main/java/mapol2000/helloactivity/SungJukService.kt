package mapol2000.helloactivity

import android.content.Context
import android.util.Log
import android.widget.Toast
import java.io.*

object SungJukService {

    //var sjlist = arrayListOf<SungJuk>()
    var fname = "sungjuk.dat"

    fun computeSungJuk(sj:SungJuk) {
        sj.tot = (sj.kor.toInt() + sj.eng.toInt() + sj.mat.toInt()).toString()
        sj.avg = (sj.tot.toDouble() / 3).toString()
        sj.grd = when((sj.avg.toDouble()/10).toInt()) {
            9,10 -> "수"
            8 -> "우"
            7 -> "미"
            6 -> "양"
            else -> "가"
        }
        Log.d("sjsrv", "${sj.tot} ${sj.avg} ${sj.grd}")
    }

    fun writeSungJuk(sj:SungJuk, context:Context) {
        var sjdata = "${sj.name}|${sj.kor}|${sj.eng}|${sj.mat}|${sj.tot}|${sj.avg}|${sj.grd}\n"

        //var fos = openFileOutput("sayHello.txt", Context.MODE_PRIVATE)
        //fos.write(sayHello.toByteArray())
        //fos.close()
        // 액티비티 내에서 FileOutputStream 객체를 호출해서 파일쓰기 기능을 구현했었음

        context.openFileOutput(fname, Context.MODE_PRIVATE or Context.MODE_APPEND).use {
                fos -> fos.write(sjdata.toByteArray())
        }
        // 액티비티 외부에서 FileOutputStream 객체를 호출해서 파일쓰기 기능을 구현하려면
        // 액티비티의 컨텍스트를 이용해서 람다식으로 코드를 작성하면 해결!

        Toast.makeText(context,
            "성적데이터 저장완료!!", Toast.LENGTH_SHORT).show()
    }

    fun readSungJuk(context:Context): MutableList<SungJuk> {
        var sjlist = arrayListOf<SungJuk>()

        var fis = context.openFileInput(fname)
        var br = BufferedReader(InputStreamReader(fis))

        while (br.ready()) {
            var sj = br.readLine().split("|")
            sjlist.add(SungJuk(sj[0].toString(),
                sj[1].toString(), sj[2].toString(), sj[3].toString()))
        }
        br.close()
        fis.close()

        return sjlist
    }

}