package com.utils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Map;


/**
 * 
 * @author Administrator
 *
 */
public class CommonUtils {

    private static Logger logger = LoggerFactory.getLogger(CommonUtils.class);

    public static<R> R  selectDataValue(Map<String, Object> map, String key , final Class<R> clazz) throws Exception {
        Map dataMap = (Map)map.get("data");
        if(dataMap == null) {
            return null;
        }

        return (R) selectValue(dataMap, key, clazz);
    }

    public  static<R> R  selectValue(Map<String, Object> map, String key , final Class<R> clazz) throws Exception {
        if (map == null) {
            return null;
        }

        Object value = map.get(key);

        if (value == null || StringUtils.isBlank(value.toString())) {
            return null;
        }
        try {
            if (clazz == String.class && value.getClass() != String.class) {
                return (R) value.toString();
            }

            if (clazz == BigDecimal.class && value.getClass() != BigDecimal.class) {
                return (R) new BigDecimal(value.toString());
            }

            if (clazz == Integer.class && value.getClass() != Integer.class) {
                return (R) new Integer(value.toString());
            }

            if (clazz == Long.class && value.getClass() != Long.class) {
                return (R) new Long(value.toString());
            }

            if (clazz == Short.class && value.getClass() != Short.class) {
                return (R) new Short(value.toString());
            }

            if (clazz == Byte.class && value.getClass() != Byte.class) {
                return (R) new Byte(value.toString());
            }

            if (clazz == Float.class && value.getClass() != Float.class) {
                return (R) new Float(value.toString());
            }

            if (clazz == Double.class && value.getClass() != Double.class) {
                return (R) new Double(value.toString());
            }

            if (clazz == Boolean.class && value.getClass() != Boolean.class) {
                return (R) new Boolean(value.toString());
            }

            if (clazz == Character.class && value.getClass() != Character.class) {
                return (R) new Character(value.toString().charAt(0));
            }



        } catch (Exception e) {
            logger.error("处理异常", e);
            throw e;
        }

        return (R)value;
    }

    public static boolean parametersIncludeNull(Object... objs) throws Exception {
        boolean isNull = false;

        try {
            for(Object obj : objs) {
                if(obj == null) {
                    isNull = true;
                    break;
                }
                if(obj.getClass() == String.class) {
                    if(((String)obj).isEmpty()) {
                        isNull = true;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            logger.error("处理异常", e);
            throw e;
        }

        return isNull;
    }

}
