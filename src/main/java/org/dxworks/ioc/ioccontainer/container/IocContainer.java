package org.dxworks.ioc.ioccontainer.container;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.dxworks.ioc.ioccontainer.config.ConfigurationProvider;
import org.dxworks.ioc.ioccontainer.config.model.BeansWrapper;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class IocContainer {

	private final ConfigurationProvider configurationProvider = new ConfigurationProvider();
	private final ObjectMapper mapper = new ObjectMapper(new YAMLFactory()) {{
		findAndRegisterModules();
	}};
	private final BeanFactory beanFactory = new BeanFactory();
	private Map<String, Object> beans;

	public void setUp(String... configFiles) {
		beans = beanFactory.instantiateObjects(configurationProvider.getConfigFiles(configFiles).stream().map(file -> {
			try {
				return mapper.readValue(file, BeansWrapper.class);
			} catch (IOException e) {
				System.out.println("Could not parse file " + file.getName());
				e.printStackTrace();
				System.exit(1);
				return null;
			}
		}).flatMap(beansWrapper -> beansWrapper.getBeans().stream())
				.collect(Collectors.toList()));
		System.out.println();
	}

	public Object getBean(String id) {
		return beans.get(id);
	}

}
