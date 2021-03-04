package mapol2000.helloactivity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class ListSungJukActivity : AppCompatActivity() {

    lateinit var listView1: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_sung_juk)
        title = "성적 리스트"

        listView1 = findViewById(R.id.listView1)
        advListView(this)



    }

    // 리스트뷰 기본 사용법
    fun basicListView() {
        val list:MutableList<String> = ArrayList()
        list.add("NBA2K")
        list.add("God Of War")
        list.add("PES")
        list.add("Diablo")
        list.add("MGS")

        val lvAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        // 문자열 배열에 정의된 데이터를 simple_list_item_1 형태의 뷰를 이용해서
        // 리스트뷰에 출력하는 어댑터를 선언
        // simple_list_item_1: 안드로이드가 미리 정의해둔 뷰 (한줄에 하나의 항목만 출력)
        // simple_list_item_2: 안드로이드가 미리 정의해둔 뷰 (한줄에 두 개의 항목 출력)
        // simple_list_item_choice: 안드로이드가 미리 정의해둔 뷰 (sli1 + 라디오버튼 포함)
        // simple_list_item_checked: 안드로이드가 미리 정의해둔 뷰 (sli1 + 체크박스버튼 포함)
        listView1.adapter = lvAdapter
        // 앞에서 선언한 어댑터를 리스트뷰에 초기화함
    }

    // 사용자 정의 리스트뷰
    // 기본뷰가 아닌 임의의 뷰는 새로운 레이아웃 파일로 미리 정의해두어야 함
    fun advListView(context: Context) {
       /* val list:MutableList<SungJuk> = ArrayList()
        list.add(SungJuk("지현", "99", "98", "99"))
        list.add(SungJuk("혜교", "92", "78", "89"))
        list.add(SungJuk("수지", "57", "89", "88"))*/

        // 앱내 생성된 sungjuk.dat에서 성적데이터를 읽어옴
        val list:MutableList<SungJuk> = SungJukService.readSungJuk(context)

        val adapter = ListViewAdapter(context, list)
        listView1.adapter = adapter

        // 리스트뷰 항목 클릭시 클릭한 항목에 대한 정보를 출력하는 이벤트 추가
        // parent: AdapterView<*> 객체, 리스트뷰에 적용된 어댑터 객체를 의미
        // view: View 객체, 어댑터 객체에 정의된 뷰 객체를 의미
        // pos: 항목 클릭시 항목 위치값을 의미
        // id: 항목 클릭시 항목 고유값을 의미
        listView1.setOnItemClickListener { parent, view, pos, id ->
            // 항목 클릭시 어댑터에서 지정해준 위치값을 이용해서 해당 성적데이터를 가져옴
            val item = parent.getItemAtPosition(pos) as SungJuk

//            Toast.makeText(context, "${item.name} ${pos}", Toast.LENGTH_SHORT).show()
            var intent = Intent(context, ViewSungJukActivity::class.java)
            intent.putExtra("sj", item) // 객체형변수를 다른 액티비티에 넘길시 주의!!
                                                // 클래스는 반드시 parcelable을 구현해야 함!
            startActivity(intent)


        }
    }

    // recyclerView 기본
    fun basicRecyclerView() {

    }

    // 사용자 정의 recyclerView
    fun advRecyclerView() {

    }


}

class ListViewAdapter(val context: Context, val items: MutableList<SungJuk>) : BaseAdapter() {

    // 데이터의 각 항목을 뷰의 어느 위치에 출력할 것인지 정의
    override fun getView(pos: Int, view: View?, parent: ViewGroup?): View? {
        var convertView = view

        // 사용자 정의 뷰를 뷰 변수에 할당
        // LayoutInflater: 레이아웃파일을 뷰 객체로 객체화하는 함수
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.sj_listview, null)
        }

        // 데이터의 항목을 각각 추출해서 뷰의 지정한 객체에 적절히 할당함
        val item: SungJuk = items[pos]
        convertView?.findViewById<TextView>(R.id.tvName)?.text = item.name
        convertView?.findViewById<TextView>(R.id.tvKor)?.text = item.kor
        convertView?.findViewById<TextView>(R.id.tvEng)?.text = item.eng
        convertView?.findViewById<TextView>(R.id.tvMat)?.text = item.mat

        return  convertView
    }

    override fun getItem(pos: Int): SungJuk { // 선택한 항목 출력하기
        return items[pos]
    }

    override fun getItemId(pos: Int): Long { // 선택한 항목의 위치값 출력
        return pos.toLong()
    }

    override fun getCount(): Int { // 데이터 항목수 출력
        return items.size
    }

}
