package com.example.user.lecturesample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import io.realm.OrderedRealmCollection
import io.realm.RealmBaseAdapter

/**
 * Created by User on 2018/09/21.
 */
class MemoListAdapter(realmResults: OrderedRealmCollection<Memo>) : RealmBaseAdapter<Memo>(realmResults), ListAdapter {

    companion object {
        class ViewHolder {
            lateinit var title: TextView
            lateinit var contents: TextView
        }
    }

    // リストビューのセルデータが必要になったとき呼び出し
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // convertViewはvalで変更不可なので、変更可能にするためvarにする
        var convertView = convertView
        val viewHolder: ViewHolder

        // viewが存在しない場合、viewを作成する
        if (convertView == null) {
            convertView = LayoutInflater.from(parent!!.context).inflate(android.R.layout.simple_list_item_2, parent, false)
            viewHolder = ViewHolder()
            viewHolder.title = convertView!!.findViewById(android.R.id.text1)
            viewHolder.contents = convertView.findViewById(android.R.id.text2)
            convertView.tag = viewHolder
        } else {
            // viewが存在するときは、タグとしてつけておいたviewHolderからviewを取り出して使う
            viewHolder = convertView.tag as ViewHolder
        }

        val memo = adapterData!![position]

        viewHolder.title.text = memo.title
        viewHolder.contents.text = memo.contents

        return convertView
    }
}