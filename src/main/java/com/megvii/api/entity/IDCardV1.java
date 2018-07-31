package com.megvii.api.entity;

import android.graphics.PointF;

public class IDCardV1
{
    private int timeUsed;
    private String requestId;
    private IDCardLegality legality;
    private String side;
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

    public String getSide()
    {
        return side;
    }

    public void setSide(String side)
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

    public class FrontSide
    {
        private String race;
        private String name;
        private String gender;
        private String idCardNumber;
        private String address;
        private Birthday birthday;
        private HeadRect headRect;

        public String getRace()
        {
            return race;
        }

        public void setRace(String race)
        {
            this.race = race;
        }

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public String getGender()
        {
            return gender;
        }

        public void setGender(String gender)
        {
            this.gender = gender;
        }

        public String getIdCardNumber()
        {
            return idCardNumber;
        }

        public void setIdCardNumber(String idCardNumber)
        {
            this.idCardNumber = idCardNumber;
        }

        public String getAddress()
        {
            return address;
        }

        public void setAddress(String address)
        {
            this.address = address;
        }

        public Birthday getBirthday()
        {
            return birthday;
        }

        public void setBirthday(Birthday birthday)
        {
            this.birthday = birthday;
        }

        public HeadRect getHeadRect()
        {
            return headRect;
        }

        public void setHeadRect(HeadRect headRect)
        {
            this.headRect = headRect;
        }

        public class Birthday
        {
            private String year;
            private String month;
            private String day;

            public String getYear()
            {
                return year;
            }

            public void setYear(String year)
            {
                this.year = year;
            }

            public String getMonth()
            {
                return month;
            }

            public void setMonth(String month)
            {
                this.month = month;
            }

            public String getDay()
            {
                return day;
            }

            public void setDay(String day)
            {
                this.day = day;
            }

            @Override
            public String toString()
            {
                return String.format("%s-%s-%s", this.year, this.month.length() > 1 ? this.month : "0" + this.month, this.day);
            }
        }

        public class HeadRect
        {
            private PointF rt;
            private PointF lt;
            private PointF rb;
            private PointF lb;

            public PointF getRt()
            {
                return rt;
            }

            public void setRt(PointF rt)
            {
                this.rt = rt;
            }

            public PointF getLt()
            {
                return lt;
            }

            public void setLt(PointF lt)
            {
                this.lt = lt;
            }

            public PointF getRb()
            {
                return rb;
            }

            public void setRb(PointF rb)
            {
                this.rb = rb;
            }

            public PointF getLb()
            {
                return lb;
            }

            public void setLb(PointF lb)
            {
                this.lb = lb;
            }
        }
    }

    public class BackSide
    {
        private String validDate;
        private String issuedBy;

        public String getValidDate()
        {
            return validDate;
        }

        public void setValidDate(String validDate)
        {
            this.validDate = validDate;
        }

        public String getIssuedBy()
        {
            return issuedBy;
        }

        public void setIssuedBy(String issuedBy)
        {
            this.issuedBy = issuedBy;
        }
    }
}
