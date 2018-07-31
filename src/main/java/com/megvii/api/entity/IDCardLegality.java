package com.megvii.api.entity;

public class IDCardLegality
{
    private float edited;
    private float photocopy;
    private float idPhoto;
    private float screen;
    private float temporaryIdPhoto;
    private float idPhotoThreshold;
    private int version;

    public float getEdited()
    {
        return edited;
    }

    public void setEdited(float edited)
    {
        this.edited = edited;
    }

    public float getPhotocopy()
    {
        return photocopy;
    }

    public void setPhotocopy(float photocopy)
    {
        this.photocopy = photocopy;
    }

    public float getIdPhoto()
    {
        return idPhoto;
    }

    public void setIdPhoto(float idPhoto)
    {
        this.idPhoto = idPhoto;
    }

    public float getScreen()
    {
        return screen;
    }

    public void setScreen(float screen)
    {
        this.screen = screen;
    }

    public float getTemporaryIdPhoto()
    {
        return temporaryIdPhoto;
    }

    public void setTemporaryIdPhoto(float temporaryIdPhoto)
    {
        this.temporaryIdPhoto = temporaryIdPhoto;
    }

    public float getIdPhotoThreshold()
    {
        return idPhotoThreshold;
    }

    public void setIdPhotoThreshold(float idPhotoThreshold)
    {
        this.idPhotoThreshold = idPhotoThreshold;
    }

    public void setVersion(int version)
    {
        this.version = version;
    }

    public boolean isIdPhoto()
    {
        return version == 2 ? this.idPhoto > this.idPhotoThreshold : this.idPhoto > 0.8f;
    }

}
