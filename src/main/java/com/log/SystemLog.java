/**
 * SystemLog.java
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-11-08 9:42
 */
package com.log;

import java.lang.annotation.*;

/**
 * 文件的说明:自定义注解
 *
 * @author 杨凯
 * @version 1.0.0, 2018-11-08 9:42
 **/
@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {
    String module()  default "";
    String methods()  default "";
}
