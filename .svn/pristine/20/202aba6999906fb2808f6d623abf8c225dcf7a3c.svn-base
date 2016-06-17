package com.gwamcc.aii.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.forms.DataBaseObjForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.PageForm;

/**
 * 数据库对象操作服务类
 * @author 张亚平
 *
 */
public interface DataBaseObjService {
	/**
	 * 获取存储过程列表
	 * @param dbID 数据库ID
	 * @return
	 */
	List<DefaultForm>getProcedure(int dbID);
	/**
	 * 获取数据库对象名称列表
	 * @return
	 */
	List<DefaultForm>getDbObj(int dbID);
	/**
	 * 获取数据库对象列表
	 * @return
	 */
	List<DefaultForm>getDbObjList(int dbID);
	/**
	 * 分页获取数据库对象信息列表
	 * @param dbObj	查询条件
	 * @param page	分页信息
	 * @return
	 */
	Object getDbObjList(DataBaseObjForm dbObj,PageForm page);

	/**
	 * 新增一条数据库对象信息
	 * @param dbObj
	 * @return
	 */
	DefaultForm append(DataBaseObjForm dbObj);

	/**
	 * 修改一条数据库对象信息
	 * @param dbObj
	 * @return
	 */
	DefaultForm edit(DataBaseObjForm dbObj);

	/**
	 * 删除指定的数据库对象信息
	 * @param dbObjID	待删除的数据库对象
	 * @return
	 */
	DefaultForm remove(int dbObjID);
	/**
	 * 获取数据库对象类型
	 * @return
	 */
    Object objType();

    Object upload(MultipartFile file)throws Exception;

	Object download()throws Exception;
	Object uploadPdm(int db,MultipartFile file) throws Exception;

}
