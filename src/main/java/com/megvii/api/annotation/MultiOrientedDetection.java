package com.megvii.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@StringDef({MultiOrientedDetection.VERIFY_MULTI_ORIENTED_DETECTION_NO, MultiOrientedDetection.VERIFY_MULTI_ORIENTED_DETECTION_YES})
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.PARAMETER)
public @interface MultiOrientedDetection
{
    String VERIFY_MULTI_ORIENTED_DETECTION_NO = "0";
    String VERIFY_MULTI_ORIENTED_DETECTION_YES = "1";
}
