package com.gwamcc.aii.util.excel.mapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Row;

import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.MsgFrom;
import com.gwamcc.aii.util.excel.ExcelRowMapper;
import com.gwamcc.aii.util.excel.RowValidator;
import com.gwamcc.aii.util.excel.RowValidatorList;

/**
 * Excel行验证导航
 * @author 范大德
 *
 */
public class RowValidatorMapper extends ExcelRowMapper {
	/**
	* Logger for this class
	*/
	private static final Log logger = LogFactory.getLog(RowValidatorMapper.class);



	@Override
	public DefaultForm mapRow(Row row) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("mapRow(Row) - start"); //$NON-NLS-1$
		}
		if(getKit().getListener()!=null && row.getRowNum()>0){
			List<RowValidatorList>list=getKit().getValidatorList();
			for(RowValidatorList validatorList:list){
				int[]columns=validatorList.getColumns();
				Map<Integer, Object>validateDataMap=new HashMap<Integer,Object>();
				Map<Integer, Object>displayDataMap=new HashMap<Integer,Object>();
				for(int col:columns){
					validateDataMap.put(col, getKit().getCellValue(row.getCell(col)));
					displayDataMap.put(col, getKit().getCellValueWithOutFormat(row.getCell(col)));
				}
				for(RowValidator validator:validatorList.list()){
					if(!validator.validate(validateDataMap)){
						validator.setRow(row.getRowNum());
						validator.setValueMap(validateDataMap);
						validator.setDisplayMap(displayDataMap);
						DefaultForm returnDefaultForm = new MsgFrom(getKit().getListener().onError(validator), validator.errMsg());
						if (logger.isDebugEnabled()) {
							logger.debug("mapRow(Row) - end"); //$NON-NLS-1$
						}
						return  returnDefaultForm;
					}
				}
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("mapRow(Row) - end"); //$NON-NLS-1$
		}
		return null;
	}

}
