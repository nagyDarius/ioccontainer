package org.dxworks.ioc.ioccontainer.movie;

public class MovieLister {

	private MovieFinder finder;

	public MovieFinder getFinder() {
		return finder;
	}

	public void setFinder(MovieFinder finder) {
		this.finder = finder;
	}

	@Override
	public String toString() {
		return "MovieLister{\n\tMovieFinder: " + finder + "\n}";
	}
}
