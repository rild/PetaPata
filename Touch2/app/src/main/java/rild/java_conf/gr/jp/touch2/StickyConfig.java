package rild.java_conf.gr.jp.touch2;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Browser;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.Buffer;

/**
 * Created by rild2357 on 2015/02/25.
 */
public class StickyConfig extends Activity {
    EditText editText_Tag;
    EditText editText_URL;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
/*
        editText_Tag = (EditText) findViewById(R.id.editText_Tag);
        // エディットテキストのテキストを設定します
        editText_Tag.setText("表示する名前を入力してください");
        // エディットテキストのテキストを全選択します
        editText_Tag.selectAll();
        // エディットテキストのテキストを取得します
        String text = editText_Tag.getText().toString();
        Toast.makeText(this, text, Toast.LENGTH_LONG);
*/

/*
        Cursor c = Browser.getAllBookmarks(getContentResolver());
        String url =c.getString(c.getColumnIndex(Browser.BookmarkColumns.URL));

        editText_URL = (EditText) findViewById(R.id.editText_URL);
        editText_Tag.selectAll();

        Toast.makeText(this, url, Toast.LENGTH_LONG);
*/
    }

    public void showWeb (View v){
        EditText txtKeywd =(EditText)findViewById(R.id.editText_URL);
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(txtKeywd.getText().toString()));
        startActivity(i);
    }
    public void reserve(View v){
        try{
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            openFileOutput("tag.dat", Context.MODE_PRIVATE)
                    )
            );//tag.datへの書き込み準備
            writer.write(editText_Tag.getText().toString());//editText_Tagへの入力値をファイルに書き込み
            writer.close();//ファイルをクローズ

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    public void back(View v){
        finish();
        //StickyConfigの終了
    }



}
