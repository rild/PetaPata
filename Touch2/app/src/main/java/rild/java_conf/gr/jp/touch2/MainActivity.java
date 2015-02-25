package rild.java_conf.gr.jp.touch2;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends Activity {

    private int mState = 0;
    private int aState = 0;//activity State
    private int offsetX = 0;// ボタンクリックしたスクリーンのX座標とボタンのX座標の差分
    private int offsetY = 0;// ボタンクリックしたスクリーンのY座標とボタンのY座標の差分
    private final int STATE_NONE = 0;
    private final int STATE_DRAG = 1;
    private int pointX, pointY = 0;
    final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
    //private int Operator=0;

    private Button btn_edit;

    private final Context mContext = this;
    //

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_edit =(Button)findViewById(R.id.edit);





        ImageButton imbtn1 = (ImageButton)findViewById(R.id.imageButton_pallet_pink);
        imbtn1.setOnLongClickListener(new View.OnLongClickListener() {
            //ボタンが長押しクリックされた時のハンドラ

            //aState=1（編集モード）の時のみ

            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                // 長押しクリックされた時の処理を記述

                if(aState == 0){
                    //操作モードの時のpalletの動きはどうしようか2/25 11:37
                    Log.v("OnLongClick", "Button_pallet_pink was clicked as Action");
                }

               if(aState == 1){
                   Log.v("OnLongClick", "Button_pallet_pink was clicked as Editing");
                   final Button btn;
                   final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.main_LinearLayout);

                   btn = new Button(mContext);
                   //または
                   //btn = new Button(MainActivity.this);
                   //new ImageButton(this)とするとListenerクラスがthisになる02/24
                   //thisってなんだ・・・？
                   btn.setBackground(getResources().getDrawable(R.drawable.res_sticky1));
                   btn.setAlpha(200);

                   btn.setOnClickListener(new View.OnClickListener() {
                       //implements より onClickメソッドを定義しなければならない

                       @Override
                       public void onClick(View v) {

                           if(aState==0){
                               Log.v("OnClick", "Button_Sticky_pink was clicked as Action");

                           }if(aState==1){
                               Log.v("OnClick", "Button_Sticky_pink was clicked as Editing");
                               Intent intent = new Intent(MainActivity.this, rild.java_conf.gr.jp.touch2.StickyConfig.class);//ここはFQCN
                               //StickyConfigへのインテントの作成
                               startActivity(intent);
                               //Activityの起動


                           }

                       }
                   });

                   btn.setOnLongClickListener(new View.OnLongClickListener() {
                       //implements より onClickメソッドを定義しなければならない

                       @Override
                       public boolean onLongClick(View v) {
                           Log.v("OnLongClick", "Button_Sticky_pink was clicked");
                           return false;
                       }
                   });
                   btn.setOnTouchListener(new View.OnTouchListener() {
                       @TargetApi(Build.VERSION_CODES.HONEYCOMB)
                       @Override
                       public boolean onTouch(View v, MotionEvent event) {
                           // ドラッグ
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
                                       //int pointX, pointY;　先頭で宣言したため削除
                                       pointX = (int) event.getRawX() - offsetX;
//ステータスバーの取得方法が不明のため直打ち（要変更）
                                       pointY = (int) event.getRawY() - (offsetY + 50);
                                       //button1.layout(pointX, pointY, pointX + button1.getWidth(), pointY + button1.getHeight());
                                       btn.setX(pointX);
                                       btn.setY(pointY);
                                       // pointX + button1.getWidth(), pointY + button1.getHeight());
                                   }
                                   break;
                           }
                           return false;
                       }
                   });

                   linearLayout.addView(btn, new LinearLayout.LayoutParams(WC, WC));
                   //(int width, int height)
               }


                return false;
            }//ここまでがonLongClick
        });//ここまでがonLongClickListener






    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void generate_sticky1(View v){
        //タッチ操作で動かすもの
        //aState=0（操作モード）での機能
        //タッチするとブラウザ表示

    }

    public void edit(View v ){
        switch(aState){
        case 0:
            //編集モードに遷移
            btn_edit.setBackgroundColor(Color.RED);
            btn_edit.setText("E");
            aState=1;
            Log.v("edit", "aState became 1");
            break;
        case 1:
           //操作モードに遷移
            btn_edit.setBackgroundColor(Color.WHITE);
            btn_edit.setText("A");
            aState=0;
            Log.v("edit", "aState became 0");
            break;
        }
    }


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
}
