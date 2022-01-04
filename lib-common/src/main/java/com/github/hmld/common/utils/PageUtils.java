package com.github.hmld.common.utils;

import com.github.hmld.common.core.page.PageDomain;
import com.github.hmld.common.core.page.TableSupport;
import com.github.hmld.common.utils.sql.SqlUtil;
import com.github.pagehelper.PageHelper;

public class PageUtils extends PageHelper{

		public static void startPage() {
			 PageDomain pageDomain = TableSupport.buildPageRequest();
       Integer pageNum = pageDomain.getPageNum();
       Integer pageSize = pageDomain.getPageSize();
       if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize))
       {
           String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
           PageHelper.startPage(pageNum, pageSize, orderBy);
       }
		}
	
}
