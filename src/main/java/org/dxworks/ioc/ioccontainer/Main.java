package org.dxworks.ioc.ioccontainer;

import org.dxworks.ioc.ioccontainer.container.IocContainer;
import org.dxworks.ioc.ioccontainer.movie.MovieLister;

public class Main {
	public static void main(String[] args) {
		final IocContainer container = new IocContainer();
		container.setUp("config1.yaml");
		final MovieLister ml = (MovieLister) container.getBean("ml");
		System.out.println(ml);
	}
}
