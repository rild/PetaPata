package rild.java_conf.gr.jp.petapeta;


import flash.display.Sprite;
import flash.display.StageAlign;
import flash.display.StageScaleMode;
import flash.events.TouchEvent;
import flash.ui.Multitouch;
import flash.ui.MultitouchInputMode;
/**
 * Created by rild2357 on 2015/02/21.
 */


public class touchAction extends Sprite
{
    private var rect:Sprite;
    public function touchAction()
    {
        super();
// autoOrients をサポート
        stage.align = StageAlign.TOP_LEFT;
        stage.scaleMode = StageScaleMode.NO_SCALE;
// マルチタッチのモードを "TOUCH_POINT" にする
        Multitouch.inputMode = MultitouchInputMode.TOUCH_POINT;
// タッチアクションに反応する画面上のオブジェクトを生成する
        rect = new Sprite();
        rect.graphics.beginFill(0xFFFFFF * Math.random());
        rect.graphics.drawRect(‐100, ‐100, 200, 200);
        rect.graphics.endFill();
        rect.x = stage.stageWidth / 2;
        rect.y = stage.stageHeight / 2;
        rect.alpha = 0.3;
        addChild(rect);
// タッチされた時（触れる、離れる）に実行される関数を設定
        rect.addEventListener(TouchEvent.TOUCH_BEGIN, onTouchBegin);
        rect.addEventListener(TouchEvent.TOUCH_END, onTouchEnd);
    }
    // オブジェクトに指が触れた時
    public function onTouchBegin(e:TouchEvent):void
    {
// ドラッグ開始
        e.target.startDrag();
// オブジェクトを濃くする
        e.target.alpha = 1;
    }
    // オブジェクトから指が離れた時
    public function onTouchEnd(e:TouchEvent):void
    {
// ドラッグ終了
        e.target.stopDrag();
// オブジェクトを薄くする
        e.target.alpha = 0.3;
    }
}

