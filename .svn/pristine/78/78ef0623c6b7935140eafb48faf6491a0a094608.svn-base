package com.gwamcc.aii.dao.core;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.poi.ss.usermodel.Row;

import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.MsgFrom;
import com.gwamcc.aii.util.StrKit;
import com.gwamcc.aii.util.excel.DaoExcelRowMapper;
import com.gwamcc.aii.util.sql2.SQLUtils;

/**
 * 提供数据库操作支持的简单Excel行导航
 * @author 范大德
 *
 */
public class SimpleDaoRowMapper extends DaoExcelRowMapper{

	private String uuid;

	@SuppressWarnings("rawtypes")
	public SimpleDaoRowMapper(DefaultDao dao, Class cs) {
		super(dao, cs);
		setUuid(UUID.randomUUID().toString());
	}
	@Override
	public DefaultForm mapRow(Row row) throws Exception {
		if(row.getRowNum()>0){
			int line=row.getRowNum()+1;
			Map<String, Object>valueMap=new HashMap<String,Object>();
			for(int i=0;i<getFields().length;i++){
				String f=getFields()[i];
				Object value=getKit().getCellValue(row.getCell(i));
				valueMap.put(f.toUpperCase(), value);
			}
			String valueStr=StrKit.toString(valueMap);
			try {
				DefaultForm obj=(DefaultForm)SQLUtils.coverMapToBean(valueMap, getCs());
				obj.set_log(getUuid());
				if(!getDao().save(obj))
					return MsgFrom.err("行["+line+"]保存失败!"+valueStr);
				return MsgFrom.info("保存成功!"+valueStr);

			} catch (Exception e) {
				e.printStackTrace();
				return MsgFrom.err("行["+line+"]数据转换出错!"+valueStr);
			}
		}
		return null;

	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getUuid() {
		return uuid;
	}

}
