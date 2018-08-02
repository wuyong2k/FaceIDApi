package com.megvii.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@StringDef({ComparisonType.VERIFY_COMPARISON_TYPE_SOURCE_NO, ComparisonType.VERIFY_COMPARISON_TYPE_SOURCE_YES})
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.PARAMETER)
public @interface ComparisonType
{
    String VERIFY_COMPARISON_TYPE_SOURCE_NO = "0";
    String VERIFY_COMPARISON_TYPE_SOURCE_YES = "1";
}

