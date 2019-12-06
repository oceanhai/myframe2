package com.wuhai.myframe2.utils;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageUtil {

    public static Bitmap getSampledBitmap(String photoPath, int limitLength) {
        return getSampledBitmap(photoPath, limitLength, false);
    }

    public static Bitmap getSampledBitmap(String photoPath, int limitLength, boolean isMinLength) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(photoPath, options);
        options.inSampleSize = computeSampleSize(options, limitLength, -1, isMinLength);
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        return BitmapFactory.decodeFile(photoPath, options);
    }


    public static Bitmap getSampledBitmap(String photoPath, int width, int height) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(photoPath, options);
        options.inSampleSize = computeSampleSize(options,width,height);
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        return BitmapFactory.decodeFile(photoPath, options);
    }

    private static int computeSampleSize(BitmapFactory.Options options,int ww, int hh){
        int w = options.outWidth;
        int h = options.outHeight;
        int be = 1;
        if (w > h && w > ww) {
            be = options.outWidth / ww;
        } else if (w < h && h > hh) {
            be = options.outHeight / hh;
        }
        if (be <= 0)
            be = 1;
        return be;
    }

    /**
     * compute Sample Size
     *
     * @param options
     * @param minSideLength
     * @param maxNumOfPixels
     * @return
     */
    public static int computeSampleSize(BitmapFactory.Options options, int minSideLength,
                                        int maxNumOfPixels, boolean isMinLength) {
        int initialSize = computeInitialSampleSize(options, minSideLength, maxNumOfPixels, isMinLength);

        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }

        return roundedSize;
    }

    /**
     * compute Initial Sample Size
     *
     * @param options
     * @param minSideLength
     * @param maxNumOfPixels
     * @return
     */
    private static int computeInitialSampleSize(BitmapFactory.Options options, int minSideLength,
                                                int maxNumOfPixels, boolean isMinLength) {
        double w = options.outWidth;
        double h = options.outHeight;

        // 上下限范围
        int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));
        int upperBound = 1;
        if (isMinLength) {
            upperBound = (minSideLength == -1) ? 128 : (int) Math.min(Math.floor(w / minSideLength),
                    Math.floor(h / minSideLength));
        } else {
            upperBound = (minSideLength == -1) ? 128 : (int) Math.max(Math.floor(w / minSideLength)
                    , Math.floor(h / minSideLength));
        }

        if (upperBound < lowerBound) {
            // return the larger one when there is no overlapping zone.
            return lowerBound;
        }

        if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }

    public static Bitmap createThumbnail(String path, int minSideLength, int maxNumOfPixels) throws FileNotFoundException {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(new FileInputStream(path), null, options);
        options.inJustDecodeBounds = false;

        options.inSampleSize = computeSampleSize(options, minSideLength, maxNumOfPixels, true);

        Bitmap bmp = null;
        try {
            bmp = BitmapFactory.decodeStream(new FileInputStream(path), null, options); // 返回缩略图
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            bmp = null;
        }

        if (bmp != null) {
            int degree = getPhotoOrientation(path);
            if (degree != 0) {
                Bitmap destBitmap = rotateImg(degree, bmp);
                if (!bmp.isRecycled() && bmp != destBitmap) {
                    bmp.recycle();
                    bmp = null;
                }
                return destBitmap;
            }
        }

        return bmp;
    }

    public static Bitmap createThumbnail(byte[] data, int minSideLength, int maxNumOfPixels) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(data, 0, data.length, options);
        options.inJustDecodeBounds = false;

        options.inSampleSize = computeSampleSize(options, minSideLength, maxNumOfPixels, true);
        Bitmap bmp = null;
        try {
            bmp = BitmapFactory.decodeByteArray(data, 0, data.length, options); // 返回缩略图
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            bmp = null;
        }
        return bmp;
    }

    public static int getPhotoOrientation(String path) {
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int orientation = 0;
        if (exif != null) {
            orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    orientation = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    orientation = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    orientation = 270;
                    break;
                default:
                    orientation = 0;
                    break;
            }
        }

        return orientation;
    }

    private static Bitmap rotateImg(int degree, Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static Bitmap createOnePageBitmap(Context context, Bitmap source) {
        if (source == null) {
            return null;
        }
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float whScale = (float) metrics.widthPixels / metrics.heightPixels;
        int width = (int) (whScale * source.getHeight());
        int height = source.getHeight();

        if (source.getWidth() > width) {
            return Bitmap.createBitmap(source, (source.getWidth() - width) / 2, 0, width, height);
        } else {
            return source;
        }

    }

    public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config
                .ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    public static boolean saveImageByteToFile(byte[] imageByte, String path) {
        File file = new File(path);

        if (file.exists() && !file.delete()) {
            return false;
        }

        try {
            FileOutputStream fos = new FileOutputStream(path);
            fos.write(imageByte);
            fos.close();
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public static boolean savaBitmapToFile(Bitmap bitmap, String path) {
        File file = new File(path);

        if (file.exists() && !file.delete()) {
            return false;
        }

        try {
            FileOutputStream fos = new FileOutputStream(path);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] bytes = stream.toByteArray();

            fos.write(bytes);
            fos.close();
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public static String getImageFilePath(Context context) {
        return getImageFilePath(context, "defaultTempImage.jpg");
    }

    public static String getImageFilePath(Context context, String fileName) {
        File mediaStorageDir = new File(DeviceUtil.getBaseFilePath(context) + File.separator +
                "myframe2/picture");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return "";
            }
        }

        // Create a media file name
        return mediaStorageDir.getPath() + File.separator + fileName;
    }

    //----------------------------------------------------------------------------------

    //4.4及以上系统使用这个方法处理图片
    @TargetApi(19)
    public static Bitmap handleImageOnKitKat(Context context, Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(context, uri)) {
            //如果是document类型的Uri,则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];  //解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(context, contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            //如果不是document类型的Uri,则使用普通方式处理
            imagePath = getImagePath(context, uri, null);
        }
        return getImage(imagePath);
    }

    //4.4以下系统使用这个方法处理图片
    public static Bitmap handleImageBeforeKitKat(Context context, Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(context, uri, null);
        return getImage(imagePath);
    }

    public static String getImagePath(Context context, Uri uri, String selection) {
        String path = null;
        //通过Uri和selection来获取真实的图片路径
        Cursor cursor = context.getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    //对bitmap进行质量压缩
    public static Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) {    //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    /**
     * 按比例缩放图片
     *
     * @param origin 原图
     * @param ratio  比例
     * @return 新的bitmap
     */
    public static Bitmap scaleBitmap(Bitmap origin, float ratio) {
        if (origin == null) {
            return null;
        }
        int width = origin.getWidth();
        int height = origin.getHeight();
        Matrix matrix = new Matrix();
        matrix.preScale(ratio, ratio);
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
        if (newBM.equals(origin)) {
            return newBM;
        }
        origin.recycle();
        return newBM;
    }

    //传入图片路径，返回压缩后的bitmap
    public static Bitmap getImage(String srcPath) {
        if (TextUtils.isEmpty(srcPath))  //如果图片路径为空 直接返回
            return null;
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);//此时返回bm为空

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 1280f;//这里设置高度为800f
        float ww = 720f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
//        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
//            be = (int) (newOpts.outWidth / ww);
//        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
//            be = (int) (newOpts.outHeight / hh);
//        }
        if (w > h && w > hh) {//横屏拍照
            be = (int) (newOpts.outWidth / hh);
        } else if (w < h && h > hh) {//竖屏拍照
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return compressImage(bitmap);//压缩好比例大小后再进行质量压缩
    }

    //------------------------------------------------------------------
    //修改图片size（单词应该是revision）
    public static Bitmap revitionImageSize(String path) throws IOException {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(
                new File(path)));
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(in, null, options);
        in.close();
        int i = 0;
        Bitmap bitmap = null;
        while (true) {
            if ((options.outWidth >> i <= 2000)
                    && (options.outHeight >> i <= 2000)) {//右移i位（移除部分被抛弃）再跟1000比较
                in = new BufferedInputStream(
                        new FileInputStream(new File(path)));
                options.inSampleSize = (int) Math.pow(2.0D, i);//返回第一个参数的第二个参数次幂的值
                options.inJustDecodeBounds = false;
                bitmap = BitmapFactory.decodeStream(in, null, options);//压缩图片
                break;
            }
            i += 1;
        }
        return bitmap;
    }

    /**
     * 等比例缩放，取最小的比例
     * @param maxWidth
     * @param maxHeight
     * @param path
     * @return
     */
    public static Bitmap getSampleBitmap(int maxWidth, int maxHeight,
                                         String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(path, options);
        int inSampleSize = Math.min(options.outWidth / maxWidth,
                options.outHeight / maxHeight);
        options.inJustDecodeBounds = false;
        options.inSampleSize = inSampleSize;
        return BitmapFactory.decodeFile(path, options);
    }
}
