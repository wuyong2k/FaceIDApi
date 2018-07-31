package com.megvii.api.entity;

import android.graphics.Point;

import java.util.List;

public class BankCard
{
    private int timeUsed;
    private String requestId;
    private int result;
    private String number;
    private String bank;
    private List<String> organization;
    private String validDate;
    private String name;
    private Position position;

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

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public String getBank()
    {
        return bank;
    }

    public void setBank(String bank)
    {
        this.bank = bank;
    }

    public List<String> getOrganization()
    {
        return organization;
    }

    public void setOrganization(List<String> organization)
    {
        this.organization = organization;
    }

    public String getValidDate()
    {
        return validDate;
    }

    public void setValidDate(String validDate)
    {
        this.validDate = validDate;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Position getPosition()
    {
        return position;
    }

    public void setPosition(Position position)
    {
        this.position = position;
    }

    public class Position
    {
        private Point leftBottom;
        private Point leftTop;
        private Point rightBottom;
        private Point rightTop;

        public Point getLeftBottom()
        {
            return leftBottom;
        }

        public void setLeftBottom(Point leftBottom)
        {
            this.leftBottom = leftBottom;
        }

        public Point getLeftTop()
        {
            return leftTop;
        }

        public void setLeftTop(Point leftTop)
        {
            this.leftTop = leftTop;
        }

        public Point getRightBottom()
        {
            return rightBottom;
        }

        public void setRightBottom(Point rightBottom)
        {
            this.rightBottom = rightBottom;
        }

        public Point getRightTop()
        {
            return rightTop;
        }

        public void setRightTop(Point rightTop)
        {
            this.rightTop = rightTop;
        }
    }
}
