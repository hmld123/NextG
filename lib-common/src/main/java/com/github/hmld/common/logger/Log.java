package com.github.hmld.common.logger;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Log注解 使用方法：
 * 
 * <pre>
 * @OperationLogDetail(detail = "通过手机号[{{tel}}]获取用户名",level = 3,operationUnit = OperationUnit.USER,operationType = OperationType.SELECT)
 * </pre>
 * 
 * @author hmldt
 *
 */
@Documented
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
  /**
   * 方法描述,可使用占位符获取参数:{{tel}}
   * 
   * @return
   */
  String detail() default "";

  /**
   * 日志等级：可以自己设定
   * 
   * @return
   */
  int level() default 0;

  /**
   * 操作类型(enum):主要是select,insert,update,delete
   * 
   * @return
   */
  OperationType operationType() default OperationType.UNKNOWN;

  /**
   * 被操作对象(enum)：可以是任何对象，如表名(user)，或者是工具(redis)
   * 
   * @return
   */
  OperationUnit operationUnit() default OperationUnit.UNKNOWN;

}
