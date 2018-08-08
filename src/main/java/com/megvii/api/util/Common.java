package com.megvii.api.util;

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
     * @return 转换是否成功
     */
    public static boolean convert2File(String filePath, String base64)
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
        return file.exists();
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

}
