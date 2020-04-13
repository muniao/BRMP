package com.wondersgroup.empi.po.model;

import java.util.Date;

import com.wondersgroup.empi.util.anotation.ColumnName;
import com.wondersgroup.empi.util.anotation.Table;

@Table(name="t_organization",cName="机构信息")
public class Org {
	
	//修改标志
	@ColumnName("")
	private int xgbz=0;
	//模型中本记录主键
	private String Id;
	//记录关联人(或物)的主键
	@ColumnName("")
	private String originId="-";
	//记录生成日期
	@ColumnName("")
	private Date updateTime=new Date();
	//父机构id
	private String parentId;
	//机构代码
	private String orgCode;
	//机构名称
	private String orgName;
	//机构别名
	private String orgAlias;
	//机构缩写
	private String orgAbbreviation;
	//社区行政区划编码
	private String admiDivisionCode;
	//社区行政区划名称
	private String admiDivisionName;
	//创建年份
	private String createYear;
	//修改年份
	private String repealYesr;
	//状态编码
	private String typeCode;
	//状态
	private int status;
	//创建时间
	private Date createDate;
	//省级代码
	private String provinceCode;
	//城市代码
	private String cityCode;
	//区域代码
	private String areaCode;
	
	public int getXgbz() {
		return xgbz;
	}
	public void setXgbz(int xgbz) {
		this.xgbz = xgbz;
	}
	public String getOriginId() {
		return originId;
	}
	public void setOriginId(String originId) {
		this.originId = originId;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgAlias() {
		return orgAlias;
	}
	public void setOrgAlias(String orgAlias) {
		this.orgAlias = orgAlias;
	}
	public String getOrgAbbreviation() {
		return orgAbbreviation;
	}
	public void setOrgAbbreviation(String orgAbbreviation) {
		this.orgAbbreviation = orgAbbreviation;
	}
	public String getAdmiDivisionCode() {
		return admiDivisionCode;
	}
	public void setAdmiDivisionCode(String admiDivisionCode) {
		this.admiDivisionCode = admiDivisionCode;
	}
	public String getAdmiDivisionName() {
		return admiDivisionName;
	}
	public void setAdmiDivisionName(String admiDivisionName) {
		this.admiDivisionName = admiDivisionName;
	}
	public String getCreateYear() {
		return createYear;
	}
	public void setCreateYear(String createYear) {
		this.createYear = createYear;
	}
	public String getRepealYesr() {
		return repealYesr;
	}
	public void setRepealYesr(String repealYesr) {
		this.repealYesr = repealYesr;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	

}
