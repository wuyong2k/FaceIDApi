package com.megvii.api.util;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.NonNull;

import com.megvii.api.FaceIDConst;
import com.megvii.api.entity.BankCard;
import com.megvii.api.entity.Detect;
import com.megvii.api.entity.IDCardLegality;
import com.megvii.api.entity.IDCardV1;
import com.megvii.api.entity.IDCardV2;
import com.megvii.api.entity.Verify;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtil
{
    // region API: Detect
    public static Detect json2Detect(@NonNull String body) throws NullPointerException
    {
        JSONObject json;
        Detect detect = null;
        try
        {
            json = new JSONObject(body);
            detect = new Detect();
            detect.setRequestId(json.getString(FaceIDConst.API_PARAM_REQUEST_ID));
            detect.setTimeUsed(json.getInt(FaceIDConst.API_PARAM_TIME_USED));
            detect.setFaces(json2DetectFace(detect, json.optJSONArray(FaceIDConst.API_PARAM_FACES)));
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return detect;
    }

    private static List<Detect.DetectFace> json2DetectFace(Detect detect, JSONArray jsonFaces) throws JSONException
    {
        List<Detect.DetectFace> detectFaces = null;
        if (detect != null && jsonFaces != null)
        {
            detectFaces = new ArrayList<>();
            for (int i = 0; i < jsonFaces.length(); i++)
            {
                JSONObject faceJson = jsonFaces.getJSONObject(i);
                float quality = (float) faceJson.getDouble(FaceIDConst.API_PARAM_QUALITY);
                float qualityThreshold = (float) faceJson.getDouble(FaceIDConst.API_PARAM_QUALITY_THRESHOLD);
                if (quality > qualityThreshold)
                {
                    Detect.DetectFace face = detect.new DetectFace();
                    face.setToken(faceJson.getString(FaceIDConst.API_PARAM_TOKEN));
                    face.setQuality(quality);
                    face.setQualityThreshold(qualityThreshold);
                    face.setOrientation(faceJson.optInt(FaceIDConst.API_PARAM_ORIENTATION, 0));
                    face.setRect(json2RectF(faceJson.getJSONObject(FaceIDConst.API_PARAM_RECT)));
                    detectFaces.add(face);
                }
            }
        }
        return detectFaces;
    }
    // endregion

    // region API: IDCardOCR

    // region V1
    public static IDCardV1 json2IDCardV1(@NonNull String body) throws NullPointerException
    {
        JSONObject json;
        IDCardV1 idcard = null;
        try
        {
            json = new JSONObject(body);
            idcard = new IDCardV1();
            // Common
            idcard.setRequestId(json.getString(FaceIDConst.API_PARAM_REQUEST_ID));
            idcard.setTimeUsed(json.getInt(FaceIDConst.API_PARAM_TIME_USED));
            idcard.setSide(json.getString(FaceIDConst.API_PARAM_SIDE));
            // Legality
            JSONObject jsonLegality = json.optJSONObject(FaceIDConst.API_PARAM_LEGALITY);
            if (jsonLegality != null)
                idcard.setLegality(json2IDCardLegality(jsonLegality, 1));
            if (FaceIDConst.API_PARAM_SIDE_FRONT.equals(idcard.getSide()))
            {
                IDCardV1.FrontSide frontSide = idcard.new FrontSide();
                // front side
                frontSide.setBirthday(json2IDCardV1FrontBirthday(frontSide, json.getJSONObject(FaceIDConst.API_PARAM_BIRTHDAY)));
                frontSide.setHeadRect(json2IDCardV1FrontHeadRect(frontSide, json.getJSONObject(FaceIDConst.API_PARAM_HEAD_RECT)));
                frontSide.setAddress(json.getString(FaceIDConst.API_PARAM_ADDRESS));
                frontSide.setGender(json.getString(FaceIDConst.API_PARAM_GENDER));
                frontSide.setIdCardNumber(json.getString(FaceIDConst.API_PARAM_ID_CARD_NUMBER));
                frontSide.setName(json.getString(FaceIDConst.API_PARAM_NAME));
                frontSide.setRace(json.getString(FaceIDConst.API_PARAM_RACE));
                idcard.setFrontSide(frontSide);
            }
            else if (FaceIDConst.API_PARAM_SIDE_BACK.equals(idcard.getSide()))
            {
                IDCardV1.BackSide backSide = idcard.new BackSide();
                backSide.setIssuedBy(json.optString(FaceIDConst.API_PARAM_ISSUED_BY));
                backSide.setValidDate(json.optString(FaceIDConst.API_PARAM_VALID_DATE));
                idcard.setBackSide(backSide);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return idcard;
    }

    private static IDCardV1.FrontSide.HeadRect json2IDCardV1FrontHeadRect(IDCardV1.FrontSide frontSide, JSONObject jsonHeadRect) throws JSONException
    {
        IDCardV1.FrontSide.HeadRect headRect = null;
        if (frontSide != null && jsonHeadRect != null)
        {
            headRect = frontSide.new HeadRect();
            JSONObject jsonHeadRectRT = jsonHeadRect.getJSONObject(FaceIDConst.API_PARAM_RECT_RT);
            headRect.setRt(new PointF((float) jsonHeadRectRT.getDouble(FaceIDConst.API_PARAM_RECT_X), (float) jsonHeadRectRT.getDouble(FaceIDConst.API_PARAM_RECT_Y)));
            JSONObject jsonHeadRectLT = jsonHeadRect.getJSONObject(FaceIDConst.API_PARAM_RECT_LT);
            headRect.setLt(new PointF((float) jsonHeadRectLT.getDouble(FaceIDConst.API_PARAM_RECT_X), (float) jsonHeadRectLT.getDouble(FaceIDConst.API_PARAM_RECT_Y)));
            JSONObject jsonHeadRectRB = jsonHeadRect.getJSONObject(FaceIDConst.API_PARAM_RECT_RB);
            headRect.setRb(new PointF((float) jsonHeadRectRB.getDouble(FaceIDConst.API_PARAM_RECT_X), (float) jsonHeadRectRB.getDouble(FaceIDConst.API_PARAM_RECT_Y)));
            JSONObject jsonHeadRectLB = jsonHeadRect.getJSONObject(FaceIDConst.API_PARAM_RECT_LB);
            headRect.setLb(new PointF((float) jsonHeadRectLB.getDouble(FaceIDConst.API_PARAM_RECT_X), (float) jsonHeadRectLB.getDouble(FaceIDConst.API_PARAM_RECT_Y)));
        }
        return headRect;
    }

    private static IDCardV1.FrontSide.Birthday json2IDCardV1FrontBirthday(IDCardV1.FrontSide frontSide, JSONObject jsonBirthday) throws JSONException
    {
        IDCardV1.FrontSide.Birthday birthday = null;
        if (frontSide != null && jsonBirthday != null)
        {
            birthday = frontSide.new Birthday();
            birthday.setYear(jsonBirthday.getString(FaceIDConst.API_PARAM_BIRTHDAY_YEAR));
            birthday.setMonth(jsonBirthday.getString(FaceIDConst.API_PARAM_BIRTHDAY_MONTH));
            birthday.setDay(jsonBirthday.getString(FaceIDConst.API_PARAM_BIRTHDAY_DAY));
        }
        return birthday;
    }
    // endregion

    // region V2
    public static IDCardV2 json2IDCardV2(@NonNull String body) throws NullPointerException
    {
        JSONObject json;
        IDCardV2 idcard = null;
        try
        {
            json = new JSONObject(body);
            idcard = new IDCardV2();
            idcard.setRequestId(json.getString(FaceIDConst.API_PARAM_REQUEST_ID));
            idcard.setTimeUsed(json.getInt(FaceIDConst.API_PARAM_TIME_USED));
            idcard.setResult(json.getInt(FaceIDConst.API_PARAM_RESULT));
            idcard.setSide(json.getInt(FaceIDConst.API_PARAM_SIDE));
            idcard.setCompleteness(json.getInt(FaceIDConst.API_PARAM_COMPLETENESS));
            idcard.setCardRect(json2IDCardV2OcrRect(idcard, json.getJSONObject(FaceIDConst.API_PARAM_CARD_RECT)));
            idcard.setLegality(json2IDCardLegality(json.getJSONObject(FaceIDConst.API_PARAM_LEGALITY), 2));
            if (idcard.getSide() == 0)
            {
                IDCardV2.FrontSide frontSide = idcard.new FrontSide();
                frontSide.setAddress(json2IDCardV2OcrStruct(idcard, json.getJSONObject(FaceIDConst.API_PARAM_ADDRESS)));
                frontSide.setBirthYear(json2IDCardV2OcrStruct(idcard, json.getJSONObject(FaceIDConst.API_PARAM_BIRTH_YEAR)));
                frontSide.setBirthMonth(json2IDCardV2OcrStruct(idcard, json.getJSONObject(FaceIDConst.API_PARAM_BIRTH_MONTH)));
                frontSide.setBirthDay(json2IDCardV2OcrStruct(idcard, json.getJSONObject(FaceIDConst.API_PARAM_BIRTH_DAY)));
                frontSide.setGender(json2IDCardV2OcrStruct(idcard, json.getJSONObject(FaceIDConst.API_PARAM_GENDER)));
                frontSide.setIdcardNumber(json2IDCardV2OcrStruct(idcard, json.getJSONObject(FaceIDConst.API_PARAM_IDCARD_NUMBER)));
                frontSide.setName(json2IDCardV2OcrStruct(idcard, json.getJSONObject(FaceIDConst.API_PARAM_NAME)));
                frontSide.setNationality(json2IDCardV2OcrStruct(idcard, json.getJSONObject(FaceIDConst.API_PARAM_NATIONALITY)));
                frontSide.setPortrait(json2IDCardV2OcrStruct(idcard, json.optJSONObject(FaceIDConst.API_PARAM_PORTRAIT)));
                idcard.setFrontSide(frontSide);
            }
            else if (idcard.getSide() == 1)
            {
                IDCardV2.BackSide backSide = idcard.new BackSide();
                backSide.setIssuedBy(json2IDCardV2OcrStruct(idcard, json.getJSONObject(FaceIDConst.API_PARAM_ISSUED_BY)));
                backSide.setValidDateStart(json2IDCardV2OcrStruct(idcard, json.getJSONObject(FaceIDConst.API_PARAM_VALID_DATE_START)));
                backSide.setValidDateEnd(json2IDCardV2OcrStruct(idcard, json.getJSONObject(FaceIDConst.API_PARAM_VALID_DATE_END)));
                idcard.setBackSide(backSide);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return idcard;
    }

    private static IDCardV2.IDCardOcrStruct json2IDCardV2OcrStruct(IDCardV2 idCard, JSONObject json) throws JSONException
    {
        IDCardV2.IDCardOcrStruct idCardOcrStruct = null;
        if (idCard != null && json != null)
        {
            idCardOcrStruct = idCard.new IDCardOcrStruct();
            idCardOcrStruct.setLogic(json.getInt(FaceIDConst.API_PARAM_LOGIC));
            idCardOcrStruct.setQuality((float) json.getDouble(FaceIDConst.API_PARAM_QUALITY));
            idCardOcrStruct.setResult(json.getString(FaceIDConst.API_PARAM_RESULT));
            idCardOcrStruct.setRect(json2IDCardV2OcrRect(idCard, json.getJSONObject(FaceIDConst.API_PARAM_RECT)));
        }
        return idCardOcrStruct;
    }

    private static IDCardV2.IDCardOcrRect json2IDCardV2OcrRect(IDCardV2 idCard, JSONObject json) throws JSONException
    {
        IDCardV2.IDCardOcrRect idCardOcrRect = null;
        if (idCard != null && json != null)
        {
            idCardOcrRect = idCard.new IDCardOcrRect();
            JSONObject jsonLB = new JSONObject(json.getString(FaceIDConst.API_PARAM_RECT_LB));
            idCardOcrRect.setLb(new Point(jsonLB.getInt(FaceIDConst.API_PARAM_RECT_X), jsonLB.getInt(FaceIDConst.API_PARAM_RECT_Y)));
            JSONObject jsonRB = new JSONObject(json.getString(FaceIDConst.API_PARAM_RECT_RB));
            idCardOcrRect.setRb(new Point(jsonRB.getInt(FaceIDConst.API_PARAM_RECT_X), jsonRB.getInt(FaceIDConst.API_PARAM_RECT_Y)));
            JSONObject jsonLT = new JSONObject(json.getString(FaceIDConst.API_PARAM_RECT_LT));
            idCardOcrRect.setLt(new Point(jsonLT.getInt(FaceIDConst.API_PARAM_RECT_X), jsonLT.getInt(FaceIDConst.API_PARAM_RECT_Y)));
            JSONObject jsonRT = new JSONObject(json.getString(FaceIDConst.API_PARAM_RECT_RT));
            idCardOcrRect.setRt(new Point(jsonRT.getInt(FaceIDConst.API_PARAM_RECT_X), jsonRT.getInt(FaceIDConst.API_PARAM_RECT_Y)));
        }
        return idCardOcrRect;
    }
    // endregion

    private static IDCardLegality json2IDCardLegality(JSONObject json, int version) throws JSONException
    {
        IDCardLegality legality = new IDCardLegality();
        legality.setEdited((float) json.getDouble(FaceIDConst.API_PARAM_LEGALITY_EDITED));
        legality.setPhotocopy((float) json.getDouble(FaceIDConst.API_PARAM_LEGALITY_PHOTOCOPY));
        legality.setScreen((float) json.getDouble(FaceIDConst.API_PARAM_LEGALITY_SCREEN));
        legality.setVersion(version);
        if (version == 1)
        {
            legality.setIdPhoto((float) json.getDouble(FaceIDConst.API_PARAM_LEGALITY_ID_PHOTO_V1));
            legality.setTemporaryIdPhoto((float) json.getDouble(FaceIDConst.API_PARAM_LEGALITY_TEMPORARY_ID_PHOTO_V1));
        }
        else if (version == 2)
        {
            legality.setIdPhoto((float) json.getDouble(FaceIDConst.API_PARAM_LEGALITY_ID_PHOTO_V2));
            legality.setTemporaryIdPhoto((float) json.getDouble(FaceIDConst.API_PARAM_LEGALITY_TEMPORARY_ID_PHOTO_V2));
            legality.setIdPhotoThreshold((float) json.getDouble(FaceIDConst.API_PARAM_LEGALITY_ID_PHOTO_THRESHOLD));
        }
        return legality;
    }

    //endregion

    // region API: BankCardOCR
    public static BankCard json2BankCard(@NonNull String body) throws NullPointerException
    {
        JSONObject json;
        BankCard bankCard = null;
        try
        {
            json = new JSONObject(body);
            bankCard = new BankCard();
            bankCard.setRequestId(json.getString(FaceIDConst.API_PARAM_REQUEST_ID));
            bankCard.setTimeUsed(json.getInt(FaceIDConst.API_PARAM_TIME_USED));
            bankCard.setResult(json.getInt(FaceIDConst.API_PARAM_RESULT));
            if (bankCard.getResult() == 1001)
            {
                bankCard.setNumber(json.optString(FaceIDConst.API_PARAM_NUMBER));
                bankCard.setBank(json.optString(FaceIDConst.API_PARAM_BANK));
                List<String> orgs = json2BankCardOrganization(json.optJSONArray(FaceIDConst.API_PARAM_ORGANIZATION));
                if (orgs == null)
                {
                    orgs = new ArrayList<>();
                    orgs.add(json.optString(FaceIDConst.API_PARAM_ORGANIZATION));
                }
                bankCard.setOrganization(orgs);
                bankCard.setValidDate(json.optString(FaceIDConst.API_PARAM_VALID_DATE));
                bankCard.setName(json.optString(FaceIDConst.API_PARAM_NAME));
                bankCard.setPosition(json2BankCardPosition(bankCard, json.optJSONObject(FaceIDConst.API_PARAM_POSITION)));
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return bankCard;
    }

    private static List<String> json2BankCardOrganization(JSONArray jsonOrg) throws JSONException
    {
        List<String> list = null;
        if (jsonOrg != null && jsonOrg.length() > 0)
        {
            list = new ArrayList<>();
            for (int i = 0; i < jsonOrg.length(); i++)
                list.add(jsonOrg.get(i).toString());
        }
        return list;
    }

    private static BankCard.Position json2BankCardPosition(BankCard bankCard, JSONObject jsonPosition) throws JSONException
    {
        BankCard.Position position = null;
        if (bankCard != null && jsonPosition != null)
        {
            position = bankCard.new Position();
            JSONObject jsonLB = jsonPosition.getJSONObject(FaceIDConst.API_PARAM_LEFT_BOTTOM);
            position.setLeftBottom(new Point(jsonLB.getInt(FaceIDConst.API_PARAM_RECT_X), jsonLB.getInt(FaceIDConst.API_PARAM_RECT_Y)));
            JSONObject jsonLT = jsonPosition.getJSONObject(FaceIDConst.API_PARAM_LEFT_TOP);
            position.setLeftTop(new Point(jsonLT.getInt(FaceIDConst.API_PARAM_RECT_X), jsonLT.getInt(FaceIDConst.API_PARAM_RECT_Y)));
            JSONObject jsonRB = jsonPosition.getJSONObject(FaceIDConst.API_PARAM_RIGHT_BOTTOM);
            position.setRightBottom(new Point(jsonRB.getInt(FaceIDConst.API_PARAM_RECT_X), jsonRB.getInt(FaceIDConst.API_PARAM_RECT_Y)));
            JSONObject jsonRT = jsonPosition.getJSONObject(FaceIDConst.API_PARAM_RIGHT_TOP);
            position.setRightTop(new Point(jsonRT.getInt(FaceIDConst.API_PARAM_RECT_X), jsonRT.getInt(FaceIDConst.API_PARAM_RECT_Y)));
        }
        return position;
    }
    // endregion

    // region API: Verify
    public static Verify json2Verify(@NonNull String body) throws NullPointerException
    {
        JSONObject json;
        Verify verify = null;
        try
        {
            json = new JSONObject(body);
            verify = new Verify();
            verify.setRequestId(json.getString(FaceIDConst.API_PARAM_REQUEST_ID));
            verify.setTimeUsed(json.getInt(FaceIDConst.API_PARAM_TIME_USED));
            verify.setResultFaceId(json2FaceResult(verify, json.optJSONObject(FaceIDConst.API_PARAM_RESULT_FACEID)));
            verify.setResultRef1(json2FaceResult(verify, json.optJSONObject(FaceIDConst.API_PARAM_RESULT_REF1)));
            verify.setResultRef2(json2FaceResult(verify, json.optJSONObject(FaceIDConst.API_PARAM_RESULT_REF2)));
            verify.setResultRef3(json2FaceResult(verify, json.optJSONObject(FaceIDConst.API_PARAM_RESULT_REF3)));
            verify.setIdExceptions(json2IDExceptions(verify, json.getJSONObject(FaceIDConst.API_PARAM_ID_EXCEPTIONS)));
            verify.setFaceGenuineness(json2FaceGenuineness(verify, json.optJSONObject(FaceIDConst.API_PARAM_FACE_GENUINENESS)));
            verify.setFaceInfos(json2FaceInfos(verify, json.optJSONArray(FaceIDConst.API_PARAM_FACES)));
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return verify;
    }

    private static Verify.FaceResult json2FaceResult(Verify verify, JSONObject jsonFaceResult) throws JSONException
    {
        Verify.FaceResult faceResult = null;
        if (verify != null && jsonFaceResult != null)
        {
            faceResult = verify.new FaceResult();
            faceResult.setConfidence((float) jsonFaceResult.getDouble(FaceIDConst.API_PARAM_CONFIDENCE));
            JSONObject jsonThresholds = jsonFaceResult.getJSONObject(FaceIDConst.API_PARAM_THRESHOLDS);
            faceResult.setThresholds_1e3((float) jsonThresholds.getDouble(FaceIDConst.API_PARAM_THRESHOLDS_1E_3));
            faceResult.setThresholds_1e4((float) jsonThresholds.getDouble(FaceIDConst.API_PARAM_THRESHOLDS_1E_4));
            faceResult.setThresholds_1e5((float) jsonThresholds.getDouble(FaceIDConst.API_PARAM_THRESHOLDS_1E_5));
            faceResult.setThresholds_1e6((float) jsonThresholds.getDouble(FaceIDConst.API_PARAM_THRESHOLDS_1E_6));
        }
        return faceResult;
    }

    private static Verify.IDExceptions json2IDExceptions(Verify verify, JSONObject jsonIDExceptions) throws JSONException
    {
        Verify.IDExceptions idExceptions = null;
        if (verify != null && jsonIDExceptions != null)
        {
            idExceptions = verify.new IDExceptions();
            idExceptions.setIdAttacked(jsonIDExceptions.getInt(FaceIDConst.API_PARAM_ID_ATTACKED));
            idExceptions.setIdPhotoMonochrome(jsonIDExceptions.getInt(FaceIDConst.API_PARAM_ID_PHOTO_MONOCHROME));
        }
        return idExceptions;
    }

    private static Verify.FaceGenuineness json2FaceGenuineness(Verify verify, JSONObject jsonFaceGenuineness) throws JSONException
    {
        Verify.FaceGenuineness faceGenuineness = null;
        if (verify != null && jsonFaceGenuineness != null)
        {
            faceGenuineness = verify.new FaceGenuineness();
            faceGenuineness.setFaceReplaced(jsonFaceGenuineness.getInt(FaceIDConst.API_PARAM_FACE_REPLACED));
            faceGenuineness.setMaskConfidence((float) jsonFaceGenuineness.getDouble(FaceIDConst.API_PARAM_MASK_CONFIDENCE));
            faceGenuineness.setMaskThreshold((float) jsonFaceGenuineness.getDouble(FaceIDConst.API_PARAM_MASK_THRESHOLD));
            faceGenuineness.setScreenReplayConfidence((float) jsonFaceGenuineness.getDouble(FaceIDConst.API_PARAM_SCREEN_REPLAY_CONFIDENCE));
            faceGenuineness.setScreenReplayThreshold((float) jsonFaceGenuineness.getDouble(FaceIDConst.API_PARAM_SCREEN_REPLAY_THRESHOLD));
            faceGenuineness.setSyntheticFaceConfidence((float) jsonFaceGenuineness.getDouble(FaceIDConst.API_PARAM_SYNTHETIC_FACE_CONFIDENCE));
            faceGenuineness.setSyntheticFaceThreshold((float) jsonFaceGenuineness.getDouble(FaceIDConst.API_PARAM_SYNTHETIC_FACE_THRESHOLD));
        }
        return faceGenuineness;
    }

    private static List<Verify.FaceInfo> json2FaceInfos(Verify verify, JSONArray jsonFaces) throws JSONException
    {
        List<Verify.FaceInfo> faceInfos = null;
        if (verify != null && jsonFaces != null)
        {
            faceInfos = new ArrayList<>();
            for (int i = 0; i < jsonFaces.length(); i++)
            {
                JSONObject jsonFace = jsonFaces.getJSONObject(i);
                Verify.FaceInfo faceInfo = verify.new FaceInfo();
                faceInfo.setOrientation(jsonFace.getInt(FaceIDConst.API_PARAM_ORIENTATION));
                faceInfo.setRect(json2RectF(jsonFace.getJSONObject(FaceIDConst.API_PARAM_RECT)));
                faceInfo.setQuality((float) jsonFace.getDouble(FaceIDConst.API_PARAM_QUALITY));
                faceInfo.setQualityThreshold((float) jsonFace.getDouble(FaceIDConst.API_PARAM_QUALITY_THRESHOLD));
                faceInfos.add(faceInfo);
            }
        }
        return faceInfos;
    }

    // endregion

    // region Common
    private static RectF json2RectF(JSONObject jsonRect) throws JSONException
    {
        RectF rect = null;
        if (jsonRect != null)
        {
            rect = new RectF();
            rect.left = (float) jsonRect.getDouble(FaceIDConst.API_PARAM_LEFT);
            rect.top = (float) jsonRect.getDouble(FaceIDConst.API_PARAM_TOP);
            rect.right = (float) (jsonRect.getDouble(FaceIDConst.API_PARAM_LEFT) + jsonRect.getDouble(FaceIDConst.API_PARAM_WIDTH));
            rect.bottom = (float) (jsonRect.getDouble(FaceIDConst.API_PARAM_TOP) + jsonRect.getDouble(FaceIDConst.API_PARAM_HEIGHT));
        }
        return rect;
    }
    // endregion
}
