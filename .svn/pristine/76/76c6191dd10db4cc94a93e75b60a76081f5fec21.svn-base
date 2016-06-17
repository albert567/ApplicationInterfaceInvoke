package com.gwamcc.aii.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.forms.ProcedureParamForm;

/**
 * 存储过程参数操作服务类
 * @author 张亚平
 *
 */
public interface ProcedureParamService {
	/**
	 * 获取指定存储过程的参数列表给下拉框
	 * @param methodID 方法id
	 * @return 查询结果
	 */
	List<DefaultForm>getParamName(int objID);
	/**
	 * 获取指定存储过程的参数列表给对话框
	 * @param methodID 方法id
	 * @return 查询结果
	 */
	List<DefaultForm>getParamList(int objID);
	/**
	 * 获取所有存储过程参数类型
	 * @return 查询结果
	 */
	List<DefaultForm>getParamType();
	/**
	 * 分页获取存储过程参数列表
	 * @param proParam 查询条件
	 * @param page 分页信息
	 * @return 查询结果
	 */
	PageDataForm getParamList(ProcedureParamForm proParam,PageForm page);
	/**
	 * 变更存储过程参数
	 * @param proParam 存储过程参数对象
	 * @return
	 */
	DefaultForm edit(ProcedureParamForm proParam);
	/**
	 * 新增存储过程参数对象
	 * @param proParam 存储过程参数对象
	 * @return
	 */
	DefaultForm append(ProcedureParamForm proParam);
	/**
	 * 删除存储过程参数对象
	 * @param paramID
	 * @return
	 */
	DefaultForm remove(int paramID);

	Object upload(MultipartFile file) throws Exception;

	Object download() throws Exception;

}
