package com.gwamcc.aii.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gwamcc.aii.util.array.ArrayOperation;

public class ArrayKit<E> {
	private List<E>list;

	private ArrayKit(){};
	public E[]array(E[]e){
		return list==null?null:list.toArray(e);
	}
	public ArrayKit<E> operation(ArrayOperation<E> operation){
		list=operation.operation(list);
		return this;
	}
	@SuppressWarnings("unchecked")
	public  ArrayKit<E> remove(E...obj){
		if(list!=null)
			list.removeAll(asList(obj));
		return this;
	}
	public  ArrayKit<E> removeArray(E[]obj){
		if(list!=null){
			list.removeAll(asList(obj));
		}
		return this;
	}
	public  ArrayKit<E> pustArray(E[]obj){
		if(list==null){
			list=new ArrayList<E>();
		}
		list.addAll(asList(obj));
		return this;
	}
	@SuppressWarnings("unchecked")
	public  ArrayKit<E> pust(E...obj){
		if(list==null){
			list=new ArrayList<E>();
		}
		list.addAll(asList(obj));
		return this;
	}
	@SuppressWarnings("unchecked")
	public  ArrayKit<E> add(int index,E...obj){
		if(list==null){
			list=new ArrayList<E>();
		}
		list.addAll(index,asList(obj));
		return this;
	}
	public static <T>ArrayKit<T> kit(Class<T>c){
		ArrayKit<T> kit=new ArrayKit<T>();
		return kit;
	}
	public static <T>ArrayKit<T> kit(T[]array){
		ArrayKit<T> kit=new ArrayKit<T>();
		kit.list=asList(array);
		return kit;
	}

	public static <T>List<T> asList(T[]array){
		if(array==null){
			return null;
		}
		List<T>list=new ArrayList<T>();
		list.addAll(Arrays.asList(array));
		return list;
	}

	public static List<Map<Object, Object>> toKeyValueList(Object[]values,boolean useIndexKey){
		if(values==null){
			return null;
		}
		List<Map<Object, Object>>list=new ArrayList<Map<Object,Object>>();
		for(int i=0;i<values.length;i++){
			Map<Object, Object>map=new HashMap<Object,Object>();
			map.put("key", useIndexKey?i:values[i]);
			map.put("value", values[i]);
			list.add(map);
		}
		return list;
	}

	private static boolean inArray(Object find,Object[]findBy){
		if(find==null||findBy==null){
			return false;
		}
		for(Object obj:findBy){
			if(find.equals(obj)){
				return true;
			}
		}
		return false;
}

	public static boolean in(Object find,Object...findBy){
		if(findBy!=null&&findBy.length==1&&findBy[0]!=null
				&&findBy[0].getClass().isArray()&&find.getClass().equals(((Object[])findBy[0])[0].getClass())){
			findBy= (Object[])findBy[0];
		}
			return inArray(find, findBy);
	}

	public static boolean isEmpty(Object[]array){
		return array==null||array.length<=0;
	}

	public static boolean isEmpty(Iterable<Object>array){
		return array==null||array.iterator().hasNext();
	}
}
