package org.dxworks.ioc.ioccontainer.project;

public class Owner {
	private Project project;
	private String name;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Owner{\n\t\tprojectValue: " + (project == null ? "null" : project.getValue()) + "\n\t\tname: " + name + "\n\t}";
	}
}
