package com.example.user.lecturesample

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // lateinitで初期化を先延ばしさせる
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // FABの処理内容
        floatingActionButton.setOnClickListener{
            val intent = Intent(this, CreateMemoActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // メモの読み込み
        realm = Realm.getDefaultInstance()
        val results = realm.where(Memo::class.java).findAll()

        //リストビューにアダプタをセット
        val adapter = MemoListAdapter(results)
        adapter.notifyDataSetChanged()
        list.adapter = adapter
    }

    override fun onPause() {
        super.onPause()
        realm.close()
    }
}
