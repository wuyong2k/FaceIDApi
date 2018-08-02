package com.megvii.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@StringDef({ReturnFaces.VERIFY_RETURN_FACES_NO, ReturnFaces.VERIFY_RETURN_FACES_YES})
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.PARAMETER)
public @interface ReturnFaces
{
    String VERIFY_RETURN_FACES_NO = "0";
    String VERIFY_RETURN_FACES_YES = "1";
}
