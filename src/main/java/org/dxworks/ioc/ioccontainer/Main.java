package org.dxworks.ioc.ioccontainer;

import org.dxworks.ioc.ioccontainer.container.IocContainer;
import org.dxworks.ioc.ioccontainer.movie.MovieLister;
import org.dxworks.ioc.ioccontainer.project.Project;

public class Main {
	public static void main(String[] args) {
		final IocContainer container = new IocContainer();
		container.setUp("config1.yaml", "projectConfig.yaml");

		final MovieLister ml = (MovieLister) container.getBean("ml");
		System.out.println(ml);

		final Project project = (Project) container.getBean("project1");
		System.out.println(project);
	}
}
