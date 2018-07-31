package com.megvii.api.entity;

import android.graphics.RectF;

import java.util.List;

public class Verify
{
    private int timeUsed;
    private String requestId;
    private FaceResult resultFaceId;
    private FaceResult resultRef1;
    private FaceResult resultRef2;
    private FaceResult resultRef3;
    private IDExceptions idExceptions;
    private List<FaceInfo> faceInfos;
    private FaceGenuineness faceGenuineness;

    public int getTimeUsed()
    {
        return timeUsed;
    }

    public void setTimeUsed(int timeUsed)
    {
        this.timeUsed = timeUsed;
    }

    public String getRequestId()
    {
        return requestId;
    }

    public void setRequestId(String requestId)
    {
        this.requestId = requestId;
    }

    public FaceResult getResultFaceId()
    {
        return resultFaceId;
    }

    public void setResultFaceId(FaceResult resultFaceId)
    {
        this.resultFaceId = resultFaceId;
    }

    public FaceResult getResultRef1()
    {
        return resultRef1;
    }

    public void setResultRef1(FaceResult resultRef1)
    {
        this.resultRef1 = resultRef1;
    }

    public FaceResult getResultRef2()
    {
        return resultRef2;
    }

    public void setResultRef2(FaceResult resultRef2)
    {
        this.resultRef2 = resultRef2;
    }

    public FaceResult getResultRef3()
    {
        return resultRef3;
    }

    public void setResultRef3(FaceResult resultRef3)
    {
        this.resultRef3 = resultRef3;
    }

    public IDExceptions getIdExceptions()
    {
        return idExceptions;
    }

    public void setIdExceptions(IDExceptions idExceptions)
    {
        this.idExceptions = idExceptions;
    }

    public List<FaceInfo> getFaceInfos()
    {
        return faceInfos;
    }

    public void setFaceInfos(List<FaceInfo> faceInfos)
    {
        this.faceInfos = faceInfos;
    }

    public FaceGenuineness getFaceGenuineness()
    {
        return faceGenuineness;
    }

    public void setFaceGenuineness(FaceGenuineness faceGenuineness)
    {
        this.faceGenuineness = faceGenuineness;
    }

    public class FaceResult
    {
        private float confidence;
        private float thresholds_1e3;
        private float thresholds_1e4;
        private float thresholds_1e5;
        private float thresholds_1e6;

        public float getConfidence()
        {
            return confidence;
        }

        public void setConfidence(float confidence)
        {
            this.confidence = confidence;
        }

        public float getThresholds_1e3()
        {
            return thresholds_1e3;
        }

        public void setThresholds_1e3(float thresholds_1e3)
        {
            this.thresholds_1e3 = thresholds_1e3;
        }

        public float getThresholds_1e4()
        {
            return thresholds_1e4;
        }

        public void setThresholds_1e4(float thresholds_1e4)
        {
            this.thresholds_1e4 = thresholds_1e4;
        }

        public float getThresholds_1e5()
        {
            return thresholds_1e5;
        }

        public void setThresholds_1e5(float thresholds_1e5)
        {
            this.thresholds_1e5 = thresholds_1e5;
        }

        public float getThresholds_1e6()
        {
            return thresholds_1e6;
        }

        public void setThresholds_1e6(float thresholds_1e6)
        {
            this.thresholds_1e6 = thresholds_1e6;
        }
    }

    public class FaceInfo
    {
        private float quality;
        private float qualityThreshold;
        private RectF rect;
        private int orientation;

        public float getQuality()
        {
            return quality;
        }

        public void setQuality(float quality)
        {
            this.quality = quality;
        }

        public float getQualityThreshold()
        {
            return qualityThreshold;
        }

        public void setQualityThreshold(float qualityThreshold)
        {
            this.qualityThreshold = qualityThreshold;
        }

        public RectF getRect()
        {
            return rect;
        }

        public void setRect(RectF rect)
        {
            this.rect = rect;
        }

        public int getOrientation()
        {
            return orientation;
        }

        public void setOrientation(int orientation)
        {
            this.orientation = orientation;
        }
    }

    public class FaceGenuineness
    {
        private float syntheticFaceConfidence;
        private float syntheticFaceThreshold;
        private float maskConfidence;
        private float maskThreshold;
        private float screenReplayConfidence;
        private float screenReplayThreshold;
        private int faceReplaced;

        public float getSyntheticFaceConfidence()
        {
            return syntheticFaceConfidence;
        }

        public void setSyntheticFaceConfidence(float syntheticFaceConfidence)
        {
            this.syntheticFaceConfidence = syntheticFaceConfidence;
        }

        public float getSyntheticFaceThreshold()
        {
            return syntheticFaceThreshold;
        }

        public void setSyntheticFaceThreshold(float syntheticFaceThreshold)
        {
            this.syntheticFaceThreshold = syntheticFaceThreshold;
        }

        public float getMaskConfidence()
        {
            return maskConfidence;
        }

        public void setMaskConfidence(float maskConfidence)
        {
            this.maskConfidence = maskConfidence;
        }

        public float getMaskThreshold()
        {
            return maskThreshold;
        }

        public void setMaskThreshold(float maskThreshold)
        {
            this.maskThreshold = maskThreshold;
        }

        public float getScreenReplayConfidence()
        {
            return screenReplayConfidence;
        }

        public void setScreenReplayConfidence(float screenReplayConfidence)
        {
            this.screenReplayConfidence = screenReplayConfidence;
        }

        public float getScreenReplayThreshold()
        {
            return screenReplayThreshold;
        }

        public void setScreenReplayThreshold(float screenReplayThreshold)
        {
            this.screenReplayThreshold = screenReplayThreshold;
        }

        public int getFaceReplaced()
        {
            return faceReplaced;
        }

        public void setFaceReplaced(int faceReplaced)
        {
            this.faceReplaced = faceReplaced;
        }
    }

    public class IDExceptions
    {
        private int idAttacked;
        private int idPhotoMonochrome;

        public int getIdAttacked()
        {
            return idAttacked;
        }

        public void setIdAttacked(int idAttacked)
        {
            this.idAttacked = idAttacked;
        }

        public int getIdPhotoMonochrome()
        {
            return idPhotoMonochrome;
        }

        public void setIdPhotoMonochrome(int idPhotoMonochrome)
        {
            this.idPhotoMonochrome = idPhotoMonochrome;
        }
    }
}
