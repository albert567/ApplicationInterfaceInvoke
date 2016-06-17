package com.gwamcc.aii.util.sql2;

import java.util.ArrayList;
import java.util.List;

/**
 * Like参数实体
 * @author 范大德
 *
 */
public class LikeParam {


	private LikeType likeType=LikeType.AND;
	private LikeSpliceType spliceType=LikeSpliceType.NONE;

	private Object likeParam;


	@Override
	public String toString() {
		if(likeParam==null){
			return null;
		}
		String val=likeParam.toString();
		switch (spliceType) {
		case START:
			return "%"+val;
		case END:
			return val+"%";
		case START_END:
			return "%"+val+"%";
		case EACH_CHAR:
			String regex="(.{1})";
			return "%"+val.replaceAll(regex, "$1%");
		default:
			return likeParam.toString();
		}
	}

	/**
	 * 拼接对象为LikeParam列表
	 * 	array/List遍历添加,非LikeParam转换为likeParam
	 * @param objs
	 * @return
	 */
	public static LikeParam[] splice(Object...objs){
		if(objs==null){
			return null;
		}
		List<LikeParam>list=new ArrayList<LikeParam>();
		for(Object obj:objs){
			if (obj instanceof List<?>) {
				@SuppressWarnings("unchecked")
				List<Object> objList = (List<Object>) obj;
				list.addAll(array2List(splice(objList.toArray(new Object[0]))));
			}else if (obj instanceof LikeParam) {
				LikeParam like = (LikeParam) obj;
				list.add(like);
			}else if(obj.getClass().isArray()){
				list.addAll(array2List(splice(obj)));
			}else{
				list.add(new LikeParam(obj));
			}
		}
		return list.toArray(new LikeParam[0]);
	}

	private static List<LikeParam>array2List(LikeParam[]array){
		List<LikeParam>list=new ArrayList<LikeParam>();
		if(array==null){
			return list;
		}
		for(LikeParam param:array){
			list.add(param);
		}
		return list;
	}


	public static LikeParam[] toArray(Object[]array,LikeType likeType,LikeSpliceType spliceType){
		if(array==null){
			return null;
		}
		List<LikeParam>list=new ArrayList<LikeParam>();
		for(Object obj:array){
			list.add(new LikeParam(obj, likeType,spliceType));
		}
		return list.toArray(new LikeParam[0]);
	}

	public LikeParam(Object likeParam) {
		super();
		this.likeParam = likeParam;
	}


	public LikeParam(Object likeParam, LikeType likeType,LikeSpliceType spliceType) {
		super();
		this.likeParam = likeParam;
		this.likeType = likeType;
		this.spliceType=spliceType;
	}



	public LikeType getLikeType() {
		return likeType;
	}



	public LikeParam setLikeType(LikeType likeType) {
		this.likeType = likeType;
		return this;
	}



	public Object getLikeParam() {
		return likeParam;
	}



	public LikeParam setLikeParam(Object likeParam) {
		this.likeParam = likeParam;
		return this;
	}

	public LikeSpliceType getSpliceType() {
		return spliceType;
	}
	public LikeParam setSpliceType(LikeSpliceType spliceType) {
		this.spliceType = spliceType;
		return this;
	}



}
