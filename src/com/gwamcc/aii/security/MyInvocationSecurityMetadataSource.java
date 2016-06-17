package com.gwamcc.aii.security;
import java.util.Collection;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
/**
*
* 此类在初始化时，应该取到所有资源及其对应角色的定义
*
* @author 范大德
*
*/
public class MyInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
/*    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    public MyInvocationSecurityMetadataSource() {
         loadResourceDefine();
     }
    private void loadResourceDefine() {//定义资源
    	 resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
         Collection<ConfigAttribute> admin = new ArrayList<ConfigAttribute>();
         Collection<ConfigAttribute> user = new ArrayList<ConfigAttribute>();
         ConfigAttribute adminConfig = new SecurityConfig("ROLE_ADMIN");
         admin.add(adminConfig);
         ConfigAttribute useConfig = new SecurityConfig("ROLE_USER");
         admin.add(useConfig);
         user.add(useConfig);



     }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object)throws IllegalArgumentException {//根据地址匹配权限
        // guess object is a URL.
        System.out.println(object);
    	String url = ((FilterInvocation)object).getRequestUrl();
         Iterator<String> ite = resourceMap.keySet().iterator();
        while (ite.hasNext()) {
             String resURL = ite.next();
        }
        return null;
     }
*/
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
     }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
     }

	@Override
	public Collection<ConfigAttribute> getAttributes(Object obj) throws IllegalArgumentException {
		return null;
	}

}