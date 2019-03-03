package com.good.comm;

import java.util.HashMap;

import org.apache.commons.beanutils.PropertyUtils;

public class StringUtils {

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     *  分割字符串
     */
    public static String[] toArray(String str, char ch) {
        if (str == null)
            return null;
        if (str.indexOf(ch) == -1) {
            String[] r = {str};
            return r;
        }

        int num = 0;
        for (int k = 0; k < str.length(); k++) {
            if (str.charAt(k) == ch)
                num++;
        }
        String[] result = new String[num+1];
        str += ch;

        num = 0;
        int idx = -1;
        for (int i = 0; i < result.length; i++) {
            int end = str.indexOf(ch, idx + 1);
            result[num++] = str.substring(idx + 1, end);
            idx = end;
        }
        return result;
    }
    
    public static int parseInt(String val,int def) {
        int ret = 0;
        try{
            ret = Integer.parseInt(val.trim());
        }catch(Exception e){
            ret = def;
        }
        return ret;
    }
    
    public static StringBuilder linkString(Object ... val) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < val.length ; i++){
            sb.append(val[i]);
        }
        return sb;
    }
    public static String leftTrim(String s) {
        if (s == null){
            return s;
        }
        int i = 0 ; 
        for (; i < s.length() ; i ++) {
            char c = s.charAt(i);
            if (c == ' ' || c == '\t'|| c == '\r' || c == '\n') {
                continue;
            }
            else {
                break;
            }
        }
        return s.substring(i);
    }

    @SuppressWarnings("rawtypes")
	public static String replaceValue(String content,HashMap context) {
        int idx = 0 ;
        StringBuilder sb =new StringBuilder();
        do {
            int sidx = content.indexOf("${",idx);
            int eidx = content.indexOf("}",sidx+1);
            if (0 <= sidx && sidx < eidx) {
                sb.append(content.substring(idx, sidx));
                String fieldName = content.substring(sidx+2,eidx).trim();
                try {
                    Object val = PropertyUtils.getProperty(context, fieldName);
                    if (val !=null) {
                        sb.append(val);
                    }
                } catch (Exception e) {
                }
                idx = eidx +1;
            }
            else{
                sb.append(content.substring(idx));
                break;
            }
        } while (true);
        return sb.toString();
    }
}
