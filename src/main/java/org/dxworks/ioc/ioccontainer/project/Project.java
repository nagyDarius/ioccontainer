package org.dxworks.ioc.ioccontainer.project;

public class Project {
	private Owner owner;
	private Integer value;

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Project{\n\towner: " + owner + "\n\tvalue: " + value + "\n}";
	}
}
