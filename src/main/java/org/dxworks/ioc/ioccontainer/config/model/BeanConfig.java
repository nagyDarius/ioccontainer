package org.dxworks.ioc.ioccontainer.config.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BeanConfig {
	private String id;
	private String clazz;
	private List<PropertyConfig> properties;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClazz() {
		return clazz;
	}

	@JsonProperty("class")
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public List<PropertyConfig> getProperties() {
		return properties;
	}

	public void setProperties(List<PropertyConfig> properties) {
		this.properties = properties;
	}
}
