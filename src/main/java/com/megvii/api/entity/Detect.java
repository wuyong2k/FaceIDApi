package com.megvii.api.entity;

import android.graphics.RectF;

import java.util.List;

public class Detect
{
    private int timeUsed;
    private String requestId;
    private List<DetectFace> faces;

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

    public List<DetectFace> getFaces()
    {
        return faces;
    }

    public void setFaces(List<DetectFace> faces)
    {
        this.faces = faces;
    }

    public static class DetectFace
    {
        private float quality;
        private float qualityThreshold;
        private RectF rect;
        private String token;
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

        public String getToken()
        {
            return token;
        }

        public void setToken(String token)
        {
            this.token = token;
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
}
