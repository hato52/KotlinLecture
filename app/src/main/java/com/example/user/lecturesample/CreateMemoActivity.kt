package com.example.user.lecturesample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_create_memo.*

class CreateMemoActivity : AppCompatActivity() {

    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_memo)

        realm = Realm.getDefaultInstance()

        // 登録ボタンを押したときの処理
        submitButton.setOnClickListener {
            if (titleText.text.isEmpty()) {
                Toast.makeText(this, "メモを入力してください", Toast.LENGTH_SHORT).show()
            } else {
                addMemo()
                finish()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        realm.close()
    }

    // IDのインクリメント処理
    private fun getNextId(): Long {
        var nextId: Long = 1
        val maxId = realm.where(Memo::class.java).max("id")
        if (maxId != null) {
            nextId = maxId.toLong() + 1
        }

        return nextId
    }

    //メモの新規登録
    private fun addMemo() {
        try {
            // メモの追加
            realm.executeTransaction {
                val memo = realm.createObject(Memo::class.java, getNextId())
                memo.title = titleText.text.toString()
                memo.contents = contentsText.text.toString()
            }
            Toast.makeText(this, "メモを登録しました", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            realm.close()
            Toast.makeText(this, "メモの登録に失敗しました", Toast.LENGTH_SHORT).show()
        }
    }
}
