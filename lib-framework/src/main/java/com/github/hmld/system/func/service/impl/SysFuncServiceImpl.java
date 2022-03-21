package com.github.hmld.system.func.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.hmld.common.constant.Constants;
import com.github.hmld.common.utils.StringUtils;
import com.github.hmld.system.func.domain.SysFunc;
import com.github.hmld.system.func.domain.SysFuncTreeView;
import com.github.hmld.system.func.domain.constants.FuncConstants;
import com.github.hmld.system.func.domain.vo.MetaVo;
import com.github.hmld.system.func.domain.vo.RouterVo;
import com.github.hmld.system.func.mapper.SysFuncMapper;
import com.github.hmld.system.func.service.ISysFuncService;
/**
 * 系统功能
 * @author hmld
 *
 */
@Service
public class SysFuncServiceImpl implements ISysFuncService {
	@Autowired
	private SysFuncMapper sysFuncMapper;
	
	/**
	 * 查询 系统功能
	 * @param sysFunc
	 * @return
	 */
	public List<SysFunc> querySysFuncList(SysFunc sysFunc){
		return sysFuncMapper.querySysFuncList(sysFunc);
	}
	
	/**
	 * 查询 系统功能
	 * @param sysFunc
	 * @return
	 */
	public SysFunc querySysFuncByPK(String funcPk){
		return sysFuncMapper.querySysFuncByPK(funcPk);
	}
	/**
	 * 添加 系统功能
	 * @param sysFunc
	 * @return
	 */
	public int insertSysFunc(SysFunc sysFunc){
		sysFunc.setFuncPk(UUID.randomUUID().toString());
		return sysFuncMapper.insertSysFunc(sysFunc);
	}
	/**
	 * 修改 系统功能
	 * @param sysFunc
	 * @return
	 */
	public int updateSysFunc(SysFunc sysFunc){
		return sysFuncMapper.updateSysFunc(sysFunc);
	}
	/**
	 * 删除 系统功能
	 * @param sysFunc
	 * @return
	 */
	public int deleteSysFuncByPk(String delFlag, Timestamp updateTime, String updateBy, String funcPk) {
		return sysFuncMapper.deleteSysFuncByPk(delFlag, updateTime, updateBy, funcPk);
	}
	/**
	 * 删除 系统功能
	 * @param sysFunc
	 * @return
	 */
	public int deleteSysFuncByPks(String delFlag, Timestamp updateTime, String updateBy, String[] funcPks) {
		return sysFuncMapper.deleteSysFuncByPks(delFlag, updateTime, updateBy, funcPks);
	}
	
	/**
	 * 获取前端 Router
	 * @param userPk
	 * @return
	 */
	public List<RouterVo> selectRoutersByUserPK(String userPk) {
		List<SysFunc> funcs = sysFuncMapper.querySysFuncByUserPk(userPk);
		return this.buildRouters(funcs);
	}
	
	/**
	 * 构建功能链接为前端的 Router 数据
	 * @param funcs
	 * @return
	 */ 
	private List<RouterVo> buildRouters(List<SysFunc> funcs) {
		List<RouterVo> routers = new LinkedList<RouterVo>();
		for (SysFunc func : funcs) {
			RouterVo router = new RouterVo();
			router.setHidden(FuncConstants.NO_VISIBLE.equals(func.getVisible()));
      router.setName(getRouteName(func));
      router.setPath(getRouterPath(func));
      router.setComponent(getComponent(func));
      router.setQuery(func.getQuery());
      router.setMeta(new MetaVo(func.getFuncName(), func.getIcon(), true, func.getFuncUrl()));
      List<SysFuncTreeView> cFuncs = func.getChildren();
      List<SysFunc> childFuncs = getFuncS(cFuncs);
      if (!childFuncs.isEmpty() && childFuncs.size() > 0 && FuncConstants.FUNC_TYPE_M.equals(func.getFuncType()))
      {
          router.setAlwaysShow(true);
          router.setRedirect("noRedirect");
          router.setChildren(buildRouters(childFuncs));
      }
      else if (isFuncFrame(func))
      {
          router.setMeta(null);
          List<RouterVo> childrenList = new ArrayList<RouterVo>();
          RouterVo children = new RouterVo();
          children.setPath(func.getFuncUrl());
          children.setComponent(func.getComponent());
          children.setName(StringUtils.capitalize(func.getFuncUrl()));
          children.setMeta(new MetaVo(func.getFuncName(), func.getIcon(), StringUtils.equals("1", func.getIsCache()), func.getFuncUrl()));
          children.setQuery(func.getQuery());
          childrenList.add(children);
          router.setChildren(childrenList);
      }
      else if (StringUtils.isNotEmpty(func.getParentId()) && func.getParentId().equals(FuncConstants.FUNC_DEFAULT_PARENTID) && isInnerLink(func))
      {
          router.setMeta(new MetaVo(func.getFuncName(), func.getIcon()));
          router.setPath("/inner");
          List<RouterVo> childrenList = new ArrayList<RouterVo>();
          RouterVo children = new RouterVo();
          String routerPath = StringUtils.replaceEach(func.getFuncUrl(), new String[] { Constants.HTTP, Constants.HTTPS }, new String[] { "", "" });
          children.setPath(routerPath);
          children.setComponent(FuncConstants.INNER_LINK);
          children.setName(StringUtils.capitalize(routerPath));
          children.setMeta(new MetaVo(func.getFuncName(), func.getIcon(), func.getFuncUrl()));
          childrenList.add(children);
          router.setChildren(childrenList);
      }
      routers.add(router);
		}
		return routers;
	}
	private List<SysFunc> getFuncS(List<SysFuncTreeView> cFuncs) {
		List<SysFunc> funcList = new ArrayList<SysFunc>();
		for (SysFuncTreeView cFunc : cFuncs) {
			SysFunc func = new SysFunc();
			func.setFuncPk(cFunc.getFuncPk());
			func.setParentId(cFunc.getParentId());
			func.setFuncName(cFunc.getFuncName());
			func.setFuncUrl(cFunc.getFuncUrl());
			func.setFunPerms(cFunc.getFunPerms());
			func.setVisible(cFunc.getVisible());
			func.setOrderNum(cFunc.getOrderNum());
			func.setFuncExplanation(cFunc.getFuncExplanation());
			func.setComponent(cFunc.getComponent());
			func.setFuncType(cFunc.getFuncType());
			func.setIcon(cFunc.getIcon());
			func.setIsFrame(cFunc.getIsFrame());
			func.setStatus(cFunc.getStatus());
			func.setDelFlag(cFunc.getDelFlag());
			func.setUpdateTime(cFunc.getUpdateTime());
			func.setUpdateBy(cFunc.getUpdateBy());
			func.setCreateTime(cFunc.getCreateTime());
			func.setCreateBy(cFunc.getCreateBy());
			func.setIsCache(cFunc.getIsCache());
			func.setQuery(cFunc.getQuery());
			func.setChildren(cFunc.getChildren());
			funcList.add(func);
		}
		return funcList;
	}

	/**
	 * 获取组件信息
	 * @param func
	 * @return
	 */
	private String getComponent(SysFunc func) {
		String component = FuncConstants.LAYOUT;
		if (StringUtils.isNotEmpty(func.getComponent()) && !isFuncFrame(func)) {
			component = func.getComponent();
		}
		else if (StringUtils.isEmpty(func.getComponent()) && func.getParentId().equals(FuncConstants.NO_FRAME) && isInnerLink(func)) {
			component = FuncConstants.INNER_LINK;
		}
		else if (StringUtils.isEmpty(func.getComponent()) && isParentView(func)){
      component = FuncConstants.PARENT_VIEW;
	  }
	  return component;
	}
	/**
	 * 是否为parent_view组件
	 * @param func 功能信息
	 * @return 结果
	 */
	private boolean isParentView(SysFunc func) {
		return StringUtils.isNotEmpty(func.getParentId()) 
				&& !func.getParentId().equals(FuncConstants.FUNC_DEFAULT_PARENTID)
				&& FuncConstants.FUNC_TYPE_M.equals(func.getFuncType());
	}

	/**
	 * 是否为菜单内部跳转
	 * @param func 功能信息
	 * @return 结果
	 */
	private boolean isFuncFrame(SysFunc func) {
		return StringUtils.isNotEmpty(func.getParentId()) 
				&& func.getParentId().equals(FuncConstants.FUNC_DEFAULT_PARENTID)
				&& func.getFuncType().equals(FuncConstants.FUNC_TYPE_F)
				&& func.getIsFrame().equals(FuncConstants.NO_FRAME);
	}
  /**
   * 是否为内链组件
   * 
   * @param menu 菜单信息
   * @return 结果
   */
  public boolean isInnerLink(SysFunc func)
  {
      return func.getIsFrame().equals(FuncConstants.NO_FRAME) && StringUtils.ishttp(func.getFuncUrl());
  }
	/**
	 * 获取 路由地址
	 * @param func
	 * @return
	 */
	private String getRouterPath(SysFunc func) {
		 String routerPath = func.getFuncUrl();
     // 内链打开外网方式
     if (StringUtils.isNotEmpty(func.getParentId()) && !func.getParentId().equals(FuncConstants.FUNC_DEFAULT_PARENTID) && isInnerLink(func))
     {
         routerPath = StringUtils.replaceEach(routerPath, new String[] { Constants.HTTP, Constants.HTTPS }, new String[] { "", "" });
     }
     // 非外链并且是一级目录（类型为目录）
     if (StringUtils.isNotEmpty(func.getParentId()) 
    		 && func.getParentId().equals(FuncConstants.FUNC_DEFAULT_PARENTID)  
    		 && FuncConstants.FUNC_TYPE_M.equals(func.getFuncType())
         && FuncConstants.NO_FRAME.equals(func.getIsFrame()))
     {
         routerPath = "/" + func.getFuncUrl();
     }
     // 非外链并且是一级目录（类型为菜单）
     else if (isFuncFrame(func))
     {
         routerPath = "/";
     }
     return routerPath;
	}
	/**
	 * 获取 路由名称
	 * @param func
	 * @return
	 */
	private String getRouteName(SysFunc func) {
		String routerName = StringUtils.capitalize(func.getFuncUrl());
    // 非外链并且是一级目录（类型为目录）
    if (isFuncFrame(func))
    {
        routerName = StringUtils.EMPTY;
    }
    return routerName;
	}
	
	
}
