package mapol2000.helloactivity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListSungJuk2Activity : AppCompatActivity() {

    lateinit var rv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_sung_juk2)

        rv = findViewById(R.id.recyclerView)

        val list: MutableList<SungJuk> = SungJukService.readSungJuk(this)

        // val rvAdapter = RVAdapter(this, list) // 이벤트 없음
        val rvAdapter = RVAdapter(this, list) {sj -> goView(sj, this)}

        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(this)
//        rv.setHasFixedSize(true)
    }

    // 리사이클러뷰 어댑터 클래스 정의
    class RVAdapter(val context: Context, var list: MutableList<SungJuk>, val itemClick: (SungJuk) -> Unit) :
        RecyclerView.Adapter<RVAdapter.Holder>() {

        // onBindViewerHolder 함수 호출시 실제 데이터 항목과
        // 뷰에 출력할 위젯을 알아내서 바인딩하는 bind 함수 정의
        inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var tvName = itemView?.findViewById<TextView>(R.id.tvName)
            var tvKor = itemView?.findViewById<TextView>(R.id.tvKor)
            var tvEng = itemView?.findViewById<TextView>(R.id.tvEng)
            var tvMat = itemView?.findViewById<TextView>(R.id.tvMat)

            fun bind(sj: SungJuk, context: Context) {
                tvName.text = sj.name // 뷰 항목에 개별값 할당
                tvKor.text = sj.kor
                tvEng.text = sj.eng
                tvMat.text = sj.mat

                itemView.setOnClickListener {itemClick(sj)} // 항목에 이벤트 추가
            }

        }

        // 뷰홀더 객체 생성시 해야할 작업 정의
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val view = LayoutInflater.from(context).inflate(R.layout.sj_listview, parent, false)

            return Holder(view)
        }

        // 뷰에 출력할 전체 항목 수 반환
        override fun getItemCount(): Int = list.size

        // 데이터를 뷰홀더를 통해서 뷰객체에 할당함(바인딩)
        // 즉, onCreateViewHolder에서 생성한 뷰객체와 데이터 객체를
        // 위에서 내부클래스로 정의한 Holder 클래스의 bind 함수를 통해 바인딩함
        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder?.bind(list[position], context)
        }


    }
    private fun goView(sj: SungJuk, context: Context) {
        var intent = Intent(context, ViewSungJukActivity::class.java)
        intent.putExtra("sj", sj)
        startActivity(intent)
    }

}