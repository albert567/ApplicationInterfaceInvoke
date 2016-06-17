package com.gwamcc.aii.util;

import java.io.File;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class ClassKit {
	/**
	 * 扫描给定的基包下的所有的类(包括子包)。
	 * @param basePackageName 基包的名称
	 * @return 所有在基包下的类的全名
	 */
	public static Set<String> scanBasePackage(String basePackageName){
		String packageDirName = basePackageName.replace(".", "/");
		URL url  = Thread.currentThread().getContextClassLoader().getResource(packageDirName);
		File targetFile = new File(url.getFile());
		if(!targetFile.exists() || targetFile.isFile()){
			throw new RuntimeException(basePackageName + "不是一个包名或者该包名不存在");
		}
		Set<String> classNames = new HashSet<String>();
		getAllClass(targetFile, basePackageName, classNames);
		return classNames;

	}
	/**
	 * 得到所有在parentFile目录下的class文件名称
	 * @param parentFile
	 * @param classNames
	 * @param basePackageName
	 */
	private static void getAllClass(File parentFile, String basePackageName, Set<String> classNames){

		File[] files = parentFile.listFiles();
		for(File file : files){
			String path = file.getPath();
			if(file.isFile()){

				if(path.endsWith(".class")){
					classNames.add(
							basePackageName + "." +
							path.substring(
									path.lastIndexOf('\\') + 1, path.lastIndexOf('.')
									)
						);
				}
			}else{
				basePackageName = basePackageName + path.substring(path.lastIndexOf('\\') + 1);
				getAllClass(file, basePackageName, classNames);
			}
		}
	}
}
