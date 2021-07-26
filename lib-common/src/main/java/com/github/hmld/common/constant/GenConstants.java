package com.github.hmld.common.constant;

public class GenConstants extends BaseGenConstants{
  /** 字符串类型*/
  public static final String[] COLUMNTYPE_STR = {
      "text","varchar","bpchar"
  };
  /** 布尔类型*/
  public static final String[] COLUMNTYPE_BOOLEAN = {
      "bit","bool"
  };
  /** 单精度浮点类型*/
  public static final String[] COLUMNTYPE_FLOAT = {
      "float4"
  };
  /** 双精度浮点类型*/
  public static final String[] COLUMNTYPE_DOUBLE = {
      "float8","money"
  };
  /** INTEGER类型*/
  public static final String[] COLUMNTYPE_INTEGER = {
      "int2","int4"
  };
  /** Long类型*/
  public static final String[] COLUMNTYPE_LONG = {
      "int8"
  };
  /** BigDecimal类型*/
  public static final String[] COLUMNTYPE_BIGDECIMAL = {
      "numeric"
  };
  /** Date类型*/
  public static final String[] COLUMNTYPE_DATE = {
      "date"
  };
  /** Time类型*/
  public static final String[] COLUMNTYPE_TIME = {
      "time"
  };
  /** Timestamp类型*/
  public static final String[] COLUMNTYPE_TIMESTAMP = {
      "timestamp"
  };
  
  /** 字符串类型 */
  public static final String TYPE_STRING = "String";
  /** 布尔类型*/
  public static final String TYPE_BOOLEAN = "Boolean";
  /** 整型 */
  public static final String TYPE_INTEGER = "Integer";
  /** 长整型 */
  public static final String TYPE_LONG = "Long";
  /** 单精度浮点型 */
  public static final String TYPE_FLOAT = "Float";
  /** 双精度浮点型 */
  public static final String TYPE_DOUBLE = "Double";
  /** 高精度计算类型 */
  public static final String[] TYPE_BIGDECIMAL = {"BigDecimal","java.math.BigDecimal"};
  /** Date类型 */
  public static final String[] TYPE_DATE = {"Date","java.sql.Date"};
  /** Time类型 */
  public static final String[] TYPE_TIME = {"Time","java.sql.Time"};
  /** Timestamp类型 */
  public static final String[] TYPE_TIMESTAMP = {"Timestamp","java.sql.Timestamp"};
  
}
