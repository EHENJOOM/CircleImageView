package com.ehenjoom.circleimageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class CircleImageView extends AppCompatImageView {

    private float width;
    private float height;
    private float radius;
    private Paint paint;
    private Matrix matrix;

    public CircleImageView(Context context){
        this(context,null);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs){
        this(context,attrs,0);
    }

    public CircleImageView(Context context,@Nullable AttributeSet attrs,int defStyleAttr){
        super(context, attrs, defStyleAttr);
        paint=new Paint();
        paint.setAntiAlias(true);
        matrix=new Matrix();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        width=getMeasuredWidth();
        height=getMeasuredHeight();
        radius=Math.min(width,height)/2;
    }

    @Override
    protected void onDraw(Canvas canvas){
        paint.setShader(initBitmapShader());     // 将着色器设置给画笔
        canvas.drawCircle(width/2,height/2,radius,paint);
    }

    private BitmapShader initBitmapShader(){
        Bitmap bitmap=((BitmapDrawable)getDrawable()).getBitmap();
        BitmapShader bitmapShader=new BitmapShader(bitmap,Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
        float scale=Math.max(width/bitmap.getWidth(),height/bitmap.getWidth());
        matrix.setScale(scale,scale);     // 将图片等比例缩放，避免拉伸
        bitmapShader.setLocalMatrix(matrix);
        return bitmapShader;
    }
}
