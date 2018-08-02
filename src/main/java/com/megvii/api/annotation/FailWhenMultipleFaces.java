package com.megvii.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@StringDef({FailWhenMultipleFaces.VERIFY_FAIL_WHEN_MULTIPLE_FACES_NO, FailWhenMultipleFaces.VERIFY_FAIL_WHEN_MULTIPLE_FACES_YES})
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.PARAMETER)
public @interface FailWhenMultipleFaces
{
    String VERIFY_FAIL_WHEN_MULTIPLE_FACES_NO = "0";
    String VERIFY_FAIL_WHEN_MULTIPLE_FACES_YES = "1";
}
