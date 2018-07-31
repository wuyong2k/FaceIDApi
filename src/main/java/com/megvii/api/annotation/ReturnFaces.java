package com.megvii.api.annotation;

import android.support.annotation.StringDef;

import com.megvii.api.FaceIDApi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@StringDef({FaceIDApi.VERIFY_RETURN_FACES_NO, FaceIDApi.VERIFY_RETURN_FACES_YES})
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.PARAMETER)
public @interface ReturnFaces
{ }
