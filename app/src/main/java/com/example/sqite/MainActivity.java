package com.example.sqite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    MyDatabaseHelper mMyDatabaseHelper;
    private SQLiteDatabase mMSQLiteDatabase;
    private ContentValues mContentValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMyDatabaseHelper = new MyDatabaseHelper(this, "sqlite3", null, 6);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_insert_book:
                mMSQLiteDatabase = mMyDatabaseHelper.getWritableDatabase();
                break;
            case R.id.btn_add_data:
                mMSQLiteDatabase = mMyDatabaseHelper.getWritableDatabase();
                mContentValues = new ContentValues();
                mContentValues.put("name", "youwenkai");
                mContentValues.put("author", "hello summer");
                mContentValues.put("pages", "200");
                mContentValues.put("price", "10.99");
                mMSQLiteDatabase.insert("Book", null, mContentValues);
                mContentValues.clear();

                mContentValues.put("name", "xixixi");
                mContentValues.put("author", " summer");
                mContentValues.put("pages", "99");
                mContentValues.put("price", "100.99");
                mMSQLiteDatabase.insert("Book", null, mContentValues);
                break;
            case R.id.btn_up_data:
                mMSQLiteDatabase = mMyDatabaseHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("price", 99.99);
                mMSQLiteDatabase.update("Book", contentValues, "name = ?", new String[]{"youwenkai"});
                break;
            case R.id.btn_delect_data:
                mMSQLiteDatabase = mMyDatabaseHelper.getWritableDatabase();
                mMSQLiteDatabase.delete("Book", "pages > ?", new String[]{"100"});
                break;
            case R.id.btn_query_data:     //查询表中所有数据
                mMSQLiteDatabase = mMyDatabaseHelper.getWritableDatabase();
                Cursor cursor = mMSQLiteDatabase.query("Book", null, null, null, null, null, null);
                if(cursor.moveToFirst()){
                    do {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        String pages = cursor.getString(cursor.getColumnIndex("pages"));
                        String price = cursor.getString(cursor.getColumnIndex("price"));
                        Log.d(TAG, "书名是 " + name);
                        Log.d(TAG, "作者是 " + author);
                        Log.d(TAG, "页数是 " + pages);
                        Log.d(TAG, "价格是 " + price);
                    } while (cursor.moveToNext());
                    cursor.close();
                }
                break;
        }
    }
}
