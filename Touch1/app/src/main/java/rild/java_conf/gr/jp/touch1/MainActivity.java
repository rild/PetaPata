package rild.java_conf.gr.jp.touch1;

import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;

import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Button;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;


public class MainActivity  extends Activity implements OnClickListener,
        OnLongClickListener, OnTouchListener{

    private Button button1;
    private int mState = 0;
    private int offsetX = 0;// ボタンクリックしたスクリーンのX座標とボタンのX座標の差分
    private int offsetY = 0;// ボタンクリックしたスクリーンのY座標とボタンのY座標の差分
    private final int STATE_NONE = 0;
    private final int STATE_DRAG = 1;

    final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;

    private int Operator=0;
    private ImageButton imgbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//下部の定義
        //pallet1
        ImageButton imbtn1 = (ImageButton)findViewById(R.id.imageButton_pallet_pink);
        imbtn1.setOnLongClickListener(new View.OnLongClickListener() {
            //ボタンが長押しクリックされた時のハンドラ
            @Override



            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                // 長押しクリックされた時の処理を記述

                //ImageButton imgbtn = (ImageButton)findViewById(R.id.imageButton_pink_d);
                //valuesにButtonを定義することはできないのかな 2/23 16:36
                //ここでImageButtonを作ることができないなら、Opeで制御するか・・・2/23 16:47
                Operator = 1; //pallet1 is pushed



                Log.v("OnLongClick", "Button was clicked");
                return false;
            }
        });

        //pallet2
        ImageButton imbtn2 = (ImageButton)findViewById(R.id.imageButton_pallet_green);
        imbtn2.setOnLongClickListener(new View.OnLongClickListener() {
            //ボタンが長押しクリックされた時のハンドラ
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                // 長押しクリックされた時の処理を記述
                Operator = 2; //pallet2 is pushed


                Log.v("OnLongClick", "Button was clicked");
                return false;
            }
        });
        //pallet3
        ImageButton imbtn3 = (ImageButton)findViewById(R.id.imageButton_pallet_orange);
        imbtn3.setOnLongClickListener(new View.OnLongClickListener() {
            //ボタンが長押しクリックされた時のハンドラ
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                // 長押しクリックされた時の処理を記述
                Operator = 3; //pallet3 is pushed


                Log.v("OnLongClick", "Button was clicked");
                return false;
            }
            });
        //pallet4
        ImageButton imbtn4 = (ImageButton)findViewById(R.id.imageButton_pallet_blue);
        imbtn4.setOnLongClickListener(new View.OnLongClickListener() {
            //ボタンが長押しクリックされた時のハンドラ
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                // 長押しクリックされた時の処理を記述
                Operator = 4; //pallet4 is pushed


                Log.v("OnLongClick", "Button was clicked");
                return false;
            }
        });



        //LinearLayout linearLayout = new LinearLayout(this);
        //setContentView(linearLayout);
        //↑を消して, ↓を追加したらres_palletだけ表示された02/23
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.main_Linearlayout);
        //activity_main.xmlにあるLinearLayoutと関連付けした・・・ハズ



        //2/23 17;05～
        if(Operator == 1){
            //pallet1 is pushed
//ボタン作成
            imgbtn =(ImageButton)findViewById(R.id.imageButton_pink_d);
// 各イベントリスナの登録
            imgbtn.setOnClickListener(this);
            imgbtn.setOnLongClickListener(this);
            imgbtn.setOnTouchListener(this);
            linearLayout.addView(imgbtn, new LinearLayout.LayoutParams(WC,WC));

            Operator = 0;

        }if(Operator == 2){
            //pallet2 is pushed
//ボタン作成
            imgbtn =(ImageButton)findViewById(R.id.imageButton_green_d);
// 各イベントリスナの登録
            imgbtn.setOnClickListener(this);
            imgbtn.setOnLongClickListener(this);
            imgbtn.setOnTouchListener(this);

            Operator = 0;

        }if(Operator == 3){
            //pallet3 is pushed
//ボタン作成
            imgbtn =(ImageButton)findViewById(R.id.imageButton_orange_d);
// 各イベントリスナの登録
            imgbtn.setOnClickListener(this);
            imgbtn.setOnLongClickListener(this);
            imgbtn.setOnTouchListener(this);

            Operator = 0;

        }if(Operator == 4){
            //pallet4 is pushed
//ボタン作成
            imgbtn =(ImageButton)findViewById(R.id.imageButton_blue_d);
// 各イベントリスナの登録
            imgbtn.setOnClickListener(this);
            imgbtn.setOnLongClickListener(this);
            imgbtn.setOnTouchListener(this);

            Operator = 0;

        }

        //ボタン作成
        //imgbtn =(ImageButton)findViewById(R.id.imageButton_blue_d);
// 各イベントリスナの登録
        imgbtn.setOnClickListener(this);
        imgbtn.setOnLongClickListener(this);
        imgbtn.setOnTouchListener(this);

        Operator = 0;









//ボタン作成
        button1 = new Button(this);
// 各イベントリスナの登録
        button1.setOnClickListener(this);
        button1.setOnLongClickListener(this);
        button1.setOnTouchListener(this);


        //linearLayout.addView(button1, new LinearLayout.LayoutParams(150,50));
        //final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;=>先頭で宣言
        linearLayout.addView(button1, new LinearLayout.LayoutParams(WC,WC));
        //button1の大きさの指定かな？
        //linearLayout.setBackground(Drawable.createFromResourceStream());
        button1.setText(button1.getLeft() + "," + button1.getTop());
    }

    //↓こいつらがthisで呼ばれるものかな
    @Override
     public void onClick(View v) {
// TODO 自動生成されたメソッド・スタブ
    button1.setText("onClick");
    Log.v("OnClick", "Button_m was clicked");
}
    @Override
    public boolean onLongClick(View v) {
// TODO 自動生成されたメソッド・スタブ
        button1.setText("onLongClick");
        Log.v("OnLongClick", "Button_m was clicked");
        return false;//trueにするとどうなる・・・？
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
// TODO 自動生成されたメソッド・スタブ
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
                    int pointX, pointY;
                    pointX = (int) event.getRawX() - offsetX;
//ステータスバーの取得方法が不明のため直打ち（要変更）
                    pointY = (int) event.getRawY() - (offsetY + 50);
                    button1.layout(pointX, pointY, pointX + button1.getWidth(), pointY + button1.getHeight());
                }
                break;
        }
        return false;
    }
    /*
    @Override
    public void onClick2(View v) {
// TODO 自動生成されたメソッド・スタブ

        Log.v("OnClick", "imgbtn was clicked");
    }
    @Override
    public boolean onLongClick2(View v) {
// TODO 自動生成されたメソッド・スタブ

        Log.v("OnLongClick", "imgbtn was clicked");
        return false;//trueにするとどうなる・・・？
    }
    @Override
    public boolean onTouch2(View v, MotionEvent event) {
// TODO 自動生成されたメソッド・スタブ
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
                    int pointX, pointY;
                    pointX = (int) event.getRawX() - offsetX;
//ステータスバーの取得方法が不明のため直打ち（要変更）
                    pointY = (int) event.getRawY() - (offsetY + 50);
                    imgbtn.layout(pointX, pointY, pointX + imgbtn.getWidth(), pointY + imgbtn.getHeight());
                }
                break;
        }
        return false;
    }
    */





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



