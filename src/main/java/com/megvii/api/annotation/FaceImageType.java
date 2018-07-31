package com.megvii.api.annotation;

import android.support.annotation.StringDef;

import com.megvii.api.FaceIDApi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@StringDef({
        FaceIDApi.VERIFY_FACE_IMAGE_TYPE_MEGLIVE,
        FaceIDApi.VERIFY_FACE_IMAGE_TYPE_FACETOKEN,
        FaceIDApi.VERIFY_FACE_IMAGE_TYPE_RAW_IMAGE,
        FaceIDApi.VERIFY_FACE_IMAGE_TYPE_MEGLIVE_FLASH
})
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.PARAMETER)
public @interface FaceImageType
{ }





