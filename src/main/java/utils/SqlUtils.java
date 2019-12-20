//package utils;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.ibatis.mapping.BoundSql;
//import org.apache.ibatis.mapping.MappedStatement;
//import org.apache.ibatis.mapping.ParameterMapping;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//
//import java.io.InputStream;
//import java.io.Serializable;
//import java.text.DateFormat;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.*;
//import java.util.regex.Matcher;
//
///**
// * Created by IntelliJ IDEA.<br/>
// * User: Eugene_Wang<br/>
// * Date: 12/18/2019<br/>
// * Time: 5:19 PM<br/>
// * To change this template use File | Settings | File Templates.
// */
//public class SqlUtils {
//
//    private static SqlSessionFactory sqlSessionFactory = null;
//
//    static{
//        String resource = "/mybatis-config.xml";
//
//        try {
//            InputStream inputStream = SqlUtils.class.getResourceAsStream(resource);
//            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            throw ex;
//        }
//    }
//
//    private static ObjectMapper mapper = new ObjectMapper();
//
//    /**
//     * if param's type is `String`,add single quotation<br>
//     * <p>
//     * if param's type is `datetime`,convert to string and quote <br>
//     */
//    private static String getParameterValue(Object obj) {
//        String value = null;
//        if (obj instanceof String) {
//            value = "'" + obj.toString() + "'";
//        } else if (obj instanceof Date) {
//            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
//            value = "'" + formatter.format(new Date()) + "'";
//        } else if (obj instanceof LocalDateTime) {
//            value = "\'" + ((LocalDateTime) obj).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\'";
//        } else {
//            if (obj != null) {
//                value = obj.toString();
//            } else {
//                value = "";
//            }
//        }
//        return value;
//    }
//
//    public static String getSQLByMap(String functionName, Map argMap) {
//        MappedStatement ms = sqlSessionFactory.getConfiguration().getMappedStatement(functionName);
//        BoundSql boundSql = ms.getBoundSql(argMap);
//        boundSql = ms.getBoundSql(argMap);
//
//        List<ParameterMapping> boundParams = boundSql.getParameterMappings();
//        String sql = "";
//        sql = boundSql.getSql().replaceAll("[\\s]+", " ");
//        for (ParameterMapping param : boundParams) {
//            sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(argMap.get(param.getProperty()))));
//        }
//        return sql;
//    }
//
//    public static Map<String, Object> DTO2ValuedMap(Serializable  pojo){
//        Map<String, Object> map =
//                mapper.convertValue(pojo, new TypeReference<Map<String, Object>>() {});
//        map.values().removeAll(Collections.singleton(null));
//        return map;
//    }
//
//    public static Map<String, Object> DTO2Map(Serializable  pojo){
//        Map<String, Object> map =
//                mapper.convertValue(pojo, new TypeReference<Map<String, Object>>() {});
//        return map;
//    }
//
//    public static String getSQLbyDTO(String mapperFunction, Serializable DTO){
//        Map<String, Object> dto2Map = DTO2Map(DTO);
//        String sql = getSQLByMap(mapperFunction, dto2Map);
//        return sql;
//    }
//}