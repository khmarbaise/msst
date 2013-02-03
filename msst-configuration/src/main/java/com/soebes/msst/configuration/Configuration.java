package com.soebes.msst.configuration;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Configuration {

    private List<Repository> repositories;

    public List<Repository> getRepositories() {
	return repositories;
    }

    public void setRepositories(List<Repository> repositories) {
	this.repositories = repositories;
    }

}
