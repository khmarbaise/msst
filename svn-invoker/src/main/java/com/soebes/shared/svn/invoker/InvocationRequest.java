package com.soebes.shared.svn.invoker;

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
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Specifies the parameters used to control a SVN invocation.
 */
public interface InvocationRequest {

    /**
     * The possible Subversion commands
     */
    public enum SVNCommands {
        none, checkout, checkin, merge, list, info, resolve, update,
    }

    SVNCommands getCommand();

    InvocationRequest setCommand(SVNCommands command);

    List<String> getParameters();

    /**
     * <pre>
     *   svn checkout URL destinationFolder
     * </pre>
     * 
     * @param parameters
     * @return
     */
    InvocationRequest setParameters(List<String> parameters);

    /**
     * svn -R option
     * 
     * @return <code>true</code> if sub modules should be build,
     *         <code>false</code> otherwise.
     */
    boolean isRecursive();

    InvocationRequest setRecursive(boolean recursive);

    String getUsername();

    InvocationRequest setUsername(String username);

    String getPassword();

    InvocationRequest setPassword(String password);

    boolean isNoAuthCache();

    InvocationRequest setNoAuthCache(boolean noAuthCache);

    boolean isNonInteractive();

    /**
     * set the <code>--non-interactive</code> option on SVN command line.
     * 
     * @param nonInteractive
     * @return
     */
    InvocationRequest setNonInteractive(boolean nonInteractive);

    boolean isTrustServerCert();

    InvocationRequest setTrustServerCert(boolean trustServerCert);

    File getConfigDir();

    InvocationRequest setConfigDir(File configDir);

    String getConfigOption();

    InvocationRequest setConfigOption(String configOption);

    /**
     * Indicates whether the environment variables of the current process should
     * be propagated to the SVN invocation. By default, the current environment
     * variables are inherited by the new SVN invocation.
     * 
     * @return <code>true</code> if the environment variables should be
     *         propagated, <code>false</code> otherwise.
     */
    boolean isShellEnvironmentInherited();

    /**
     * Gets the input stream used to provide input for the invoked SVN build.
     * This is in particular useful when invoking SVN in interactive mode.
     * 
     * @return The input stream used to provide input for the invoked SVN build
     *         or <code>null</code> if not set.
     */
    InputStream getInputStream(InputStream defaultStream);

    /**
     * Gets the handler used to capture the standard output from the SVN build.
     * 
     * @return The output handler or <code>null</code> if not set.
     */
    InvocationOutputHandler getOutputHandler(InvocationOutputHandler defaultHandler);

    /**
     * Gets the handler used to capture the error output from the SVN build.
     * 
     * @return The error handler or <code>null</code> if not set.
     */
    InvocationOutputHandler getErrorHandler(InvocationOutputHandler defaultHandler);

    /**
     * Gets the path to the base directory of the POM for the Maven invocation.
     * If {@link #getPomFile()} does not return <code>null</code>, this setting
     * only affects the working directory for the Maven invocation.
     * 
     * @return The path to the base directory of the POM or <code>null</code> if
     *         not set.
     */
    File getBaseDirectory();

    /**
     * Gets the path to the base directory of the POM for the Maven invocation.
     * If {@link #getPomFile()} does not return <code>null</code>, this setting
     * only affects the working directory for the Maven invocation.
     * 
     * @param defaultDirectory
     *            The default base directory to use if none is configured for
     *            this request, may be <code>null</code>.
     * @return The path to the base directory of the POM or <code>null</code> if
     *         not set.
     */
    File getBaseDirectory(File defaultDirectory);

    /**
     * Gets the environment variables for the Maven invocation.
     * 
     * @return The environment variables for the Maven invocation or
     *         <code>null</code> if not set.
     */
    Map<String, String> getShellEnvironments();

    /**
     * The show version behaviour (--version option)
     * 
     * @return The show version behaviour
     */
    boolean isShowVersion();

    /**
     * Sets the input stream used to provide input for the invoked Maven build.
     * This is in particular useful when invoking Maven in interactive mode.
     * 
     * @param inputStream
     *            The input stream used to provide input for the invoked Maven
     *            build, may be <code>null</code> if not required.
     * @return This invocation request.
     */
    InvocationRequest setInputStream(InputStream inputStream);

    /**
     * Sets the handler used to capture the standard output from the Maven
     * build.
     * 
     * @param outputHandler
     *            The output handler, may be <code>null</code> if the output is
     *            not of interest.
     * @return This invocation request.
     */
    InvocationRequest setOutputHandler(InvocationOutputHandler outputHandler);

    /**
     * Sets the handler used to capture the error output from the Maven build.
     * 
     * @param errorHandler
     *            The error handler, may be <code>null</code> if the output is
     *            not of interest.
     * @return This invocation request.
     */
    InvocationRequest setErrorHandler(InvocationOutputHandler errorHandler);

    /**
     * Sets the path to the base directory of SVN invocation.
     * 
     * @param basedir
     *            The path to the base directory of the POM, may be
     *            <code>null</code> if not used.
     * @return This invocation request.
     */
    InvocationRequest setBaseDirectory(File basedir);

    /**
     * Sets the system properties for the Maven invocation.
     * 
     * @param properties
     *            The system properties for the Maven invocation, may be
     *            <code>null</code> if not set.
     * @return This invocation request.
     */
    InvocationRequest setProperties(Properties properties);

    /**
     * Specifies whether the environment variables of the current process should
     * be propagated to the Maven invocation.
     * 
     * @param shellEnvironmentInherited
     *            <code>true</code> if the environment variables should be
     *            propagated, <code>false</code> otherwise.
     * @return This invocation request.
     */
    InvocationRequest setShellEnvironmentInherited(boolean shellEnvironmentInherited);

    /**
     * Adds the specified environment variable to the Maven invocation.
     * 
     * @param name
     *            The name of the environment variable, must not be
     *            <code>null</code>.
     * @param value
     *            The value of the environment variable, must not be
     *            <code>null</code>.
     * @return This invocation request.
     */
    InvocationRequest addShellEnvironment(String name, String value);

    /**
     * enable displaying version without stopping the build Equivalent of
     * {@code ---version}
     * 
     * @param showVersion
     *            enable displaying version
     * @return This invocation request.
     */
    InvocationRequest setShowVersion(boolean showVersion);

}
