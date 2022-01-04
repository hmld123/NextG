package com.github.hmld.common.core.domain;

import java.util.List;

import org.slf4j.Logger;

import com.github.hmld.common.constant.HttpStatus;
import com.github.hmld.common.core.page.TableDataInfo;
import com.github.hmld.common.utils.LoggerUtil;
import com.github.hmld.common.utils.PageUtils;
import com.github.pagehelper.PageInfo;
/**
 * 默认 Controller
 * @author hmld
 *
 */
public class DefaultController {
	public static Logger LOGGER = LoggerUtil.initLogger(DefaultController.class);

	/**
   * 设置请求分页数据
   */
  protected void startPage()
  {
      PageUtils.startPage();
  }

  /**
   * 响应请求分页数据
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  protected TableDataInfo getDataTable(List<?> list)
  {
      TableDataInfo rspData = new TableDataInfo();
      rspData.setCode(HttpStatus.SUCCESS);
      rspData.setMsg("查询成功");
      rspData.setRows(list);
      rspData.setTotal(new PageInfo(list).getTotal());
      return rspData;
  }
}
