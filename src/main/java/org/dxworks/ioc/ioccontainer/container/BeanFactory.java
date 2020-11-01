package org.dxworks.ioc.ioccontainer.container;

import org.dxworks.ioc.ioccontainer.config.model.BeanConfig;
import org.dxworks.ioc.ioccontainer.config.model.PropertyConfig;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class BeanFactory {
	public Map<String, Object> instantiateObjects(List<BeanConfig> beans) {
		final Map<String, Object> objects = beans.stream().collect(Collectors.toMap(BeanConfig::getId, bean -> {
			final String clazz = bean.getClazz();
			try {
				return Class.forName(clazz).getDeclaredConstructor().newInstance();
			} catch (Exception e) {
				System.out.println("Could not instantiate object of type " + clazz);
				System.exit(1);
				return null;
			}
		}));

		beans.forEach(bean -> {
			Object o = objects.get(bean.getId());

			bean.getProperties().forEach(property -> {
				final Class<?> clazz = o.getClass();
				final Field field = Arrays.stream(clazz.getDeclaredFields())
						.filter(f -> f.getName().equals(property.getName()))
						.findFirst()
						.orElseThrow(() -> new RuntimeException(new NoSuchFieldException("No such field " + property.getName() + " on class " + bean.getClazz())));
				try {
					final Method setter = clazz.getMethod(getSetterName(property.getName()), field.getType());
					setter.invoke(o, Optional.ofNullable(property.getRef()).map(objects::get).orElseGet(() -> getPropertyValue(property, field.getType())));
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			});
		});
		return objects;
	}

	private Object getPropertyValue(PropertyConfig property, Class<?> type) {
		try {
			final String valueOf = "valueOf";
			Method m;
			if (type.equals(String.class)) {
				m = type.getDeclaredMethod(valueOf, Object.class);
			} else if (type.equals(Integer.class)) {
				m = type.getDeclaredMethod(valueOf, String.class);
			} else if (type.equals(Double.class)) {
				m = type.getDeclaredMethod(valueOf, String.class);
			} else if (type.equals(Boolean.class)) {
				m = type.getDeclaredMethod(valueOf, String.class);
			} else {
				m = type.getDeclaredMethod(valueOf, Object.class);
			}
			return m.invoke(null, property.getValue());
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

	private String getSetterName(String propertyName) {
		return "set" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
	}
}
