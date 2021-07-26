package com.github.hmld.common.constant;

public class BaseGenConstants {
  /** 页面不需要编辑字段 */
  public static final String[] COLUMNNAME_NOT_EDIT = { "id", "create_by", "create_time", "del_flag" ,"update_time","update_by"};
  /** 页面不需要查询字段 */
  public static final String[] COLUMNNAME_NOT_QUERY = { "id", "create_by", "create_time", "del_flag" ,"update_time","update_by","remark"};
  /** 页面不需要显示的列表字段 */
  public static final String[] COLUMNNAME_NOT_LIST = { "id", "create_by", "create_time", "del_flag" ,"update_time","update_by"};
  /** 文本框*/
  public static final String VIEW_INPUT = "input";
  /** 文本域*/
  public static final String VIEW_TEXTAREA = "textarea";
  /** 下拉框*/
  public static final String VIEW_SELECT = "select";
  /** 单选框*/
  public static final String VIEW_RADIO = "radio";
  /** 复选框*/
  public static final String VIEW_CHECKBOX = "checkbox";
  /** 日期控件*/
  public static final String VIEW_DATE = "date";
  /** 时间控件*/
  public static final String VIEW_TIME = "time";
  /** 日期时间控件*/
  public static final String VIEW_DATE_TIME = "date_time";
  /** 文件上传控件*/
  public static final String VIEW_UPLOAD = "upload";
  /** 富文本控件*/
  public static final String VIEW_EDITOR = "editor";
  /** 模糊查询*/
  public static final String QUERY_LIKE = "LIKE";
  /** 启用*/
  public static final String ENABLE = "1";
  /** 禁用*/
  public static final String DISABLE = "0";

}
