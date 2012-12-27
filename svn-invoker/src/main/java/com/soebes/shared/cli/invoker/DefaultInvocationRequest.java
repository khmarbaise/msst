package com.soebes.shared.cli.invoker;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Specifies the parameters used to control a SVN invocation.
 *
 * @version $Id: DefaultInvocationRequest.java 1392618 2012-10-01 21:20:53Z
 *          rfscholte $
 */
public class DefaultInvocationRequest implements InvocationRequest {

    private SVNCommands command;
    private List<String> paremeters;
    private boolean recursive;
    private String username;
    private String password;
    private boolean noAuthCache;
    private boolean nonInteractive;
    private boolean trustServerCert;
    private File configDir;
    private String configOption;
    private InputStream inputStream;
    private InvocationOutputHandler outputHandler;
    private InvocationOutputHandler errorHandler;
    private File baseDirectory;
    private boolean showVersion;

    public DefaultInvocationRequest() {
        setParameters(new ArrayList<String>());
    }

    public SVNCommands getCommand() {
        return this.command;
    }

    public InvocationRequest setCommand(SVNCommands command) {
        this.command = command;
        return this;
    }

    public List<String> getParameters() {
        return paremeters;
    }

    public InvocationRequest setParameters(List<String> parameters) {
        this.paremeters = parameters;
        return this;
    }

    public boolean isRecursive() {
        return recursive;
    }

    public InvocationRequest setRecursive(boolean recursive) {
        this.recursive = recursive;
        return this;
    }

    public String getUsername() {
        return this.username;
    }

    public InvocationRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return this.password;
    }

    public InvocationRequest setPassword(String password) {
        this.password = password;
        return this;
    }

    public boolean isNoAuthCache() {
        return this.noAuthCache;
    }

    public InvocationRequest setNoAuthCache(boolean noAuthCache) {
        this.noAuthCache = noAuthCache;
        return this;
    }

    public boolean isNonInteractive() {
        return this.nonInteractive;
    }

    public InvocationRequest setNonInteractive(boolean nonInteractive) {
        this.nonInteractive = nonInteractive;
        return this;
    }

    public boolean isTrustServerCert() {
        return this.trustServerCert;
    }

    public InvocationRequest setTrustServerCert(boolean trustServerCert) {
        this.trustServerCert = trustServerCert;
        return this;
    }

    public File getConfigDir() {
        return this.configDir;
    }

    public InvocationRequest setConfigDir(File configDir) {
        this.configDir = configDir;
        return this;
    }

    public String getConfigOption() {
        return this.configOption;
    }

    public InvocationRequest setConfigOption(String configOption) {
        this.configOption = configOption;
        return this;
    }

    public boolean isShellEnvironmentInherited() {
        // TODO Auto-generated method stub
        return false;
    }

    public InputStream getInputStream(InputStream defaultStream) {
        return this.inputStream;
    }

    public InvocationOutputHandler getOutputHandler(
            InvocationOutputHandler defaultHandler) {
        return this.outputHandler;
    }

    public InvocationOutputHandler getErrorHandler(
            InvocationOutputHandler defaultHandler) {
        return this.errorHandler;
    }

    public File getBaseDirectory() {
        return this.baseDirectory;
    }

    public File getBaseDirectory(File defaultDirectory) {
        // TODO Auto-generated method stub
        return null;
    }

    public Map<String, String> getShellEnvironments() {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean isShowVersion() {
        return this.showVersion;
    }

    public InvocationRequest setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
        return this;
    }

    public InvocationRequest setOutputHandler(
            InvocationOutputHandler outputHandler) {
        this.outputHandler = outputHandler;
        return this;
    }

    public InvocationRequest setErrorHandler(
            InvocationOutputHandler errorHandler) {
        this.errorHandler = errorHandler;
        return this;
    }

    public InvocationRequest setBaseDirectory(File basedir) {
        this.baseDirectory = basedir;
        return this;
    }

    public InvocationRequest setProperties(Properties properties) {
        // TODO Auto-generated method stub
        return null;
    }

    public InvocationRequest setShellEnvironmentInherited(
            boolean shellEnvironmentInherited) {
        // TODO Auto-generated method stub
        return null;
    }

    public InvocationRequest addShellEnvironment(String name, String value) {
        // TODO Auto-generated method stub
        return null;
    }

    public InvocationRequest setShowVersion(boolean showVersion) {
        this.showVersion = showVersion;
        return this;
    }

}
