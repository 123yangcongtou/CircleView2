package bwie.com.circleview2;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lishaocong on 2016/11/2.
 */
public class CircleView extends View {
    //圆
    private Paint mPaint;
    private float mRadius;
    private int mColor;
    private Rect rect;


    public CircleView(Context context) {
        this(context,null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //
//        mRadius=80;
//        mColor= Color.CYAN;

        TypedArray a=context.getTheme().obtainStyledAttributes(attrs,R.styleable.CircleView,defStyleAttr,0);
        //尺寸
        mRadius=a.getDimension(R.styleable.CircleView_radius,30);
        //颜色
        mColor=a.getInt(R.styleable.CircleView_colors,Color.GREEN);
        a.recycle();
        //画笔
        mPaint= new Paint();
    }
    //设置宽高
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取宽高坐标和大小
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthcurr=MeasureSpec.getSize(widthMeasureSpec);

        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        int heightcurr=MeasureSpec.getSize(heightMeasureSpec);
    //确定值
        int width;
        int height;
        //精确值
        if(widthMode==MeasureSpec.EXACTLY){
            width=widthcurr;
        }else{
            width=(int)(2*mRadius)+getPaddingLeft()+getPaddingRight();
        }
        if(heightMode==MeasureSpec.EXACTLY){
            height=heightcurr;
        }else{
            height=(int)(2*mRadius)+getPaddingTop()+getPaddingBottom();
        }

        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //宽高
        int width=getWidth()/2;
        int height=getHeight()/2;
        //设置圆形颜色
        mPaint.setColor(mColor);
        //画圆
        canvas.drawCircle(width,height,mRadius,mPaint);
        //设置字体颜色
        mPaint.setColor(Color.BLACK);
        String str="诺诺";
        rect = new Rect();
        mPaint.setTextSize(30);
        //设置字体子矩形内
        mPaint.getTextBounds(str,0,str.length(),rect);
        //写字体
        canvas.drawText(str,width-rect.width()/2,height+rect.height()/2,mPaint);
    }
}
