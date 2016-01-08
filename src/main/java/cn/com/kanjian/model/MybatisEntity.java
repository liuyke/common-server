package cn.com.kanjian.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.kanjian.annotation.SqlValue;
import cn.com.kanjian.exception.MybatisEntityException;

public class MybatisEntity {

	private String ID_COLUMN_NAME;
	private String TABLE_NAME;
	private List<Field> FIELDS;
	
	/**
	 * 获取实体类对应的表名称
	 * @return
	 */
	protected String getTableName() {
		if(TABLE_NAME != null) {
			return TABLE_NAME;
		}
		Table table = this.getClass().getAnnotation(Table.class);
		if(table == null) {
			throw new MybatisEntityException("class " + this.getClass().getName() + " not set '@Table' annotation");
		}
		return TABLE_NAME = table.name();
	}
	
	/**
	 * 获取当前类所有的字段，不包括"serialVersionUID"字段
	 * @return
	 */
	private List<Field> allFileds() {
		if(FIELDS != null) {
			return FIELDS;
		}
		FIELDS = new ArrayList<Field>();
		Field[] declaredFields = this.getClass().getDeclaredFields();
		for (Field field : declaredFields) {
			if("serialVersionUID".equals(field.getName())) {
				continue;
			}
			FIELDS.add(field);
		}
		return FIELDS;
	}
	
	/**
	 * 为主键对应的字段设值
	 * @param id
	 */
	public void setIdValue(Serializable id) {
		List<Field> allFileds = allFileds();
		try {
			for (Field field : allFileds) {
				if(isIdColunm(field)) {
					field.setAccessible(true);
					field.set(this, id);
					field.setAccessible(false);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据字段名称，获取对应的getter方法名
	 * @param fielName
	 * @return
	 */
	private static final String getMethodName(String fielName) {
		String getMethod = "get" + fielName.substring(0,1).toUpperCase() + fielName.substring(1, fielName.length());
		return getMethod;
	}
	
	/**
	 * 获取传入的属性字段不为空或者没有使用"@SqlValue"注解
	 * @param field
	 * @return
	 */
	private boolean isNotNullField(Field field) {
		String sqlValue = sqlValue(field);
		if(sqlValue != null) {
			return true;
		}
		Object value = getFieldValue(field);
		if(value != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * 获取属性字段的值
	 * @param field
	 * @return
	 */
	private Object getFieldValue(Field field) {
		Method method = fieldGetMethod(field);
		if(method != null) {
			try {
				return method.invoke(this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 根据传入的字段，获取对应的getter方法
	 * @param field
	 * @return
	 */
	private Method fieldGetMethod(Field field) {
		String methodName = getMethodName(field.getName());
		try {
			Method declaredMethod = getClass().getDeclaredMethod(methodName);
			return declaredMethod;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 生成对应实体类的插入sql语句
	 * @return
	 */
	protected String getInsertSql() {
		List<Field> fileds = allFileds();	
		StringBuffer sql = new StringBuffer("INSERT INTO ").append(getTableName()).append("(");
		for (Field field : fileds) {
			if(isNotNullField(field)) {
				sql.append(field.getName()).append(",");
			}
		}
		if(sql.toString().endsWith(",")) {
			sql = sql.deleteCharAt(sql.length() - 1);
		}
		
		sql.append(") VALUES(");
		for (Field field : fileds) {
			if(isNotNullField(field)) {
				String sqlValue = sqlValue(field);
				if(sqlValue != null) {
					sql.append(sqlValue).append(",");
				} else {
					sql.append("#{").append(field.getName()).append("},");
				}
			}
		}
		if(sql.toString().endsWith(",")) {
			sql = sql.deleteCharAt(sql.length() - 1);
		}
		sql.append(")");
		return sql.toString();
	}
	
	/**
	 * 生成对应实体类的删除sql语句
	 * @return
	 */
	protected String getDeleteSql() {
		String idColumnName = getIdColumnName();
		return new StringBuffer("DELETE FROM ").append(getTableName()).append(" WHERE ").append(idColumnName).append("=").append("#{").append(idColumnName).append("}").toString();
	}
	
	/**
	 * 生成对应实体类的更新sql语句
	 * @return
	 */
	protected String getUpdateSql() {
		StringBuffer sql = new StringBuffer("update ").append(getTableName()).append(" set ");
		for (Field field : allFileds()) {
			if(!isIdColunm(field)) {
				String name = field.getName();
				sql.append(name).append("=#{").append(name).append("},");
			}
		}
		if(sql.toString().endsWith(",")) {
			sql = sql.deleteCharAt(sql.length() - 1);
		}
		String idColumnName = getIdColumnName();
		sql.append(" WHERE ").append(idColumnName).append("=").append("#{").append(idColumnName).append("}");
		return sql.toString();
	}
	
	/**
	 * 根据某个属性，获取所设置的"@SqlValue"的值
	 * @param field
	 * @return
	 */
	private final String sqlValue(Field field) {
		if(field.isAnnotationPresent(SqlValue.class)) {
			return field.getAnnotation(SqlValue.class).value();
		}
		Method method = fieldGetMethod(field);
		if(method != null && method.isAnnotationPresent(SqlValue.class)) {
			return method.getAnnotation(SqlValue.class).value();
		}
		return null;
	}
	
	/**
	 * 获取主键对应的属性名称
	 * @return
	 */
	protected String getIdColumnName() {
		if(ID_COLUMN_NAME != null) {
			return ID_COLUMN_NAME;
		}
		try {
			for (Field field : allFileds()) {
				String fieldName = field.getName();
				if(field.isAnnotationPresent(Id.class)) {
					return ID_COLUMN_NAME = fieldName;
				}
				Method method = fieldGetMethod(field);
				if(method != null && method.isAnnotationPresent(Id.class)) {
					return ID_COLUMN_NAME = fieldName;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 判断传入的字段是否是主键对应的字段
	 * @param field
	 * @return
	 */
	private boolean isIdColunm(Field field) {
		if(field.isAnnotationPresent(Id.class)) {
			return true;
		}
		Method method = fieldGetMethod(field);
		if(method != null && method.isAnnotationPresent(Id.class)) {
			return true;
		}
		return false;
	}
	
	
}
