package rild.java_conf.gr.jp.touch1;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;

import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Button;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends Activity {

    private Button button1;
    private int mState = 0;
    private int offsetX = 0;// ボタンクリックしたスクリーンのX座標とボタンのX座標の差分
    private int offsetY = 0;// ボタンクリックしたスクリーンのY座標とボタンのY座標の差分
    private final int STATE_NONE = 0;
    private final int STATE_DRAG = 1;
    private int aState = 0;//activity State
    private Button btn_edit;
    private final Context mContext = this;

    final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;


    private int[] sticky_number = {0,0,0,0};
    //sticky_number[0](or[1][2][3])
    private ImageButton imgbtn[] = new ImageButton[4];
    private LinearLayout[] bg_sticky = new LinearLayout[4];



    int pointX, pointY = 0;
    class EditItem extends Activity{
        private TextView[][] tv_sticky = new TextView[4][2];
        }


    EditItem mEditItem = new EditItem();


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_edit =(Button)findViewById(R.id.edit);
        imgbtn[0] = (ImageButton) findViewById(R.id.imageButton_pallet_pink);
        imgbtn[1] = (ImageButton) findViewById(R.id.imageButton_pallet_green);
        imgbtn[2] = (ImageButton) findViewById(R.id.imageButton_pallet_orange);
        imgbtn[3] = (ImageButton) findViewById(R.id.imageButton_pallet_blue);

        Log.v("edit", "aState became 1");




//下部の定義
        for(int n=0;n< bg_sticky.length;n++){
            CreatePallet(n+1);
            //pallet1~4
            CreateSticky(n+1);
            /*
            if (savedInstanceState != null) {
                String str_text = savedInstanceState.getString("text" + KEY_INPUT_DATA + n);
                String str_url = savedInstanceState.getString("url" + KEY_INPUT_DATA + n);
                float pointX = savedInstanceState.getFloat("locationX" + KEY_SELECT_POS + n);
                float pointY = savedInstanceState.getFloat("locationY" + KEY_SELECT_POS + n);

                if ( str_text.equals(null) == false|| str_url.equals(null) == false) {
                    final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.main_Linearlayout);
                    linearLayout.addView(bg_sticky[n], new LinearLayout.LayoutParams(WC, WC));
                    bg_sticky[n].setX(pointX);
                    bg_sticky[n].setY(pointY);
                }
            }*/
        }


    }//onCreate


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }






//編集ボタン
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void edit(View v) {
        switch (aState) {
            case 0:
                //編集モードに遷移
                btn_edit.setBackgroundColor(Color.argb(180,154,205,50));
                //YellowGreen#9ACD32
                btn_edit.setText("edit");

                aState = 1;
                Log.v("edit", "aState became 1");
                break;
            case 1:
                //Deleteモードに遷移
                btn_edit.setBackgroundColor(Color.argb(180,255,127,80));
                //Coral#FF7F50
                btn_edit.setText("delete");
                aState = 2;
                Log.v("edit", "aState became 2");
                break;
            case 2:
                //Activityモードに遷移
                btn_edit.setBackgroundColor(Color.argb(180,181,147,100));
                //.setBackgroundColor(Color.argb(int, int, int, int)
                //BurlyWood#DEB887 180,222,184,135->#b4b59364 180,181,147,100

                btn_edit.setText("activate");
                aState = 0;
                Log.v("edit", "aState became 0");
        }
    }

    //palletをつくるメソッド
    private void CreatePallet(final int n){
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.main_Linearlayout);

        imgbtn[n-1].setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onClick(View v) {
                if (sticky_number[n - 1] == 0) {
                    final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.main_Linearlayout);
                    linearLayout.addView(bg_sticky[n-1], new LinearLayout.LayoutParams(WC, WC));
                    //bg_sticky[n-1].setX(imgbtn[n-1].getX());
                    //bg_sticky[n-1].setY(imgbtn[n - 1].getY());
                    sticky_number[n - 1] = 1;
                    Log.v("Create", "Button_Sticky" + n + " was created and number[" + (n - 1) + "]became 1");
                }

            }//onClick

                                       });
        imgbtn[n-1].setOnLongClickListener(new View.OnLongClickListener() {
            //ボタンが長押しクリックされた時のハンドラ
            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                // 長押しクリックされた時の処理を記述
                if(aState==2){

                    linearLayout.removeView(bg_sticky[n-1]);
                    sticky_number[n-1]=0;
                    bg_sticky[n-1].setX(0);
                    bg_sticky[n-1].setY(0);
                    Log.v("Remove", "Button_Sticky"+n+" was removed and number[" +(n-1)+"]became 0");
                }


                Log.v("OnLongClick", "Button was clicked");
                return false;
            }//onLongClick
        });
    }

//画像に透明化処理を施すメソッド
private Bitmap ImageProcessing(Bitmap input_bmp, int alpha){

    Bitmap bitmap = input_bmp;
    int width = bitmap.getWidth();
    int height = bitmap.getHeight();

// BitmapDrawableにまかせる方法
    Bitmap bitmap2 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
    android.graphics.Canvas cvs = new android.graphics.Canvas(bitmap2);

    BitmapDrawable drawable = new BitmapDrawable(getResources(),bitmap);
    drawable.setAlpha(alpha);
    drawable.setBounds(0, 0, width, height);
    drawable.draw(cvs);
    bitmap = bitmap2;

    return bitmap;
}


//コンフィグ画面を作るメソッド
private void CreateConfigDialog(final int n, final EditItem edititem){
    LayoutInflater inflater
            = LayoutInflater.from(MainActivity.this);
    //View view = inflater.inflate(R.layout.sticky_config_dialog, null);
    View v = this.getLayoutInflater().inflate(R.layout.sticky_config_dialog, null);
    //final LinearLayout linearLayout_cfg_main=(LinearLayout)findViewById(R.id.L_layout_main);
    final EditText input_editText_cfg_text =(EditText)v.findViewById(R.id.editText_text);
    final EditText input_editText_cfg_url = (EditText)v.findViewById(R.id.editText_url);

    AlertDialog show = new AlertDialog.Builder(MainActivity.this)
            .setTitle(R.string.config_title)
            .setIcon(android.R.drawable.ic_input_get)
            .setView(v)
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    Log.v("OnClick", "input text = " + input_editText_cfg_text.getText().toString());

                    //入力した文字をトースト出力する
                    Toast.makeText(MainActivity.this,
                            input_editText_cfg_text.getText().toString() +"\n"+ input_editText_cfg_url.getText().toString(),
                            Toast.LENGTH_LONG).show();
                    mEditItem.tv_sticky[n - 1][0].setText(input_editText_cfg_text.getText().toString());
                    mEditItem.tv_sticky[n - 1][1].setText(input_editText_cfg_url.getText().toString());
                }
            })
            .setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                }
            })
            .show();
}

//付箋をつくるメソッド
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)

    private void CreateSticky(final int n){

        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.main_Linearlayout);
        bg_sticky[n-1] = new LinearLayout(mContext);
        bg_sticky[n-1].setBackground(new BitmapDrawable(getResources(),
                        ImageProcessing(
                        BitmapFactory.decodeResource(getResources(),getResources().getIdentifier("res_sticky"+n,"drawable", getPackageName() )),
                        178)
            )
        );
        bg_sticky[n-1].setOrientation(LinearLayout.VERTICAL);


        //ボタン作成
        mEditItem. tv_sticky[n-1][0] = new  TextView(mContext);
        mEditItem.tv_sticky[n-1][1] = new  TextView(mContext);
        bg_sticky[n-1].addView(mEditItem.tv_sticky[n-1][0],new LinearLayout.LayoutParams(WC, WC));
        bg_sticky[n-1].addView(mEditItem.tv_sticky[n-1][1],new LinearLayout.LayoutParams(WC, WC));

// 各イベントリスナの登録
        bg_sticky[n-1].setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.v("OnClick", "Button_Sticky"+n+ "was clicked");
                if(aState==0){

                }

                if(aState ==1){
                    CreateConfigDialog(n,mEditItem);
                }
                if(aState ==2){
                    linearLayout.removeView(bg_sticky[n-1]);
                    sticky_number[n-1]=0;
                    mEditItem.tv_sticky[n - 1][0].setText("");
                    mEditItem.tv_sticky[n - 1][1].setText("");
                    Log.v("Remove", "Button_Sticky"+n+" was removed and number[" +(n-1)+"]became 0");
                }
     }
        });

        bg_sticky[n-1].setOnLongClickListener(new OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Log.v("OnLongClick", "Button_Sticky_pink was clicked");



                return false;
            }
        });


        bg_sticky[n-1].setOnTouchListener(new OnTouchListener() {


                @TargetApi(Build.VERSION_CODES.HONEYCOMB)
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    //bg_sticky[n-1].bringToFront();
                    // ドラッグ
                    if(aState==1){
                        switch (event.getAction() & MotionEvent.ACTION_MASK) {
                            case MotionEvent.ACTION_DOWN:
                                offsetX = (int) event.getX();
                                offsetY = (int) event.getY();
                                mState = STATE_DRAG;
                                break;
                            case MotionEvent.ACTION_UP:
                            case MotionEvent.ACTION_POINTER_UP:
                                mState = STATE_NONE;
                                break;
                            case MotionEvent.ACTION_MOVE:
                                if (mState == STATE_DRAG) {

                                    pointX = (int) event.getRawX() - offsetX;
//ステータスバーの取得方法が不明のため直打ち（要変更）
                                    pointY = (int) event.getRawY() - (offsetY + 50);

                                    bg_sticky[n-1].setX(pointX);
                                    bg_sticky[n-1].setY(pointY);
                         }
                                break;
                        }

                    }

                    return false;
                }
            });

    }



    public void save(View v){
        SaveEditItem();
    }
    public void load(View v){
        LoadEditItem();
    }


    private void SaveEditItem(){
        /*for(int n=1;n<5;n++){
            if(mEditItem.bg_sticky[n-1].getBackground()!=null){
                mEditItem.bg_sticky[n-1].setBackground(null

                );
            }
        }

        SharedPreferences pref = getSharedPreferences("pref",MODE_PRIVATE);

        //GsonでUserをJSON文字列に変換して保存する
        Gson gson = new Gson();
        gson.toJson(mEditItem);
        pref.edit().putString("EditItem",gson.toJson(mEditItem)).commit();



        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        for(int i=0;i<4;i++){
            sp.edit().putInt("SaveInteger"+i, sticky_number[i]).commit();
        }
        for(int n=1;n<5;n++){
            sp.edit().putString("StickyText", mEditItem.tv_sticky[n][0].getText().toString());
            sp.edit().putString("StickyURL", mEditItem.tv_sticky[n][1].getText().toString());
        }
        */


    }
    private void LoadEditItem(){
/*
        SharedPreferences pref = getSharedPreferences("pref",MODE_PRIVATE);
        Gson gson = new Gson();
//Userオブジェクト復元
        EditItem mEditItem = gson.fromJson(pref.getString("EditItem",""),EditItem.class);


        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        for(int i=0;i<4;i++){

            sticky_number[i]=sp.getInt("SaveInteger" + i, sticky_number[i]);
        }
        for(int n=1;n<5;n++){
            mEditItem.tv_sticky[n][0].setText(sp.getString("StickyText",null), TextView.BufferType.NORMAL);
            mEditItem.tv_sticky[n][1].setText(sp.getString("StickyURL",null), TextView.BufferType.NORMAL);
        }
*/
    }

    private static final String KEY_INPUT_DATA = "input.data";
    private static final String KEY_FIELD_COUNT = "fld.count";
    private static final String KEY_SELECT_POS = "select.pos";

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@Override
protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
  /* ここで状態を保存 */

    for (int n = 0; n < bg_sticky.length ; n++) {
        String str_text = mEditItem.tv_sticky[n][0].getText()
                .toString();
        String str_url = mEditItem.tv_sticky[n][1].getText()
                .toString();
        if ( str_text.equals("") == false|| str_url.equals("") == false) {
            // Bundle#put******(String key, ****** value)
            // 引数は 記録するキー と 記録する値
            outState.putString("text" + KEY_INPUT_DATA + n, mEditItem.tv_sticky[n][0].getText()
                    .toString());
            outState.putString("url" + KEY_INPUT_DATA + n, mEditItem.tv_sticky[n][1].getText()
                    .toString());
            outState.putFloat("locationX" + KEY_SELECT_POS + n,
                    bg_sticky[n].getX());
            outState.putFloat("locationY" + KEY_SELECT_POS + n,
                    bg_sticky[n].getY());
        }
    }

}

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
  /* ここで保存した状態を読み出して設定 */
        if (savedInstanceState != null) {
            // Bundle#get******(String key) でデータ取得

            for (int n = 0; n < bg_sticky.length ; n++) {
                String str_text = savedInstanceState.getString("text" + KEY_INPUT_DATA + n);
                String str_url = savedInstanceState.getString("url" + KEY_INPUT_DATA + n);
                mEditItem.tv_sticky[n][0].setText(str_text);
                mEditItem.tv_sticky[n][1].setText(str_url);

                float pointX = savedInstanceState.getFloat("locationX" + KEY_SELECT_POS + n);
                float pointY = savedInstanceState.getFloat("locationY" + KEY_SELECT_POS + n);
                bg_sticky[n].setX(pointX);
                bg_sticky[n].setY(pointY);
            }

        }


    }

}



