package rild.java_conf.gr.jp.touch2;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;


public class MainActivity extends ActionBarActivity {

    private int mState = 0;
    private int offsetX = 0;// ボタンクリックしたスクリーンのX座標とボタンのX座標の差分
    private int offsetY = 0;// ボタンクリックしたスクリーンのY座標とボタンのY座標の差分
    private final int STATE_NONE = 0;
    private final int STATE_DRAG = 1;
    private int pointX, pointY = 0;
    final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
    private int Operator=0;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton imbtn1 = (ImageButton)findViewById(R.id.imageButton_pallet_pink);
        imbtn1.setOnLongClickListener(new View.OnLongClickListener() {
            //ボタンが長押しクリックされた時のハンドラ
            @Override
           public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                // 長押しクリックされた時の処理を記述
                Log.v("OnLongClick", "Button_pallet_pink was clicked");
                //Operator = 1; //pallet1 is pushed

                final ImageButton imgbtn;
                final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.main_LinearLayout);

                imgbtn = new ImageButton(this);
                //thisってなんだ・・・？
                imgbtn.setBackground(getResources().getDrawable(R.drawable.res_sticky1));

                imgbtn.setOnClickListener(new View.OnClickListener() {
                    //implements より onClickメソッドを定義しなければならない

                    @Override
                    public void onClick(View v) {
                        Log.v("OnClick", "Button_Sticky_pink was clicked");

                    }
                });
                imgbtn.setOnLongClickListener(new View.OnLongClickListener() {
                    //implements より onClickメソッドを定義しなければならない

                    @Override
                    public boolean onLongClick(View v) {
                        Log.v("OnLongClick", "Button_Sticky_pink was clicked");
                        return false;
                    }
                });
                imgbtn.setOnTouchListener(new View.OnTouchListener() {
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
                                    imgbtn.setX(pointX);
                                    imgbtn.setY(pointY);
                                    // pointX + button1.getWidth(), pointY + button1.getHeight());
                                }
                                break;
                        }
                        return false;
                    }
                });

                linearLayout.addView(imgbtn, new LinearLayout.LayoutParams(WC, WC));


                return false;
            }
        });


/*
if(Operator==1) {
*/
    final ImageButton imgbtn;
    final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.main_LinearLayout);

    imgbtn = new ImageButton(this);
    //thisってなんだ・・・？
    imgbtn.setBackground(getResources().getDrawable(R.drawable.res_sticky1));

    imgbtn.setOnClickListener(new View.OnClickListener() {
        //implements より onClickメソッドを定義しなければならない

        @Override
        public void onClick(View v) {
            Log.v("OnClick", "Button_Sticky_pink was clicked");

        }
    });
    imgbtn.setOnLongClickListener(new View.OnLongClickListener() {
        //implements より onClickメソッドを定義しなければならない

        @Override
        public boolean onLongClick(View v) {
            Log.v("OnLongClick", "Button_Sticky_pink was clicked");
            return false;
        }
    });
    imgbtn.setOnTouchListener(new View.OnTouchListener() {
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
                        imgbtn.setX(pointX);
                        imgbtn.setY(pointY);
                        // pointX + button1.getWidth(), pointY + button1.getHeight());
                    }
                    break;
            }
            return false;
        }
    });

    linearLayout.addView(imgbtn, new LinearLayout.LayoutParams(WC, WC));
/*
    Operator=0;
}else if(Operator == 0){
    Log.v("Null", "nothing is done");

}
*/


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
