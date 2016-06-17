package com.gwamcc.aii.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.forms.DataBaseForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.PageForm;

/**
 * 数据库数据操作接口
 * @author 张亚平
 *
 */
public interface DataBaseDao {
	/**
	 * 获取数据库名称列表
	 * @return
	 */
	List<DefaultForm>getDb();
	/**
	 * 分页获取数据库信息列表
	 * @param db	查询条件
	 * @param page	分页信息
	 * @return
	 */
	Object getDbList(DataBaseForm db,PageForm page);

	/**
	 * 新增一条数据库信息
	 * @param db
	 * @return
	 */
	DefaultForm append(DataBaseForm db);

	/**
	 * 修改一条数据库信息
	 * @param db
	 * @return
	 */
	DefaultForm edit(DataBaseForm db);

	/**
	 * 删除指定的数据库信息
	 * @param dbID	待删除的数据库
	 * @return
	 */
	DefaultForm remove(int dbID);
	
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
	
	/**
	 * 数据展示
	 * @param dbID
	 * @return
	 */
	public List<DefaultForm> getForceData(int dbID);
}
