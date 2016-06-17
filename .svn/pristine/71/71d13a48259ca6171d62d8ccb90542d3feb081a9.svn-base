package com.gwamcc.aii.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.MsgFrom;
import com.gwamcc.aii.util.excel.ExcelRowMapper;
import com.gwamcc.aii.util.excel.RowValidatorList;
import com.gwamcc.aii.util.excel.formattor.ValueFormatter;
import com.gwamcc.aii.util.excel.mapper.RowValidatorMapper;
import com.gwamcc.aii.util.excel.validator.RowValidateErrorListener;

/**
 * Excel操作类
 * @author 范大德
 *
 */
public class ExcelKit {


	public Object getCellValueWithOutFormat(Cell cell){
		Object value=null;
		if(cell!=null){
			int type=cell.getCellType();
			if(type==Cell.CELL_TYPE_FORMULA){
				type=cell.getCachedFormulaResultType();
			}
			switch (type) {
			case Cell.CELL_TYPE_BLANK:
				value= null;
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				value= cell.getBooleanCellValue();
				break;
			case Cell.CELL_TYPE_ERROR:
				value= cell.getErrorCellValue();
				break;
			case Cell.CELL_TYPE_NUMERIC:
				if(DateUtil.isCellDateFormatted(cell)){
					value= cell.getDateCellValue();
				}else if(String.valueOf(cell.getNumericCellValue()).endsWith(".0")){
					value= new Double(cell.getNumericCellValue()).intValue();
				}else{
					value= cell.getNumericCellValue();
				}
				break;
			default:
				value= cell.getStringCellValue();
				break;
			}

		}
		return value;
	}

	/**
	 * 获取单元格值
	 * 		公式单元格返回结果值
	 * @param cell	单元格
	 * @return
	 */
	public Object getCellValue(Cell cell){
		Object value=getCellValueWithOutFormat(cell);
		if(cell!=null&&formatterMap.get(cell.getColumnIndex())!=null){
			value=formatterMap.get(cell.getColumnIndex()).setRow(cell.getRow()).format(value);
		}
		return value;
	}
	private Sheet sheet;
	private ExcelKit(Sheet sheet){
		this.sheet=sheet;
	}

	/**
	 * 加载Excel文件流
	 * @param in	excel文件流
	 * @param fileName	excel文件名
	 * @param sheetId	工作表id
	 * @return
	 * @throws Exception
	 */
	public static ExcelKit load(InputStream in,String fileName,int sheetId)throws Exception{
		 Workbook wb = null;
	        if (fileName.toLowerCase().endsWith(".xls")) {
	            wb = new HSSFWorkbook(in);
	        }
	        else if (fileName.toLowerCase().endsWith(".xlsx")||fileName.toLowerCase().endsWith(".xlsm")) {
	            wb = new XSSFWorkbook(in);
	        }
	        else {
	            System.out.println("您输入的excel格式不正确");
	        }
	        in.close();
	        Sheet sheet = wb.getSheetAt(sheetId);
		return new ExcelKit(sheet);
	}

	private RowValidateErrorListener listener;//单元格验证错误监听器
	private List<RowValidatorList>validatorList=new ArrayList<RowValidatorList>();//单元格验证器表
	private Map<Integer, ValueFormatter>formatterMap=new HashMap<Integer,ValueFormatter>();


	private String[]titles;//工作表表头

	/**
	 * 设置工作表表头
	 * @param titles	工作表表头数组
	 * @return
	 */
	public ExcelKit title(String...titles){
		this.titles=titles;
		return this;
	}

	/**
	 * 添加一个验证器列表
	 * @param list	验证器列表
	 * @return
	 */
	public ExcelKit addValidator(RowValidatorList list){
		validatorList.add(list);
		return this;
	}
	/**
	 * 给数据列添加数据格式化器
	 * @param column
	 * @param formatters
	 * @return
	 */
	public ExcelKit addFormatter(int column,ValueFormatter formatter){
		formatter.setExcelKit(this);
		formatterMap.put(column, formatter);
		return this;
	}

	/**
	 * 验证当前工作表
	 * @return
	 * @throws Exception
	 */
	public ExcelKit validator() throws Exception{
		ExcelRowMapper mapper=new RowValidatorMapper().setKit(this);
		for (Row row : sheet) {
        	DefaultForm res= mapper.mapRow(row);
             if(res!=null){
            	 if (res instanceof MsgFrom) {
					MsgFrom msg = (MsgFrom) res;
					if(!msg.isSuccess()){
						return this;
					}
				}
             }
        }
		return this;
	}

	/**
	 * 添加单元格验证错误监听
	 * @param listener
	 * @return
	 */
	public ExcelKit onValidateError(RowValidateErrorListener listener){
		this.listener=listener;
		return this;
	}


	/**
	 * 遍历工作表
	 * @param mapper	工作表行处理器
	 * @return
	 * @throws Exception
	 */
	public List<DefaultForm> mapper(ExcelRowMapper mapper)throws Exception{
        List<DefaultForm>list=new ArrayList<DefaultForm>();
        mapper.setKit(this);
        for (Row row : sheet) {
        	DefaultForm res= mapper.mapRow(row);
             if(res!=null){
            	 list.add(res);
             }
        }
		return list;
	}
	/**
	 * 加载Excel文件
	 * @param file	Excel文件
	 * @param sheet	工作表id
	 * @return
	 * @throws Exception
	 */
	public static ExcelKit load(MultipartFile file,int sheet) throws Exception{
		return load(file.getInputStream(), file.getOriginalFilename(),sheet);
	}

	public Sheet getSheet() {
		return sheet;
	}

	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}

	public RowValidateErrorListener getListener() {
		return listener;
	}

	public void setListener(RowValidateErrorListener listener) {
		this.listener = listener;
	}

	public List<RowValidatorList> getValidatorList() {
		return validatorList;
	}

	public void setValidatorList(List<RowValidatorList> validatorList) {
		this.validatorList = validatorList;
	}

	public String[] getTitles() {
		return titles;
	}

	public void setTitles(String[] titles) {
		this.titles = titles;
	}


}
