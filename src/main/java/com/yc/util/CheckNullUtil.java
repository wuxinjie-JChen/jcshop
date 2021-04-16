package com.yc.util;

import com.yc.service.ServiceException;

public class CheckNullUtil {

	public static void checkNull(Object value,String msg) throws ServiceException{
		if(value==null){
			throw new ServiceException(msg);
		}
		if(value instanceof String){
			String s=(String)value;
			if(s.trim().isEmpty()){
				throw new ServiceException(msg);
			}
		}
	}
}
