package com.megvii.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.megvii.api.annotation.CheckDelta;
import com.megvii.api.annotation.ComparisonType;
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
import okhttp3.Response;
import okhttp3.ResponseBody;

@SuppressWarnings("unused")
public final class FaceIDApi
{
    public static final int API_ID_DETECT = 101;
    public static final int API_ID_OCR_IDCARD_V1 = 102;
    public static final int API_ID_OCR_IDCARD_V2 = 103;
    public static final int API_ID_OCR_BANKCARD = 104;
    public static final int API_ID_VERIFY = 105;

    private static final String VERIFY_COMPARISON_TYPE_SOURCE_NO = "0";
    private static final String VERIFY_COMPARISON_TYPE_SOURCE_YES = "1";
    private static final String VERIFY_FACE_IMAGE_TYPE_MEGLIVE = "meglive";
    private static final String VERIFY_FACE_IMAGE_TYPE_FACETOKEN = "facetoken";
    private static final String VERIFY_FACE_IMAGE_TYPE_RAW_IMAGE = "raw_image";
    private static final String VERIFY_FACE_IMAGE_TYPE_MEGLIVE_FLASH = "meglive_flash";

    public static final String CONTENT_TYPE_IMAGE = "image/jpg";

    private FaceIDConfig mFaceIDConfig;
    private FaceIDApiCallback mFaceIDApiCallback;
    private static volatile FaceIDApi mInstance;

    private FaceIDApi()
    {

    }

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

    public void setFaceIDApiCallback(FaceIDApiCallback callback)
    {
        this.mFaceIDApiCallback = callback;
    }

    public void Detect(@NonNull File detectImage, int multiOrientedDetection)
    {
        checkIsInitFinished();
        final String API_DETECT_URL = "https://api.faceid.com/faceid/v1/Detect";
        Map<String, Object> paramMap = createParamMap();
        paramMap.put(FaceIDConst.API_PARAM_IMAGE, detectImage);
        paramMap.put(FaceIDConst.API_PARAM_MULTI_ORIENTED_DETECTION, multiOrientedDetection == 1 ? 1 : 0);
        OkHttpManager.getInstance().async(OkHttpRequest.createFilePostRequest(API_DETECT_URL, paramMap, CONTENT_TYPE_IMAGE), new Callback()
        {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e)
            {
                OnFailureCallback(API_ID_DETECT, call, e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException
            {
                OnResponseCallback(API_ID_DETECT, call, response);
            }
        });
    }

    public void IDCardOCR_V1(@NonNull File idcardImage, @Nullable String legality)
    {
        checkIsInitFinished();
        final String API_OCR_IDCARD_URL = "https://api.faceid.com/faceid/v1/ocridcard";
        Map<String, Object> paramMap = createParamMap();
        paramMap.put(FaceIDConst.API_PARAM_IMAGE, idcardImage);
        if (!TextUtils.isEmpty(legality))
            paramMap.put(FaceIDConst.API_PARAM_LEGALITY, legality);
        OkHttpManager.getInstance().async(OkHttpRequest.createFilePostRequest(API_OCR_IDCARD_URL, paramMap, CONTENT_TYPE_IMAGE), new Callback()
        {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e)
            {
                OnFailureCallback(API_ID_OCR_IDCARD_V1, call, e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException
            {
                OnResponseCallback(API_ID_OCR_IDCARD_V1, call, response);
            }
        });
    }

    public void IDCardOCR_V2(@NonNull File idcardImage, @Nullable String returnPortrait)
    {
        checkIsInitFinished();
        final String API_OCR_IDCARD_URL = "https://api.megvii.com/faceid/v3/ocridcard";
        Map<String, Object> paramMap = createParamMap();
        paramMap.put(FaceIDConst.API_PARAM_IMAGE, idcardImage);
        if (!TextUtils.isEmpty(returnPortrait))
            paramMap.put(FaceIDConst.API_PARAM_RETURN_PORTRAIT, returnPortrait);
        OkHttpManager.getInstance().async(OkHttpRequest.createFilePostRequest(API_OCR_IDCARD_URL, paramMap, CONTENT_TYPE_IMAGE), new Callback()
        {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e)
            {
                OnFailureCallback(API_ID_OCR_IDCARD_V2, call, e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException
            {
                OnResponseCallback(API_ID_OCR_IDCARD_V2, call, response);
            }
        });
    }

    public void BankCardOCR(@NonNull File bankcardImage)
    {
        checkIsInitFinished();
        final String API_OCR_BANKCARD_URL = "https://api.megvii.com/faceid/v3/ocrbankcard";
        Map<String, Object> paramMap = createParamMap();
        paramMap.put(FaceIDConst.API_PARAM_IMAGE, bankcardImage);
        OkHttpManager.getInstance().async(OkHttpRequest.createFilePostRequest(API_OCR_BANKCARD_URL, paramMap, CONTENT_TYPE_IMAGE), new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e)
            {
                OnFailureCallback(API_ID_OCR_BANKCARD, call, e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException
            {
                OnResponseCallback(API_ID_OCR_BANKCARD, call, response);
            }
        });
    }

    public Verify Verify(@NonNull String idcardName, @NonNull String idcardNumber, File imageRef1, File imageRef2, File imageRef3, @MultiOrientedDetection String multiOrientedDetection)
    {
        return new Verify(idcardName, idcardNumber, imageRef1, imageRef2, imageRef3, multiOrientedDetection);
    }

    public Verify Verify(@NonNull String uuid, @NonNull File imageRef1, File imageRef2, File imageRef3, @MultiOrientedDetection String multiOrientedDetection)
    {
        return new Verify(uuid, imageRef1, imageRef2, imageRef3, multiOrientedDetection);
    }

    private Map<String, Object> createParamMap()
    {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put(FaceIDConst.API_PARAM_API_KEY, mFaceIDConfig.getApiKey());
        paramMap.put(FaceIDConst.API_PARAM_API_SECRET, mFaceIDConfig.getApiSecret());
        return paramMap;
    }

    private void OnResponseCallback(final int apiId, @NonNull Call call, @NonNull Response response) throws IOException
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

    private void OnFailureCallback(final int apiId, @NonNull Call call, @NonNull IOException e)
    {
        if (mFaceIDApiCallback != null)
            mFaceIDApiCallback.onFailure(apiId, call, e);
    }

    public class Verify
    {
        private final String API_VERIFY_URL = "https://api.megvii.com/faceid/v2/verify";

        private Map<String, Object> paramMap;

        private Verify(@NonNull String idcardName, @NonNull String idcardNumber, File imageRef1, File imageRef2, File imageRef3, @MultiOrientedDetection String multiOrientedDetection)
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

        private Verify(@NonNull String uuid, @NonNull File imageRef1, File imageRef2, File imageRef3, @MultiOrientedDetection String multiOrientedDetection)
        {
            this(VERIFY_COMPARISON_TYPE_SOURCE_NO, multiOrientedDetection);
            paramMap.put(FaceIDConst.API_PARAM_UUID, uuid);
            paramMap.put(FaceIDConst.API_PARAM_IMAGE_REF1, imageRef1);
            if (imageRef2 != null)
                paramMap.put(FaceIDConst.API_PARAM_IMAGE_REF2, imageRef2);
            if (imageRef3 != null)
                paramMap.put(FaceIDConst.API_PARAM_IMAGE_REF3, imageRef3);
        }

        private Verify(@ComparisonType String comparisonType, @MultiOrientedDetection String multiOrientedDetection)
        {
            checkIsInitFinished();
            paramMap = createParamMap();
            paramMap.put(FaceIDConst.API_PARAM_COMPARISON_TYPE, comparisonType);
            if (!TextUtils.isEmpty(multiOrientedDetection))
                paramMap.put(FaceIDConst.API_PARAM_MULTI_ORIENTED_DETECTION, multiOrientedDetection);
        }

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
            post();
        }

        public void ByFaceToken(@NonNull String faceToken)
        {
            paramMap.put(FaceIDConst.API_PARAM_FACE_IMAGE_TYPE, VERIFY_FACE_IMAGE_TYPE_FACETOKEN);
            paramMap.put(FaceIDConst.API_PARAM_FACE_TOKEN, faceToken);
            post();
        }

        public void ByRawImage(@NonNull File image, @FailWhenMultipleFaces String failWhenMultipleFaces, String faceQualityThreshold, @ReturnFaces String returnFaces)
        {
            paramMap.put(FaceIDConst.API_PARAM_FACE_IMAGE_TYPE, VERIFY_FACE_IMAGE_TYPE_RAW_IMAGE);
            if (!TextUtils.isEmpty(failWhenMultipleFaces))
                paramMap.put(FaceIDConst.API_PARAM_FAIL_WHEN_MULTIPLE_FACES, failWhenMultipleFaces);
            if (!TextUtils.isEmpty(faceQualityThreshold))
                paramMap.put(FaceIDConst.API_PARAM_FACE_QUALITY_THRESHOLD, faceQualityThreshold);
            if (!TextUtils.isEmpty(returnFaces))
                paramMap.put(FaceIDConst.API_PARAM_RETURN_FACES, returnFaces);
            post();
        }

        public void ByMegliveFlash(@NonNull String megliveFlashResult)
        {
            paramMap.put(FaceIDConst.API_PARAM_FACE_IMAGE_TYPE, VERIFY_FACE_IMAGE_TYPE_MEGLIVE_FLASH);
            paramMap.put(FaceIDConst.API_PARAM_MEGLIVE_FLASH_RESULT, megliveFlashResult);
            post();
        }

        private void post()
        {
            OkHttpManager.getInstance().async(OkHttpRequest.createFilePostRequest(API_VERIFY_URL, paramMap, CONTENT_TYPE_IMAGE), new Callback()
            {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e)
                {
                    OnFailureCallback(API_ID_VERIFY, call, e);
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException
                {
                    OnResponseCallback(API_ID_VERIFY, call, response);
                }
            });
        }
    }
}
