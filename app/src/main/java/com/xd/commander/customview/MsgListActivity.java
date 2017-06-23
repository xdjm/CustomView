package com.xd.commander.customview;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MsgListActivity extends AppCompatActivity {
    private ListView listView;
    private List<String> list;

    private MyDatabasehelper myDatabasehelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_list);
        myDatabasehelper = new MyDatabasehelper(this,"Msg.db",null,1);
        SQLiteDatabase sqLiteDatabase = myDatabasehelper.getWritableDatabase();
        list =new ArrayList<>();
        listView = (ListView)findViewById(R.id.list_item);

        Cursor cursor = sqLiteDatabase.query("msg",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                list.add(list.size()+" 标题："+cursor.getString(cursor.getColumnIndex("title"))+" 内容:"+cursor.getString(cursor.getColumnIndex("context")));
            }
            while (cursor.moveToNext());

        }
        cursor.close();
        listView.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list));
    }
}
