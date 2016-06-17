package com.gwamcc.aii.util.http;

import java.util.ArrayList;
import java.util.List;

public class ZipFileList extends ArrayList<ZipFileItem>{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String fileName;



	public ZipFileList(String fileName) {
		super();
		this.fileName = fileName;
	}


	public <T>ZipFileList addList(List<T>list,OnListItemAdd<T> on){
		if(list==null){
			return this;
		}
		for(int i=0;i<list.size();i++){
			ZipFileItem item=on.convert(list, i, list.get(i));
			if(item!=null){
				add(item);
			}
		}
		return this;
	}
	public <T>ZipFileList addArray(T[]array,OnArrayItemAdd<T> on){
		if(array==null){
			return this;
		}
		for(int i=0;i<array.length;i++){
			ZipFileItem item=on.convert(array, i, array[i]);
			if(item!=null){
				add(item);
			}
		}
		return this;
	}

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public interface OnArrayItemAdd<T>{
		public ZipFileItem convert(T[]array,int index,T item);
	}
	public interface OnListItemAdd<T>{
		public ZipFileItem convert(List<T>list,int index,T item);
	}

}
