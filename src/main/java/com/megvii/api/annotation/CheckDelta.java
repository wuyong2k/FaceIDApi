package com.megvii.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@StringDef({CheckDelta.VERIFY_CHECK_DELTA_NO, CheckDelta.VERIFY_CHECK_DELTA_YES})
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.PARAMETER)
public @interface CheckDelta
{
    String VERIFY_CHECK_DELTA_NO = "0";
    String VERIFY_CHECK_DELTA_YES = "1";
}
