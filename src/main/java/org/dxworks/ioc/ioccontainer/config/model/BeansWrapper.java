package org.dxworks.ioc.ioccontainer.config.model;

import java.util.List;

public class BeansWrapper {
	private List<BeanConfig> beans;

	public List<BeanConfig> getBeans() {
		return beans;
	}

	public void setBeans(List<BeanConfig> beans) {
		this.beans = beans;
	}
}
