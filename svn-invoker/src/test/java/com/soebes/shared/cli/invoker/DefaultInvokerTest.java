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

import static org.fest.assertions.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.Collections;

import org.apache.maven.plugin.MojoExecutionException;
import org.codehaus.plexus.util.StringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.soebes.shared.cli.invoker.InvocationRequest.SVNCommands;

public class DefaultInvokerTest extends TestBase {
    
    private Invoker invoker;
    private InvocationRequest request;

    @BeforeMethod
    public void beforeMethod(Method method) throws IOException, URISyntaxException, MojoExecutionException {
	File basedir = getTargetDirFile();

	String logFileNameForTest = StringUtils.addAndDeHump(method.getName());

	System.out.println("Method:" + method.getName()+  " Meta:" + logFileNameForTest);

	invoker = newInvoker();

	request = new DefaultInvocationRequest();
	request.setBaseDirectory(basedir);


	FileLogger flog = setupLogger(basedir, "build-" + logFileNameForTest + ".log");
	request.setErrorHandler(flog);
	request.setOutputHandler(flog);
    }

    @Test
    public void testBuildShouldSucceed() throws IOException, SubversionInvocationException, URISyntaxException, MojoExecutionException {

	request.setCommand(SVNCommands.none);
	request.setShowVersion(true);

	InvocationResult result = invoker.execute(request);

	assertThat(result.getExitCode()).isEqualTo(0);

    }

    @Test
    public void testSvnCommandListShouldSucceed() throws IOException, SubversionInvocationException, URISyntaxException, MojoExecutionException {

	request.setCommand(SVNCommands.list);
	request.setParameters(Collections.singletonList("http://svn.apache.org/repos/asf/"));
	request.setBaseDirectory(getTargetDirFile());
//	invoker.setWorkingDirectory(getTargetDirFile());
	InvocationResult result = invoker.execute(request);

	assertThat(result.getExitCode()).isEqualTo(0);

    }

    @Test
    public void testSvnCommandWhichShouldFailWithAMessageOnStdErr() throws IOException, SubversionInvocationException, URISyntaxException, MojoExecutionException {

	request.setCommand(SVNCommands.info);

	InvocationResult result = invoker.execute(request);

	assertThat(result.getExitCode()).isNotEqualTo(0);
    }

    private FileLogger setupLogger(File basedir, String logFile) throws MojoExecutionException, IOException {
	return new FileLogger(new File(basedir, logFile));
    }

    private Invoker newInvoker() throws IOException {
	Invoker invoker = new DefaultInvoker();

	InvokerLogger logger = new SystemOutLogger();

	logger.setThreshold(InvokerLogger.DEBUG);
	invoker.setLogger(logger);

	return invoker;
    }

}
