package com.wondersgroup.empi.service.impl;

import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;

import com.wondersgroup.empi.dao.intf.CommonDaoIntf;
import com.wondersgroup.empi.po.RequestPo;
import com.wondersgroup.empi.service.intf.ReqEMPICenterIntf;
import com.wondersgroup.empi.util.anotation.Table;
import com.wondersgroup.empi.util.common.BaseResource;
import com.wondersgroup.empi.util.common.CommonUtil;
import com.wondersgroup.empi.util.common.RestCXFClient;

@Service
public class ReqEMPICenterImpl implements ReqEMPICenterIntf {
	
	@Autowired
	private BaseResource baseResource;
	
	@Autowired CommonDaoIntf commonDaoIntf;
	
	@Test	
	@Rollback(true)  //标明使用完此方法后事务不回滚,true时为回滚 
	@Override
	public <T> String ReqEMPICenter4Model(Class<T> clazz) {
		//System.out.println(baseResource.getBRMPUsername());
		//System.out.println(baseResource.getBRMPPassword());

		RequestPo reqPo = new RequestPo();
				
		reqPo.setUsername(baseResource.getBRMPUsername());
		reqPo.setPassword(baseResource.getBRMPPassword());
		reqPo.setParamType("model");
		
		Table table = clazz.getAnnotation(Table.class);
		
		reqPo.setModelType(table.cName());
		
		int records = commonDaoIntf.getRecords(table.name());//总记录数
		int pageSize = 5000;//分页数
		int totalPage;//总页数
		if(records % pageSize == 0){
			totalPage = records / pageSize;
		}else{
			totalPage = records / pageSize + 1;
		}
		
		for (int i=0;i<totalPage;i++) {
			
			List<T> lists = commonDaoIntf.selectObj(clazz, table.name(), i+1, pageSize);
			
			System.out.println(lists.size());
			
			
//			reqPo.setParams(CommonUtil.toJSONString(lists));
//			String json = CommonUtil.toJSONString(reqPo);
//			String string = RestCXFClient.reqEMPICenter(baseResource.getEMPICenterAdress(), json);
//			System.out.println(string);
			
		}
		return "";
	}
	/*
	 {
"modelType":"测试新建模型1",
"paramType":"model",
"params":"[{\"originId\":\"test005\",
			\"Id\":\"521203198706302947\",
			\"updateTime\":\"2019-01-01\",
			\"Along1\":20,\"自定义字段5\":0}, 
		   {\"originId\":\"test006\",
		    \"Id\":\"521311197611264522\",
			\"updateTime\":\"2019-01-01 12:41:52\",
			\"Along1\":\"1\",
			\"Along1\":\"1\",
			\"aLong2\":0},
		   {\"originId\":\"test007\",
		    \"Id\":\"521203198706302947\",
			\"updateTime\":\"2019-01-01 12:41:52\",
			\"Along1\":\"899999999\",
			\"aLong2\":0},
		   {\"originId\":\"testNull\",
		    \"aLong2\":0}   
		  ]",
"password":"123",
"username":"test_system1"
}
	  
	  
	  
	 */
	
	

}
