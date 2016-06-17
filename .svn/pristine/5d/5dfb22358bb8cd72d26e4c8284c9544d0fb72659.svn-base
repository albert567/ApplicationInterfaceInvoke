package com.gwamcc.aii.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.MethodParamForm;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;

/**
 * 接口方法参数数据操作接口
 * @author 张亚平
 *
 */
public interface MethodParamDao {
	/**
	 * 获取指定方法的参数列表给下拉框
	 * @param methodID 方法id
	 * @return 查询结果
	 */
	List<DefaultForm>getParamName(int methodID);
	/**
	 * 获取指定方法的参数列表给对话框
	 * @param methodID 方法id
	 * @return 查询结果
	 */
	List<DefaultForm>getParamList(int methodID);
	/**
	 * 获取所有方法参数类型
	 * @param methodType 方法类型
	 * @return 查询结果
	 */
	List<DefaultForm>getParamType();
	/**
	 * 分页获取方法参数列表
	 * @param methodParam 查询条件
	 * @param page 分页信息
	 * @return 查询结果
	 */
	PageDataForm getParamList(DefaultForm methodParam,PageForm page);
	/**
	 * 变更方法参数
	 * @param methodParam 方法参数对象
	 * @return
	 */
	DefaultForm edit(MethodParamForm methodParam);
	/**
	 * 新增方法参数对象
	 * @param methodParam 方法参数对象
	 * @return
	 */
	DefaultForm append(MethodParamForm methodParam);
	/**
	 * 删除方法参数对象
	 * @param paramID
	 * @return
	 */
	DefaultForm remove(int paramID);
	/**
	 * 导入数据
	 * @param file
	 * @return
	 * @throws Exception
	 */
	Object upload(MultipartFile file) throws Exception;
	/**
	 * 下载模板
	 * @return
	 * @throws Exception
	 */
	Object download() throws Exception;
}
