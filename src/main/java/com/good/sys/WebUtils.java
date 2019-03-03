package com.good.sys;

import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.good.comm.web.WebRequest;
import com.good.comm.web.WebRequest.Order;
import com.good.db.IPage;
import com.good.db.impl.PageImpl;
import com.good.sys.bean.LogonInfo;


/**
 * 公共方法
 * 
 * @author wuwei
 *
 */
public class WebUtils {
    
    public static final String ORDER_COL = "order_column";
    public static final String ORDER_DIR = "order_dir";

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static HashMap fillOrderParam(WebRequest wr,HashMap condition) {
        HashMap ret = condition;
        if (ret == null) {
            ret = new HashMap();
        }
        if (wr.getNorder() != null && wr.getNorder().length >0 ) {
            HashMap<Order, String>[] orders = wr.getNorder();
            StringBuilder sb =new StringBuilder();
            for (HashMap<Order, String> order : orders) {
                sb.append(convertCamel(order.get(Order.column)));
                sb.append(" ").append(order.get(Order.dir));
                sb.append(",");
            }
            if (orders.length > 0 ) {
                sb.setLength(sb.length()-1);
                ret.put("orders", sb.toString());
            }
        }
        return ret;
    }
    
    /**
     * 驼峰字段转换
     * roleId -->  role_id
     * @return
     */
    private static String convertCamel(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i =0 ;i < str.length(); i++) {
            char ch = str.charAt(i);
            if ( ch >= 'A' && ch <= 'Z') {
                sb.append('_').append(Character.toLowerCase(ch));
            }
            else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public static IPage getPageParam(WebRequest wr) {
        PageImpl page = new PageImpl();
        page.setOffset(wr.getStart());
        page.setPageSize(wr.getLength() ==0 ?15:wr.getLength());
        return page;
    }
    
    public static LogonInfo getLogInfo(HttpServletRequest request) {
    	if (request == null) {
    		throw new RuntimeException(MsgConstants.E0001);
    	}
    	LogonInfo ret = (LogonInfo) request.getSession().getAttribute(Constants.SESSION_USER_KEY);
    	if (ret == null) {
    		throw new RuntimeException(MsgConstants.E0005);
    	}
    	return ret;
    }

    public static boolean isSuccess(String retcode) {
        if (retcode == null) {
            return false;
        }
        return retcode.startsWith("I");
    }

    public static void convertParam(HashMap<String, Object> target, HttpServletRequest request) {
        if (target == null || request == null) {
            return;
        }
        Enumeration<String> pnames = request.getParameterNames();
        while (pnames.hasMoreElements()) {
            String name = pnames.nextElement();
            if (name.startsWith("s_")) {
                target.put(name.substring(2),request.getParameter(name));
            }
        }
    }
}
