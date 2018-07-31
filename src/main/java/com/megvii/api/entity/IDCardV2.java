package com.megvii.api.entity;

import android.graphics.Point;

public class IDCardV2
{
    private int timeUsed;
    private String requestId;
    private int result;
    private int side;
    private int completeness;
    private IDCardOcrRect cardRect;
    private IDCardLegality legality;
    private FrontSide frontSide;
    private BackSide backSide;

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

    public int getResult()
    {
        return result;
    }

    public void setResult(int result)
    {
        this.result = result;
    }

    public int getSide()
    {
        return side;
    }

    public void setSide(int side)
    {
        this.side = side;
    }

    public FrontSide getFrontSide()
    {
        return frontSide;
    }

    public void setFrontSide(FrontSide frontSide)
    {
        this.frontSide = frontSide;
    }

    public BackSide getBackSide()
    {
        return backSide;
    }

    public void setBackSide(BackSide backSide)
    {
        this.backSide = backSide;
    }

    public IDCardLegality getLegality()
    {
        return legality;
    }

    public void setLegality(IDCardLegality legality)
    {
        this.legality = legality;
    }

    public int getCompleteness()
    {
        return completeness;
    }

    public void setCompleteness(int completeness)
    {
        this.completeness = completeness;
    }

    public IDCardOcrRect getCardRect()
    {
        return cardRect;
    }

    public void setCardRect(IDCardOcrRect cardRect)
    {
        this.cardRect = cardRect;
    }

    public class FrontSide
    {
        private IDCardOcrStruct name;
        private IDCardOcrStruct gender;
        private IDCardOcrStruct address;
        private IDCardOcrStruct idcardNumber;
        private IDCardOcrStruct birthYear;
        private IDCardOcrStruct birthMonth;
        private IDCardOcrStruct birthDay;
        private IDCardOcrStruct nationality;
        private IDCardOcrStruct portrait;

        public IDCardOcrStruct getName()
        {
            return name;
        }

        public void setName(IDCardOcrStruct name)
        {
            this.name = name;
        }

        public IDCardOcrStruct getGender()
        {
            return gender;
        }

        public void setGender(IDCardOcrStruct gender)
        {
            this.gender = gender;
        }

        public IDCardOcrStruct getAddress()
        {
            return address;
        }

        public void setAddress(IDCardOcrStruct address)
        {
            this.address = address;
        }

        public IDCardOcrStruct getIdcardNumber()
        {
            return idcardNumber;
        }

        public void setIdcardNumber(IDCardOcrStruct idcardNumber)
        {
            this.idcardNumber = idcardNumber;
        }

        public IDCardOcrStruct getBirthYear()
        {
            return birthYear;
        }

        public void setBirthYear(IDCardOcrStruct birthYear)
        {
            this.birthYear = birthYear;
        }

        public IDCardOcrStruct getBirthMonth()
        {
            return birthMonth;
        }

        public void setBirthMonth(IDCardOcrStruct birthMonth)
        {
            this.birthMonth = birthMonth;
        }

        public IDCardOcrStruct getBirthDay()
        {
            return birthDay;
        }

        public void setBirthDay(IDCardOcrStruct birthDay)
        {
            this.birthDay = birthDay;
        }

        public IDCardOcrStruct getNationality()
        {
            return nationality;
        }

        public void setNationality(IDCardOcrStruct nationality)
        {
            this.nationality = nationality;
        }

        public IDCardOcrStruct getPortrait()
        {
            return portrait;
        }

        public void setPortrait(IDCardOcrStruct portrait)
        {
            this.portrait = portrait;
        }
    }

    public class BackSide
    {
        private IDCardOcrStruct issuedBy;
        private IDCardOcrStruct validDateStart;
        private IDCardOcrStruct validDateEnd;

        public IDCardOcrStruct getIssuedBy()
        {
            return issuedBy;
        }

        public void setIssuedBy(IDCardOcrStruct issuedBy)
        {
            this.issuedBy = issuedBy;
        }

        public IDCardOcrStruct getValidDateStart()
        {
            return validDateStart;
        }

        public void setValidDateStart(IDCardOcrStruct validDateStart)
        {
            this.validDateStart = validDateStart;
        }

        public IDCardOcrStruct getValidDateEnd()
        {
            return validDateEnd;
        }

        public void setValidDateEnd(IDCardOcrStruct validDateEnd)
        {
            this.validDateEnd = validDateEnd;
        }
    }

    public class IDCardOcrStruct
    {
        private String result;
        private float quality;
        private int logic;
        private IDCardOcrRect rect;

        public String getResult()
        {
            return result;
        }

        public void setResult(String result)
        {
            this.result = result;
        }

        public float getQuality()
        {
            return quality;
        }

        public void setQuality(float quality)
        {
            this.quality = quality;
        }

        public int getLogic()
        {
            return logic;
        }

        public void setLogic(int logic)
        {
            this.logic = logic;
        }

        public IDCardOcrRect getRect()
        {
            return rect;
        }

        public void setRect(IDCardOcrRect rect)
        {
            this.rect = rect;
        }
    }

    public class IDCardOcrRect
    {
        private Point rt;
        private Point lt;
        private Point rb;
        private Point lb;

        public Point getRt()
        {
            return rt;
        }

        public void setRt(Point rt)
        {
            this.rt = rt;
        }

        public Point getLt()
        {
            return lt;
        }

        public void setLt(Point lt)
        {
            this.lt = lt;
        }

        public Point getRb()
        {
            return rb;
        }

        public void setRb(Point rb)
        {
            this.rb = rb;
        }

        public Point getLb()
        {
            return lb;
        }

        public void setLb(Point lb)
        {
            this.lb = lb;
        }
    }
}
