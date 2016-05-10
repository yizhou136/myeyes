package com.zy.myeyes.utils;

import com.zy.myeyes.beans.Product;
import com.zy.myeyes.beans.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.Id;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Created by zhougb on 2016/5/6.
 */
public class ModelSqlBuilder<T> {
    private String tableName;
    private String idName;
    private Class<T> mappedClass;
    private Map<String, PropertyDescriptor> mappedFields;
    private Map<String, String> mappedPropertiesFields;
    private Set<String> mappedProperties;
    private Object []   parameterValues;

    public ModelSqlBuilder(){
        /*Type superType = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType)getClass();
        Class<T> clazz = (Class<T>) parameterizedType.getActualTypeArguments()[0];
        initialize(clazz);*/
    }

    public ModelSqlBuilder(Class<T> clazz){
        initialize(clazz);
    }

    protected void initialize(Class<T> mappedClass) {
        this.tableName = lowerCaseName(ClassUtils.getShortName(mappedClass));
        this.mappedClass = mappedClass;
        this.mappedFields = new HashMap<String, PropertyDescriptor>();
        this.mappedProperties = new HashSet<String>();
        this.mappedPropertiesFields = new HashMap<String, String>();
        Field[] fieldes = mappedClass.getDeclaredFields();
        for (Field field: fieldes) {
            Id idAnno = field.getAnnotation(Id.class);
            if (idAnno != null){
                idName = lowerCaseName(field.getName());
            }
        }
        PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(mappedClass);
        for (PropertyDescriptor pd : pds) {
            if (pd.getWriteMethod() != null) {
                String fieldName = pd.getName();
                String property = underscoreName(fieldName);
                this.mappedFields.put(fieldName, pd);
                /*String underscoredName = underscoreName(pd.getName());
                if (!lowerCaseName(pd.getName()).equals(underscoredName)) {
                    this.mappedFields.put(underscoredName, pd);
                }*/
                this.mappedProperties.add(property);
                mappedPropertiesFields.put(property, fieldName);
            }
        }
    }

    public Class<T> getMappedClass() {
        return mappedClass;
    }

    public String  propertyNameToFieldName(String property){
        return mappedPropertiesFields.get(lowerCaseName(property));
    }

    protected String underscoreName(String name) {
        if (!StringUtils.hasLength(name)) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        result.append(lowerCaseName(name.substring(0, 1)));
        for (int i = 1; i < name.length(); i++) {
            String s = name.substring(i, i + 1);
            String slc = lowerCaseName(s);
            if (!s.equals(slc)) {
                result.append("_").append(slc);
            }
            else {
                result.append(s);
            }
        }
        return result.toString();
    }

    /**
     * Convert the given name to lower case.
     * By default, conversions will happen within the US locale.
     * @param name the original name
     * @return the converted name
     * @since 4.2
     */
    protected String lowerCaseName(String name) {
        return name.toLowerCase(Locale.US);
    }


    //final String sql = "insert into user(name, age) value(?, ?)";
    public String buildInsertString(T t, List<Object> paramsObjects){
        //List<Object> paramsObjects = new ArrayList<Object>();
        BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(t);
        //bw.getPropertyValue();
        StringBuilder valuesSB = new StringBuilder(" values(");
        StringBuilder stringBuilder = new StringBuilder("insert into ");
        stringBuilder.append(tableName).append("(");
        Set<String> keys = mappedFields.keySet();
        Iterator<String> iterator = mappedProperties.iterator();
        boolean hasNext = iterator.hasNext();
        while (hasNext){
            String property = iterator.next();
            if (property.equals(idName))
                continue;
            stringBuilder.append(property);
            valuesSB.append("?");
            paramsObjects.add(bw.getPropertyValue(mappedPropertiesFields.get(property)));
            if ((hasNext = iterator.hasNext())) {
                stringBuilder.append(",");
                valuesSB.append(",");
            }
        }
        valuesSB.append(")");
        stringBuilder.append(") ");
        stringBuilder.append(valuesSB.toString());

        return stringBuilder.toString();
    }

    public String buildQueryStringById(){
        return buildQueryString(new String[]{idName});
    }

    public String buildQueryString(String [] byColumes){
        StringBuilder sqlBuilder = new StringBuilder("select ");
        Iterator<String> iterator = mappedProperties.iterator();
        boolean hasNext = iterator.hasNext();
        while (hasNext){
            String property = iterator.next();
            sqlBuilder.append(property);
            if ((hasNext = iterator.hasNext())) {
                sqlBuilder.append(", ");
            }
        }
        sqlBuilder.append(" from ").append(tableName);
        if (byColumes != null && byColumes.length > 0) {
            sqlBuilder.append(" where ");
            for (int i=0; i<byColumes.length; i++){
                sqlBuilder.append(byColumes[i]).append(" = ?");
                if (i < (byColumes.length - 1))
                    sqlBuilder.append(" and ");
            }
        }
        //"select uid, age, name from user where uid = ?";
        return sqlBuilder.toString();
    }

    public String buildUpdateString(Class clazz){

        return null;
    }

    public String buildDeleteString(Class clazz){

        return null;
    }

    public static void main(String args[]){
        ModelSqlBuilder<Product> modelSqlBuilder = new ModelSqlBuilder<Product>(Product.class);
        //ModelSqlBuilder<User> modelSqlBuilder = new ModelSqlBuilder<User>();
        Product product = new Product();
        product.setType(32);
        product.setProductName("sdfasdf");
        product.setProductDesc("afasd");
        List<Object> list  = new ArrayList<Object>();
        System.out.println(modelSqlBuilder.buildInsertString(product, list));
        for (Object obj : list){
            System.out.println(obj);
        }

        System.out.println(modelSqlBuilder.buildQueryString(new String[]{"id", "323", "23"}));
    }
}
