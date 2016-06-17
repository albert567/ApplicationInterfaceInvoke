package com.gwamcc.aii.service;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.forms.AppCodeForm;
import com.gwamcc.aii.forms.DefaultForm;

/**
 * 应用系统代码结构操作服务类
 * @author 张亚平
 *
 */
public interface AppCodeService {
	/**
	 * 查询 应用系统代码结构数据列表
	 * @param appID
	 * @return
	 */
	List<DefaultForm> getCode(int appID);
	/**
	 * 查询 应用系统代码结构根节点
	 * @param appID
	 * @return
	 */
	List<DefaultForm> getRoot(int appID);
	/**
	 * 查询 应用系统代码结构子节点
	 * @param codeID
	 * @return
	 */
	List<DefaultForm> getChild(int codeID);
	/**
	 * 查询 应用系统代码结构数据列表
	 * @param form
	 * @return
	 * @throws Exception
	 */
	Object getList(AppCodeForm form) throws Exception;

	/**
	 * 编辑 应用系统代码结构数据
	 * @param form
	 * @return
	 * @throws Exception
	 */
	Object edit(AppCodeForm form)throws Exception;

	/**
	 * 添加 应用系统代码结构数据
	 * @param form
	 * @return
	 * @throws Exception
	 */
	Object append(AppCodeForm form)throws Exception;

	/**
	 * 删除 应用系统代码结构数据
	 * @param form
	 * @return
	 * @throws Exception
	 */
	Object remove(AppCodeForm form)throws Exception;
	
	/**
	 * 导入数据
	 * @param file
	 * @return
	 * @throws Exception
	 */
	Object upload(MultipartFile file,int appID) throws Exception;

	/**
	 * 下载模板
	 * @return
	 * @throws Exception
	 */
	Object download()throws Exception;
}
