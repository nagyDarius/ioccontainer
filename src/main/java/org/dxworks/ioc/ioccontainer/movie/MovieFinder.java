package org.dxworks.ioc.ioccontainer.movie;

public class MovieFinder {

    private String file;

    public String getFile() {
        return file;
    }

	public void setFile(String file) {
		this.file = file;
    }

    @Override
    public String toString() {
        return file;
    }
}
