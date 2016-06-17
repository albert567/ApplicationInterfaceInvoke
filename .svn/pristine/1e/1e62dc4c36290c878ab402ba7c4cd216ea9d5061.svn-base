package com.gwamcc.aii.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.gwamcc.aii.exception.DefaultException;

/**
 * 统一异常处理类
 * @author 范大德
 *
 */
public class MainExceptionHandler implements HandlerExceptionResolver {
	/**
	* Logger for this class
	*/
	private static final Log logger = LogFactory.getLog(MainExceptionHandler.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest req, HttpServletResponse resp, Object arg2,
			Exception e) {
		if (logger.isInfoEnabled()) {
			logger.info("Catch an exception - URL="+req.getRequestURI()+" - Exception="+e.getClass().getName()+":" + e.getMessage()); //$NON-NLS-1$
			e.printStackTrace();
		}
		Map<String, Object> model = new HashMap<String,Object>();
        model.put("exception", e);
        if(e instanceof DefaultException){
        	DefaultException de=(DefaultException) e;
        	return new ModelAndView("forward:"+de.getView(),model);
        }else if(e instanceof DuplicateKeyException){
            return new ModelAndView("forward:/exception/DuplicateKeyException", model);
        }else{
            return new ModelAndView("forward:"+DefaultException.DEFAULT_VIEW, model);
        }
	}
}
