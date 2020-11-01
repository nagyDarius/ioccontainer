package org.dxworks.ioc.ioccontainer.config;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConfigurationProvider {
	public List<File> getConfigFiles(String... fileNames) {
		return Stream.of(fileNames).map(fileName -> {
			try {
				return new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource(fileName)).toURI());
			} catch (Exception e) {
				System.out.println("Could not read resource file " + fileName);
				System.exit(1);
				return null;
			}
		}).collect(Collectors.toList());
	}
}
