package com.soebes.msst.configuration;

import java.util.List;

public class Repository {

    private String id;
    private List<Module> modules;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public List<Module> getModules() {
	return modules;
    }

    public void setModules(List<Module> modules) {
	this.modules = modules;
    }

}
