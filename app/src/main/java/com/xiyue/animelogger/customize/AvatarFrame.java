package com.xiyue.animelogger.customize;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;

public class AvatarFrame extends androidx.appcompat.widget.AppCompatImageView {
    private Paint paint;

    public AvatarFrame(Context context) {
        super(context);
    }

    public AvatarFrame(Context context, AttributeSet attrs){
        super(context, attrs);
        init();
    }

    public AvatarFrame(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        // 自定义绘制逻辑
        Bitmap bitmap = getBitmapFromDrawable(getDrawable());
        if (bitmap != null) {
            // 创建一个圆形的 Bitmap
            Bitmap circleBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            Canvas circleCanvas = new Canvas(circleBitmap);

            int radius = Math.min(getWidth(), getHeight()) / 2;
            circleCanvas.drawCircle((float) getWidth() / 2, (float) getHeight() / 2, radius, paint);

            // 使用 BitmapShader 将图片映射到圆形区域内
            paint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
            circleCanvas.drawCircle((float) getWidth() / 2, (float) getHeight() / 2, radius, paint);

            // 将裁剪后的圆形图片绘制到原始的 Canvas 上
            canvas.drawBitmap(circleBitmap, 0, 0, null);
        }
    }

    private Bitmap getBitmapFromDrawable(android.graphics.drawable.Drawable drawable) {
        if (drawable == null) {
            return null;
        }

        if (drawable instanceof android.graphics.drawable.BitmapDrawable) {
            return ((android.graphics.drawable.BitmapDrawable) drawable).getBitmap();
        }

        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

}
