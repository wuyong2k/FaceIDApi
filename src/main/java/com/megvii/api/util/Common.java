package com.megvii.api.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Base64;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Common
{
    /**
     * base64转换为图片文件
     * @param filePath 图片文件路径
     * @param base64 图片base64数据
     * @return 转换图片
     */
    public static File convert2File(String filePath, String base64)
    {
        byte[] data = Base64.decode(base64, Base64.DEFAULT);
        File file = new File(filePath);
        try
        {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data, 0, data.length);
            fos.flush();
            fos.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * Sha1 算法
     * @param strSrc 待加密串
     * @return 加密后的串
     */
    public static String shaEncrypt(String strSrc)
    {
        MessageDigest md = null;
        String strDes = null;
        byte[] bt = strSrc.getBytes();
        try
        {
            md = MessageDigest.getInstance("SHA-1");// 将此换成SHA-1、SHA-512、SHA-384等参数
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        }
        catch (NoSuchAlgorithmException e)
        {
            return null;
        }
        return strDes;
    }

    /**
     * 数据转换16进制
     * @param bts 数据
     */
    public static String bytes2Hex(byte[] bts)
    {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++)
        {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1)
            {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

    /**
     * 截取图片中的人脸
     * @param rect 人脸框在图片中的位置占比
     * @param filePath 人脸图路径
     * @return 人脸图
     */
    public static Bitmap getFaceBitmap(RectF rect, String filePath)
    {
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        rect = new RectF(rect.left * bitmap.getWidth(), rect.top * bitmap.getHeight(), rect.right * bitmap.getWidth(), rect.bottom * bitmap.getHeight());
        return Bitmap.createBitmap(bitmap, (int) rect.left, (int) rect.top, (int) rect.width(), (int) rect.height());
    }

    public static Bitmap getFaceBitmap(PointF rt, PointF lt, PointF lb, PointF rb, String filePath)
    {
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        Bitmap nBitmap = null;
        //判断需要旋转多少度
        if ((rb.x > lt.x) && (rb.y > lt.y)) //正面
            nBitmap = shot(bitmap, lt.x, rb.x, lt.y, rb.y, 0);
        else if ((rb.x > lt.x) && (rb.y < lt.y)) // 90度
            nBitmap = shot(bitmap, rt.x, lb.x, rt.y, lb.y, 90);
        else if ((rb.x < lt.x) && (rb.y < lt.y)) //180度
            nBitmap = shot(bitmap, rb.x, lt.x, rb.y, lt.y, 180);
        else if ((rb.x < lt.x) && (rb.y > lt.y)) //270度
            nBitmap = shot(bitmap, lb.x, rt.x, lb.y, rt.y, 270);
        return nBitmap;
    }

    /**
     * 根据角度和坐标点去旋转Bitmap
     */
    public static Bitmap shot(Bitmap bitmap, double l, double r, double t, double b, int degree)
    {
        Rect rect = new Rect();
        rect.left = (int) (l * bitmap.getWidth());
        rect.right = (int) (r * bitmap.getWidth());
        rect.top = (int) (t * bitmap.getHeight());
        rect.bottom = (int) (b * bitmap.getHeight());
        Bitmap newBip = Bitmap.createBitmap(bitmap, rect.left, rect.top, rect.width(), rect.height());
        if (degree != 0)
        {
            Matrix matrix = new Matrix();
            matrix.postRotate(degree);
            int width = newBip.getWidth();
            int height = newBip.getHeight();
            newBip = Bitmap.createBitmap(newBip, 0, 0, width, height, matrix, true);
        }
        //返回处理完的图片
        return newBip;
    }
}
