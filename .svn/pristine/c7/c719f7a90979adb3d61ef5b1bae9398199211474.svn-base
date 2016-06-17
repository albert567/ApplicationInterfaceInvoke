package com.gwamcc.aii.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.gwamcc.aii.util.http.ZipFileItem;
import com.gwamcc.aii.util.http.ZipFileList;

public class HttpKit {

	/**
     * 下载文件
     * @param filePath	待下载文件路径
     * @param downloadName	下载文件名
     * @return
     * @throws Exception
     */
    public static Object download(String filePath,String downloadName) throws Exception{
    	String path=System.getProperty("webapp.root")+"\\"+filePath;
        File file=new File(path);
        HttpHeaders headers = new HttpHeaders();
        String fileName=processFileName(downloadName);//为了解决中文名称乱码问题
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.OK);
    }

    public static Object zip(ZipFileList list) throws Exception{
    	ByteArrayOutputStream byteArray=new ByteArrayOutputStream();
    	ZipOutputStream zout=new ZipOutputStream(byteArray);
		String path=System.getProperty("webapp.root");
		File tempFile=null;
		FileInputStream fin=null;
		int i=0;
		byte[]bs=new byte[4096];
		for(ZipFileItem item:list){
			tempFile=new File(path+"/"+item.getPath()+"/"+item.getName());
			try {
				fin=new FileInputStream(tempFile);
				zout.putNextEntry(new ZipEntry(item.getDisplay()));
				while((i=fin.read(bs))!=-1){
					zout.write(bs, 0, i);
				}
				zout.closeEntry();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				fin.close();
			}
		}
		zout.finish();
		byteArray.flush();
		HttpHeaders headers = new HttpHeaders();
        String fileName=HttpKit.processFileName(list.getFileName());//为了解决中文名称乱码问题
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(byteArray.toByteArray(),headers, HttpStatus.OK);
    }

	/**
	 *
	 * @Title: processFileName
	 * @Description: ie,chrom,firfox下处理文件名显示乱码
	 **/
    public static String processFileName(String fileNames) {
    	String codedfilename = null;
    	HttpServletRequest request=request();
    	try {
    		String agent = request.getHeader("USER-AGENT");
	    	if (null != agent && -1 != agent.indexOf("MSIE") || null != agent && -1 != agent.indexOf("Trident")) {// ie
	    		String name = java.net.URLEncoder.encode(fileNames, "UTF8");
	    		codedfilename = name;
	    	}else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等
	    		codedfilename = new String(fileNames.getBytes("UTF-8"), "iso-8859-1");
	    	}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return codedfilename;
    }

    public static HttpServletRequest request(){
    	return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    }
}
