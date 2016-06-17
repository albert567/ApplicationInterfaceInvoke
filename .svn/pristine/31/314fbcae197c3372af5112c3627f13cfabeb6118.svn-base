package com.gwamcc.aii.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.dao.DictDao;
import com.gwamcc.aii.dao.core.DefaultDao;
import com.gwamcc.aii.dao.core.SimpleDaoRowMapper;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.DictForm;
import com.gwamcc.aii.forms.MsgFrom;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.SimpleDictForm;
import com.gwamcc.aii.util.ExcelKit;
import com.gwamcc.aii.util.StrKit;
import com.gwamcc.aii.util.excel.RowValidatorList;
import com.gwamcc.aii.util.excel.formattor.ValueFormatter;
import com.gwamcc.aii.util.sql2.ConditionDefBuilder;
import com.gwamcc.aii.util.sql2.ParamMap;
import com.gwamcc.aii.util.template.anno.Template;

/**
 * 数据字典操作实现类
 *
 * @author 范大德
 *
 */
@Repository
@Template(key="Dict.xlsx",value="D100_数据字典模板.xlsx")
public class DictDaoImpl extends DefaultDao implements DictDao {
	@Override
	public List<DefaultForm> validList(String dType) throws Exception {
		return queryList(SimpleDictForm.class, new String[] { "T_Dict.sn" },
				new ConditionDefBuilder().param("T_Dict.Dtype=:dtype").param("T_Dict.IsValid=:valid").define(),
				new ParamMap().put("dtype", dType).put("valid", 1));
	}

	@Override
	public Object getList(DictForm form) throws Exception{
		ParamMap map=new ParamMap();

		ConditionDefBuilder cond=new ConditionDefBuilder();
		cond.add("T_Dict.isvalid=:isvalid or T_Dict.dtype=0");
		map.put("isvalid", form.getIsValid());
		if(!StrKit.isEmpty(form.getDtype())){
			cond.add("T_Dict.dkey=:dtype or T_Dict.dtype=:dtype");
			map.put("dtype", form.getDtype());
		}
		if(!StrKit.isEmpty(form.getDescription())){
			cond.add("(T_Dict.dvalue like :desc or T_Dict.description like :desc or T_Dict.dtype=0)");
			map.put("desc", "%"+form.getDescription()+"%");
		}
		List<DefaultForm> rows= queryList(DictForm.class, new String[]{"T_Dict.dtype","T_Dict.sn","T_Dict.dkey"}, cond.build(),map );
		return new PageDataForm(rows.size(), rows);
	}
	@Override
	public Object edit(DictForm form) throws Exception {
		if (saveUpdate(form)) {
			return MsgFrom.info("保存成功!");
		} else{
			return MsgFrom.err("保存失败!");
		}
	}
	@Override
	public Object append(DictForm form)throws Exception {
		if (save(form)) {
			return MsgFrom.info("保存成功!");
		} else{
			return MsgFrom.err("保存失败!");
		}
	}
	@Override
	public Object remove(DictForm form) throws Exception {
		if(form.getDtype().equals("00")){
			return MsgFrom.err("无法操作此数据!");
		}
		if(form.getIsValid()==0)
			form.setIsValid(1);
		else
			form.setIsValid(0);
		if (saveUpdate(form)) {
			return MsgFrom.info("删除成功!");
		} else {
			return MsgFrom.err("删除失败!");
		}
	}
	@Override
	public Object upload(MultipartFile file) throws Exception {
		return this.impExcel(ExcelKit.load(file, 0).title("DType","DValue","description","IsValid")
				.addFormatter(0, new ValueFormatter() {
					@Override
					public Object format(Object value) {
						return String.format("%2d", Integer.parseInt(value+"")).replaceAll(" ", "0");
					}
				})
				.addValidator(new RowValidatorList(0).in(queryOneFieldList("select DKey from T_Dict where DType=:dtype", new ParamMap().put("dtype", "00"))))
				.addValidator(new RowValidatorList(0,1).unique().required())
				.addValidator(new RowValidatorList(3).in(0,1))
				, new SimpleDaoRowMapper(this, DictForm.class));
	}
	@Override
	public Object download() throws Exception {
		return TemplateDaoImpl.download(this.getClass());
	}


}
