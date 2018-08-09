package com.megvii.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.megvii.api.annotation.CheckDelta;
import com.megvii.api.annotation.FailWhenMultipleFaces;
import com.megvii.api.annotation.MultiOrientedDetection;
import com.megvii.api.annotation.ReturnFaces;
import com.megvii.api.okhttp.OkHttpManager;
import com.megvii.api.okhttp.OkHttpRequest;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

@SuppressWarnings("unused")
public final class FaceIDApi
{
    /** Detect 接口回调id */
    public static final int API_ID_DETECT = 101;
    /** IDCardOCR（V1版本） 接口回调id */
    public static final int API_ID_OCR_IDCARD_V1 = 102;
    /** IDCardOCR（V2版本） 接口回调id */
    public static final int API_ID_OCR_IDCARD_V2 = 103;
    /** BankCardOCR 接口回调id */
    public static final int API_ID_OCR_BANKCARD = 104;
    /** Verify 接口回调id */
    public static final int API_ID_VERIFY = 105;

    private static final String VERIFY_COMPARISON_TYPE_SOURCE_NO = "0";
    private static final String VERIFY_COMPARISON_TYPE_SOURCE_YES = "1";
    private static final String VERIFY_FACE_IMAGE_TYPE_MEGLIVE = "meglive";
    private static final String VERIFY_FACE_IMAGE_TYPE_FACETOKEN = "facetoken";
    private static final String VERIFY_FACE_IMAGE_TYPE_RAW_IMAGE = "raw_image";
    private static final String VERIFY_FACE_IMAGE_TYPE_MEGLIVE_FLASH = "meglive_flash";

    private static final String CONTENT_TYPE_IMAGE = "image/jpg";

    private FaceIDConfig mFaceIDConfig;
    private FaceIDApiCallback mFaceIDApiCallback;
    private static volatile FaceIDApi mInstance;

    private FaceIDApi()
    {

    }

    /**
     * 获取实例对象
     * @return FaceIDApi对象
     */
    public static FaceIDApi getInstance()
    {
        if (mInstance == null)
        {
            synchronized (FaceIDApi.class)
            {
                if (mInstance == null)
                {
                    mInstance = new FaceIDApi();
                }
            }
        }
        return mInstance;
    }

    /**
     * FaceIDApi 配置初始化
     * @param config FaceIDConfig 全局配置
     */
    public void init(@NonNull FaceIDConfig config)
    {
        this.mFaceIDConfig = config;
        OkHttpManager.getInstance().init(mFaceIDConfig);
    }

    private void checkIsInitFinished()
    {
        if (mFaceIDConfig == null)
        {
            throw new NullPointerException("FaceIDConfig is NULL! \n" + "Please check if the init() method is called.");
        }
    }

    public FaceIDConfig getFaceIDConfig()
    {
        return mFaceIDConfig;
    }

    /**
     * 设置网络回调监听器
     * @param callback 回调监听实例
     */
    public void setFaceIDApiCallback(FaceIDApiCallback callback)
    {
        this.mFaceIDApiCallback = callback;
    }

    /**
     * 检测一张照片中的人脸，并且将检测出的人脸保存到FaceID平台里，便于后续的人脸比对
     * @param detectImage 真人人脸照片
     * @param multiOrientedDetection 是否启动旋转检测；"1"：启动 "0"：不启动
     */
    public void Detect(@NonNull File detectImage, @MultiOrientedDetection int multiOrientedDetection)
    {
        checkIsInitFinished();
        if (!detectImage.exists())
            throw new NullPointerException("detectImage is not exist!");
        final String API_DETECT_URL = "https://api.faceid.com/faceid/v1/detect";
        Map<String, Object> paramMap = createParamMap();
        paramMap.put(FaceIDConst.API_PARAM_IMAGE, detectImage);
        paramMap.put(FaceIDConst.API_PARAM_MULTI_ORIENTED_DETECTION, multiOrientedDetection == 1 ? 1 : 0);
        post(API_ID_DETECT, OkHttpRequest.createFilePostRequest(API_DETECT_URL, paramMap, CONTENT_TYPE_IMAGE));
    }

    /**
     * 检测一张照片中的人脸，并且将检测出的人脸保存到FaceID平台里，便于后续的人脸比对
     * @param detectData 真人人脸数据流
     * @param multiOrientedDetection 是否启动旋转检测；"1"：启动 "0"：不启动
     */
    public void Detect(@NonNull byte[] detectData, @MultiOrientedDetection int multiOrientedDetection)
    {
        checkIsInitFinished();
        final String API_DETECT_URL = "https://api.faceid.com/faceid/v1/detect";
        Map<String, Object> paramMap = createParamMap();
        paramMap.put(FaceIDConst.API_PARAM_IMAGE, detectData);
        paramMap.put(FaceIDConst.API_PARAM_MULTI_ORIENTED_DETECTION, multiOrientedDetection == 1 ? 1 : 0);
        post(API_ID_DETECT, OkHttpRequest.createFilePostRequest(API_DETECT_URL, paramMap, CONTENT_TYPE_IMAGE));
    }

    /**
     * 检测和识别中华人民共和国第二代身份证
     * @param idcardImage 一个身份证照图片，二进制文件
     * @param legality 返回身份证照片合法性检查结果；"1"：返回 "0"：不返回
     * @version V1
     */
    public void IDCardOCR_V1(@NonNull File idcardImage, @Nullable String legality)
    {
        checkIsInitFinished();
        final String API_OCR_IDCARD_URL = "https://api.faceid.com/faceid/v1/ocridcard";
        Map<String, Object> paramMap = createParamMap();
        paramMap.put(FaceIDConst.API_PARAM_IMAGE, idcardImage);
        if (!TextUtils.isEmpty(legality))
            paramMap.put(FaceIDConst.API_PARAM_LEGALITY, legality);
        post(API_ID_OCR_IDCARD_V1, OkHttpRequest.createFilePostRequest(API_OCR_IDCARD_URL, paramMap, CONTENT_TYPE_IMAGE));
    }

    /**
     * 检测和识别中华人民共和国第二代身份证
     * @param idcardImage 一个身份证照图片，二进制文件
     * @param returnPortrait 是否返回身份证上的人像；"1"：返回 "0"：不返回
     * @version V2
     */
    public void IDCardOCR_V2(@NonNull File idcardImage, @Nullable String returnPortrait)
    {
        checkIsInitFinished();
        final String API_OCR_IDCARD_URL = "https://api.megvii.com/faceid/v3/ocridcard";
        Map<String, Object> paramMap = createParamMap();
        paramMap.put(FaceIDConst.API_PARAM_IMAGE, idcardImage);
        if (!TextUtils.isEmpty(returnPortrait))
            paramMap.put(FaceIDConst.API_PARAM_RETURN_PORTRAIT, returnPortrait);
        post(API_ID_OCR_IDCARD_V2, OkHttpRequest.createFilePostRequest(API_OCR_IDCARD_URL, paramMap, CONTENT_TYPE_IMAGE));
    }

    /**
     * 检测和识别银行卡的卡号信息
     * @param bankcardImage 一个银行卡照图片，二进制文件
     */
    public void BankCardOCR(@NonNull File bankcardImage)
    {
        checkIsInitFinished();
        final String API_OCR_BANKCARD_URL = "https://api.megvii.com/faceid/v3/ocrbankcard";
        Map<String, Object> paramMap = createParamMap();
        paramMap.put(FaceIDConst.API_PARAM_IMAGE, bankcardImage);
        post(API_ID_OCR_BANKCARD, OkHttpRequest.createFilePostRequest(API_OCR_BANKCARD_URL, paramMap, CONTENT_TYPE_IMAGE));
    }

    /**
     * 创建一个Verify有源比对请求对象
     * @param idcardName 身份证号
     * @param idcardNumber 身份证号
     * @param multiOrientedDetection 对image参数和image_ref[x]参数启用图片旋转检测功能；"1"：启用 "0"：不启用
     * @param imageRef1 参照人脸照片1
     * @param imageRef2 参照人脸照片2
     * @param imageRef3 参照人脸照片3
     * @return Verify有源比对请求对象
     */
    public Verify Verify(@NonNull String idcardName, @NonNull String idcardNumber, @MultiOrientedDetection String multiOrientedDetection, File imageRef1, File imageRef2, File imageRef3)
    {
        return new Verify(idcardName, idcardNumber, multiOrientedDetection, imageRef1, imageRef2, imageRef3);
    }

    /**
     * 创建一个Verify无源比对请求对象
     * @param uuid 标志本次识别对应的用户的id
     * @param imageRef1 参照人脸照片1（必须）
     * @param multiOrientedDetection 对image参数和image_ref[x]参数启用图片旋转检测功能；"1"：启用 "0"：不启用
     * @param imageRef2 参照人脸照片2
     * @param imageRef3 参照人脸照片3
     * @return Verify无源比对请求对象
     */
    public Verify Verify(@NonNull String uuid, @NonNull File imageRef1, @MultiOrientedDetection String multiOrientedDetection, File imageRef2, File imageRef3)
    {
        return new Verify(uuid, imageRef1, multiOrientedDetection, imageRef2, imageRef3);
    }

    private Map<String, Object> createParamMap()
    {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put(FaceIDConst.API_PARAM_API_KEY, mFaceIDConfig.getApiKey());
        paramMap.put(FaceIDConst.API_PARAM_API_SECRET, mFaceIDConfig.getApiSecret());
        return paramMap;
    }

    private void OnResponseCallback(final int apiId, @NonNull Response response) throws IOException
    {
        if (mFaceIDApiCallback != null)
        {
            int code = response.code();
            ResponseBody body = response.body();
            if (body != null)
            {
                String json = body.string();
                mFaceIDApiCallback.onResponse(apiId, code, json);
            }
        }
    }

    private void OnFailureCallback(final int apiId, @NonNull IOException e)
    {
        if (mFaceIDApiCallback != null)
            mFaceIDApiCallback.onFailure(apiId, e);
    }

    public class Verify
    {
        private final String API_VERIFY_URL = "https://api.megvii.com/faceid/v2/verify";

        private Map<String, Object> paramMap;

        /**
         * 构造器：有源比对
         * @param idcardName 身份证号
         * @param idcardNumber 身份证号
         * @param multiOrientedDetection 对image参数和image_ref[x]参数启用图片旋转检测功能；"1"：启用 "0"：不启用
         * @param imageRef1 参照人脸照片1
         * @param imageRef2 参照人脸照片2
         * @param imageRef3 参照人脸照片3
         */
        private Verify(String idcardName, String idcardNumber, String multiOrientedDetection, File imageRef1, File imageRef2, File imageRef3)
        {
            this(VERIFY_COMPARISON_TYPE_SOURCE_YES, multiOrientedDetection);
            paramMap.put(FaceIDConst.API_PARAM_IDCARD_NAME, idcardName);
            paramMap.put(FaceIDConst.API_PARAM_IDCARD_NUMBER, idcardNumber);
            if (imageRef1 != null)
                paramMap.put(FaceIDConst.API_PARAM_IMAGE_REF1, imageRef1);
            if (imageRef2 != null)
                paramMap.put(FaceIDConst.API_PARAM_IMAGE_REF2, imageRef2);
            if (imageRef3 != null)
                paramMap.put(FaceIDConst.API_PARAM_IMAGE_REF3, imageRef3);
        }

        /**
         * 构造器：无源比对
         * @param uuid 标志本次识别对应的用户的id
         * @param imageRef1 参照人脸照片1（必须）
         * @param multiOrientedDetection 对image参数和image_ref[x]参数启用图片旋转检测功能；"1"：启用 "0"：不启用
         * @param imageRef2 参照人脸照片2
         * @param imageRef3 参照人脸照片3
         */
        private Verify(String uuid, File imageRef1, String multiOrientedDetection, File imageRef2, File imageRef3)
        {
            this(VERIFY_COMPARISON_TYPE_SOURCE_NO, multiOrientedDetection);
            paramMap.put(FaceIDConst.API_PARAM_UUID, uuid);
            paramMap.put(FaceIDConst.API_PARAM_IMAGE_REF1, imageRef1);
            if (imageRef2 != null)
                paramMap.put(FaceIDConst.API_PARAM_IMAGE_REF2, imageRef2);
            if (imageRef3 != null)
                paramMap.put(FaceIDConst.API_PARAM_IMAGE_REF3, imageRef3);
        }

        /**
         * 构造器
         * @param comparisonType 确定本次比对为“有源比对”或“无源比对”
         * @param multiOrientedDetection 对image参数和image_ref[x]参数启用图片旋转检测功能；"1"：启用 "0"：不启用
         */
        private Verify(String comparisonType, String multiOrientedDetection)
        {
            checkIsInitFinished();
            paramMap = createParamMap();
            paramMap.put(FaceIDConst.API_PARAM_COMPARISON_TYPE, comparisonType);
            if (!TextUtils.isEmpty(multiOrientedDetection))
                paramMap.put(FaceIDConst.API_PARAM_MULTI_ORIENTED_DETECTION, multiOrientedDetection);
        }

        /**
         * 配合MegLiveSDK使用时，进行比对调用此方法
         * @param delta 校验上传数据的校验字符串
         * @param imageBest MegLiveSDK返回的质量最佳的人脸图片
         * @param imageEnv MegLiveSDK返回的带环境图
         * @param imageAction1 活体检测图片1
         * @param imageAction2 活体检测图片2
         * @param imageAction3 活体检测图片3
         * @param imageAction4 活体检测图片4
         * @param imageAction5 活体检测图片5
         * @param checkDelta 校验delta是否已经使用过；"1"：校验 "0"：不校验
         */
        public void ByMeglive(@NonNull String delta, @NonNull File imageBest, File imageEnv, File imageAction1, File imageAction2, File imageAction3, File imageAction4, File imageAction5, @CheckDelta String checkDelta)
        {
            paramMap.put(FaceIDConst.API_PARAM_FACE_IMAGE_TYPE, VERIFY_FACE_IMAGE_TYPE_MEGLIVE);
            paramMap.put(FaceIDConst.API_PARAM_DELTA, delta);
            paramMap.put(FaceIDConst.API_PARAM_IMAGE_BEST, imageBest);
            if (imageEnv != null)
                paramMap.put(FaceIDConst.API_PARAM_IMAGE_ENV, imageEnv);
            if (imageAction1 != null)
                paramMap.put(FaceIDConst.API_PARAM_IMAGE_ACTION1, imageAction1);
            if (imageAction2 != null)
                paramMap.put(FaceIDConst.API_PARAM_IMAGE_ACTION2, imageAction2);
            if (imageAction3 != null)
                paramMap.put(FaceIDConst.API_PARAM_IMAGE_ACTION3, imageAction3);
            if (imageAction4 != null)
                paramMap.put(FaceIDConst.API_PARAM_IMAGE_ACTION4, imageAction4);
            if (imageAction5 != null)
                paramMap.put(FaceIDConst.API_PARAM_IMAGE_ACTION5, imageAction5);
            if (!TextUtils.isEmpty(checkDelta))
                paramMap.put(FaceIDConst.API_PARAM_CHECK_DELTA, checkDelta);
            post(API_ID_VERIFY, OkHttpRequest.createFilePostRequest(API_VERIFY_URL, paramMap, CONTENT_TYPE_IMAGE));
        }

        /**
         * 调用detect后获得facetoken时，进行比对调用此方法
         * @param faceToken 使用detect接口获得的一个标示人脸的token
         */
        public void ByFaceToken(@NonNull String faceToken)
        {
            paramMap.put(FaceIDConst.API_PARAM_FACE_IMAGE_TYPE, VERIFY_FACE_IMAGE_TYPE_FACETOKEN);
            paramMap.put(FaceIDConst.API_PARAM_FACE_TOKEN, faceToken);
            post(API_ID_VERIFY, OkHttpRequest.createFilePostRequest(API_VERIFY_URL, paramMap, CONTENT_TYPE_IMAGE));
        }

        /**
         * 直接上传一张照片时，进行比对调用此方法
         * @param image 待比对的人脸照片
         * @param failWhenMultipleFaces 对验证照作人脸检测时发现有多张脸，是否立即返回错误，或者取最大的一张脸继续比对；"1"：立即返回错误码 "0"：取最大脸继续比对
         * @param faceQualityThreshold 验证照中（最大的一张）人脸图像质量分的阈值（缺省值为30）
         * @param returnFaces 返回人脸检测结果；"1"：返回 "0"：不返回
         */
        public void ByRawImage(@NonNull File image, @FailWhenMultipleFaces String failWhenMultipleFaces, String faceQualityThreshold, @ReturnFaces String returnFaces)
        {
            paramMap.put(FaceIDConst.API_PARAM_FACE_IMAGE_TYPE, VERIFY_FACE_IMAGE_TYPE_RAW_IMAGE);
            if (!TextUtils.isEmpty(failWhenMultipleFaces))
                paramMap.put(FaceIDConst.API_PARAM_FAIL_WHEN_MULTIPLE_FACES, failWhenMultipleFaces);
            if (!TextUtils.isEmpty(faceQualityThreshold))
                paramMap.put(FaceIDConst.API_PARAM_FACE_QUALITY_THRESHOLD, faceQualityThreshold);
            if (!TextUtils.isEmpty(returnFaces))
                paramMap.put(FaceIDConst.API_PARAM_RETURN_FACES, returnFaces);
            post(API_ID_VERIFY, OkHttpRequest.createFilePostRequest(API_VERIFY_URL, paramMap, CONTENT_TYPE_IMAGE));
        }

        /**
         * 配合MegLiveFlash（炫彩活体）SDK使用时，进行比对调用此方法
         * @param megliveFlashResult 在 MegLiveFlash（炫彩活体）成功时会生成并返回一个文件
         */
        public void ByMegliveFlash(@NonNull File megliveFlashResult)
        {
            paramMap.put(FaceIDConst.API_PARAM_FACE_IMAGE_TYPE, VERIFY_FACE_IMAGE_TYPE_MEGLIVE_FLASH);
            paramMap.put(FaceIDConst.API_PARAM_MEGLIVE_FLASH_RESULT, megliveFlashResult);
            post(API_ID_VERIFY, OkHttpRequest.createFilePostRequest(API_VERIFY_URL, paramMap, CONTENT_TYPE_IMAGE));
        }
    }

    private void post(final int apiId, Request request)
    {
        OkHttpManager.getInstance().async(request, new Callback()
        {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e)
            {
                OnFailureCallback(apiId, e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException
            {
                OnResponseCallback(apiId, response);
            }
        });
    }
}
