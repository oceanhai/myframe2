package com.wuhai.myframe2.utils
import android.app.Activity
import android.content.Context
import android.graphics.*
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.wuhai.myframe2.R
import kotlinx.android.synthetic.main.layout_watermark.view.*

/**
 *  Activity中显示水印，在onCreate的setContentView方法后调用
 *  多个界面显示水印可在BaseActivity中统一调用
 *  @param activity
 * @param string 水印文字内容
 */
object WaterMarkUtil2 {

    const val TAG = "WaterMarkUtil2"

    fun showWatermarkView(activity: Activity, string: String) {
        val rootView: ViewGroup = activity.window.decorView.findViewById(android.R.id.content)
        val framView: View = LayoutInflater.from(activity).inflate(R.layout.layout_watermark, null)
        val wm: WindowManager = activity.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        wm.defaultDisplay.getMetrics(dm)
        val width = dm.widthPixels         // 屏幕宽度（像素）
        val height = dm.heightPixels       // 屏幕高度（像素）
        framView.ivWm.setImageDrawable(getMarkTextBitmapDrawable(activity, string, width, height))
        //可对水印布局进行初始化操作
        rootView.addView(framView)
    }

    /**
     * 获得文字水印的图片
     * @param width
     * @param height
     * @return
     */
    private fun getMarkTextBitmapDrawable(
        gContext: Context,
        gText: String,
        width: Int,
        height: Int
    ): Drawable? {
        val bitmap = getMarkTextBitmap(gContext, gText, width, height)
        if (bitmap != null) {
            val drawable = BitmapDrawable(gContext.resources, bitmap)
            drawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)
            drawable.setDither(true)
            return drawable
        }
        return null
    }

    fun getMarkTextBitmap(
        gContext: Context,
        gText: String,
        width: Int,
        height: Int
    ): Bitmap? {
        val textSize: Float = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            18f,
            gContext.resources.displayMetrics
        )
        val inter: Float = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            25f,
            gContext.resources.displayMetrics
        )

        val sideLength: Int = if (width > height) {
            Math.sqrt((2 * (width * width)).toDouble()).toInt()
        } else {
            Math.sqrt((2 * (height * height)).toDouble()).toInt()
        }
        Log.e(TAG, "sideLength=$sideLength")

        val paint = Paint(ANTI_ALIAS_FLAG)
        val rect = Rect()
        paint.textSize = textSize
        //获取文字长度和宽度
        paint.getTextBounds(gText, 0, gText.length, rect)

        val strwid = rect.width()
        val strhei = rect.height()
        Log.e(TAG, "strwid=$strwid,strhei=$strhei")

        var markBitmap: Bitmap? = null
        try {
            markBitmap = Bitmap.createBitmap(sideLength, sideLength, Bitmap.Config.ARGB_4444)
            val canvas = Canvas(markBitmap)
            //创建透明画布
            canvas.drawColor(Color.TRANSPARENT)

            paint.color = Color.BLACK
            paint.alpha = (0.1 * 255f).toInt()
            // 获取跟清晰的图像采样
            paint.isDither = true
            paint.isFilterBitmap = true

            //先平移，再旋转才不会有空白，使整个图片充满
            if (width > height) {
                canvas.translate(
                    width.toFloat() - sideLength.toFloat() - inter,
                    sideLength - width + inter
                )
            } else {
                canvas.translate(
                    height.toFloat() - sideLength.toFloat() - inter,
                    sideLength - height + inter
                )
            }

            //将该文字图片逆时针方向倾斜45度
            canvas.rotate((-45).toFloat())

            var i = 0
            while (i <= sideLength) {
                var count = 0
                var j = 0
                while (j <= sideLength) {
                    if (count % 2 == 0) {
                        canvas.drawText(gText, i.toFloat(), j.toFloat(), paint)
                    } else {
                        //偶数行进行错开
                        canvas.drawText(gText, (i + strwid / 2).toFloat(), j.toFloat(), paint)
                    }
                    j = (j.toFloat() + inter + strhei.toFloat()).toInt()
                    count++
                }
                i = (i.toFloat() + strwid.toFloat() + inter).toInt()
            }
            canvas.save()
        } catch (e: OutOfMemoryError) {
            if (markBitmap != null && !markBitmap.isRecycled) {
                markBitmap.recycle()
                markBitmap = null
            }
        }

        return markBitmap
    }

}
