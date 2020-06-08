package com.wondersgroup.empi.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import com.wondersgroup.empi.dao.intf.CommonDaoIntf;
import com.wondersgroup.empi.util.common.BaseResource;
import com.wondersgroup.empi.util.common.CommonUtil;
import com.wondersgroup.empi.util.daoutil.CommonSql;
import com.wondersgroup.empi.util.daoutil.ParamMapUtil;

@Repository
public class CommonDaoImpl implements CommonDaoIntf {
	
	@Resource(name = "jdbcTemplate")
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Resource(name = "jdbcTemplate_join")
	private NamedParameterJdbcTemplate jdbcTemplate_join;
	
	@Autowired
	private BaseResource baseResource;
	
	@Override
	public boolean isTable(String tableName) {
		String sql = "select count(1) from ".concat(tableName);
		System.out.println(sql);
		try{
			jdbcTemplate.queryForObject(sql, new HashMap<String,Object>(), Integer.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;//表不存在
		}
		return true;
	}

	@Override
	public String delDropTable(String tableName) {
		String sql = "drop table ".concat(tableName);
		System.out.println(sql);
		try {
			jdbcTemplate.update(sql, new HashMap<String,Object>());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return tableName.concat("drop失败");
		}
		return tableName.concat("drop完成");
	}

	@Override
	public String createTable(String createSql) {
		try {
			jdbcTemplate.update(createSql, new HashMap<String,Object>());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "建立模型失败";
		}
		return "建立模型完成";
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> String saveObj(List<T> list, String tableName) {
		Class<T> clazz = (Class<T>) list.get(0).getClass();
		//System.out.println(clazz.getSimpleName());
		String sql;
		if ("java.util.HashMap".equals(clazz.getName())) {//使用map的封装保存数据
			sql = CommonSql.insertSql4Map((Map<String, Object>)list.get(0), tableName);
		} else {//使用类的list保存数据
			sql = CommonSql.insertSql(clazz, tableName);
		}
		System.out.println("开始保存save_sql".concat(clazz.getName()).concat(":").concat(sql));
		Map<String,Object> paramMap;
		for (int i=0;i<list.size();i++) {
			try {
				if ("java.util.HashMap".equals(clazz.getName())) {//使用map的封装保存数据
					paramMap = (Map<String, Object>) list.get(i);
				} else {//使用类的list保存数据
					ParamMapUtil.setNull(list.get(i));
					paramMap = ParamMapUtil.getParamMap(list.get(i));
				}
				jdbcTemplate.update(sql, paramMap);
			} catch (Exception e) {
				e.printStackTrace();
				return "数据保存出错save_error:".concat(e.getMessage());
			} 
		}
		return "数据保存完成save_complates";
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> String saveObj(Class<T> clazz, Object Object, String tableName) {
		String sql;
		if ("java.util.HashMap".equals(clazz.getName())) {//使用map的封装保存数据
			sql = CommonSql.insertSql4Map((Map<String, Object>)Object, tableName);
		} else {//使用类的list保存数据
			sql = CommonSql.insertSql(clazz, tableName);
		}
		System.out.println("开始保存save_sql".concat(clazz.getName()).concat(":").concat(sql));
		Map<String,Object> paramMap;
		try {
			if ("java.util.HashMap".equals(clazz.getName())) {//使用map的封装保存数据
				paramMap = (Map<String, Object>) Object;
			} else {//使用类的保存数据方式
				ParamMapUtil.setNull(Object);
				paramMap = ParamMapUtil.getParamMap(Object);
			}
			jdbcTemplate.update(sql, paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			return "数据保存出错save_error:".concat(e.getMessage());
		} 
		return "数据保存完成save_complates";
	}

	

	@Override
	public List<Map<String, Object>> selectObj(List<String> attributeNames, String tableName, int pageNo, int pageSize) {
		String sql = CommonSql.selectSql4Map(attributeNames,tableName,pageNo,pageSize,baseResource.getJdbcDriverClassName());
		System.out.println(sql);
		List<Map<String, Object>> objList = jdbcTemplate.queryForList(sql, new HashMap<String,Object>());
		return objList;
	}
	
	@Override
	public <T> List<T> selectObj(Class<T> clazz, String tableName, int pageNo, int pageSize) {
		String sql = CommonSql.selectSql4Obj(clazz,tableName,pageNo,pageSize,baseResource.getJdbcDriverClassName());
		System.out.println(sql);
		List<T> objList = null;
		try {
			objList = jdbcTemplate.query(sql, new HashMap<String,Object>(),new BeanPropertyRowMapper<T>(clazz));
		} catch (Exception e) {
			//没有查询返回空null
		}
		return objList;
	}

	
	
	
	@Override
	public String delTruncateTable(String tableName) {
		try {
			jdbcTemplate.update("truncate table ".concat(tableName), new HashMap<String,Object>());
		} catch (Exception e) {
			e.printStackTrace();
			return "清空表出错";
		}	
		return "清空表完成";
	}

	
	@Override
	public String updateObj(Object object, String tableName, String fieldNameById) {
		Map<String,Object> paramMap = CommonUtil.object2Map(object);
		String sql = CommonSql.updateSql(paramMap,tableName,fieldNameById);
		System.out.println(sql);
		try {
			jdbcTemplate.update(sql, paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			return "更新出错";
		}
		return "更新完成";
	}

	@Override
	public <T> Object selectObjByParam(Class<T> clazz, String tableName, Map<String, Object> paramMap) {
		String sql = CommonSql.selectSql(clazz, tableName, paramMap);
		System.out.println(sql);
		Object object = null;
		try {
			object = jdbcTemplate.queryForObject(sql, paramMap,new BeanPropertyRowMapper<T>(clazz));
		} catch (Exception e) {
			//没有查询返回空null
			//object = new Object();
		}
		return object;
	}
	
	@Override
	public <T> List<T> selectObjListByParam(Class<T> clazz, String tableName, Map<String, Object> paramMap) {
		String sql = CommonSql.selectSql(clazz, tableName, paramMap);
		System.out.println(sql);
		List<T> list = null;
		try {
			list = jdbcTemplate.query(sql, paramMap,new BeanPropertyRowMapper<T>(clazz));
		} catch (Exception e) {
			//没有查询返回空null
		}
		return list;
	}

	
	@Override
	public <T> List<T> selectObjByParamlist(Class<T> clazz, String tableName, Map<String, Object> paramMap) {
		String sql = CommonSql.selectSqlParamlist(clazz, tableName, paramMap);
		System.out.println(sql);
		List<T> list = null;
		try {
			list = jdbcTemplate.query(sql, paramMap,new BeanPropertyRowMapper<T>(clazz));
		} catch (Exception e) {
			list = new ArrayList<T>();
		}
		return list;
	}

	@Override
	public int getRecords(String tableName) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		String sql = "select count(1) from ".concat(tableName);
		System.out.println(sql);
		int i = -1;
		try {
			i = jdbcTemplate.queryForObject(sql , paramMap, Integer.class);
		} catch (Exception e) {
			
		}
		return i;
	}

	@Override
	public <T> int batchPreparedStatementSetter(List<T> list, String tableName) {
		
		Class<T> clazz = (Class<T>) list.get(0).getClass();
		//System.out.println(clazz.getSimpleName());
		String sql;
		if ("java.util.HashMap".equals(clazz.getName())) {//使用map的封装保存数据
			sql = CommonSql.insertSql4Map((Map<String, Object>)list.get(0), tableName);
		} else {//使用类的list保存数据
			sql = CommonSql.insertSql(clazz, tableName);
		}
		System.out.println("开始保存save_sql".concat(clazz.getName()).concat(":").concat(sql));
		
		SqlParameterSource[] params = SqlParameterSourceUtils  
                .createBatch(list.toArray());
		
		jdbcTemplate_join.batchUpdate(sql, params);				
		
		return 1;
	}
	

}
