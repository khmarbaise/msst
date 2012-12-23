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
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.maven.plugin.MojoExecutionException;
import org.codehaus.plexus.util.StringUtils;
import org.testng.annotations.Test;

import com.soebes.shared.cli.invoker.InvocationRequest.SVNCommands;

public class DefaultInvokerTest {

	private FileLogger setupLogger(File basedir) throws MojoExecutionException, IOException {
		return new FileLogger(new File(basedir, "build.log"));
	}

	@Test
	public void testBuildShouldSucceed() throws IOException,
			MavenInvocationException, URISyntaxException, MojoExecutionException {
		File basedir = getBasedirForBuild();

		Invoker invoker = newInvoker();

		InvocationRequest request = new DefaultInvocationRequest();
		request.setBaseDirectory(basedir);

		FileLogger flog = setupLogger(basedir);
		request.setErrorHandler(flog);
		request.setOutputHandler(flog);

		request.setCommand(SVNCommands.none);
		request.setShowVersion(true);

		InvocationResult result = invoker.execute(request);

		assertThat(result.getExitCode()).isEqualTo(0);

	}

	private Invoker newInvoker() throws IOException {
		Invoker invoker = new DefaultInvoker();

		InvokerLogger logger = new SystemOutLogger();
		logger.setThreshold(InvokerLogger.DEBUG);
		invoker.setLogger(logger);

		return invoker;
	}

	private File getBasedirForBuild() throws URISyntaxException {
		StackTraceElement element = new NullPointerException().getStackTrace()[1];
		String methodName = element.getMethodName();

		String dirName = StringUtils.addAndDeHump(methodName);

		ClassLoader cloader = Thread.currentThread().getContextClassLoader();
		URL dirResource = cloader.getResource(dirName);

		if (dirResource == null) {
			throw new IllegalStateException("Project: " + dirName
					+ " for test method: " + methodName + " is missing.");
		}

		return new File(new URI(dirResource.toString()).getPath());
	}

	// this is just a debugging helper for separating unit test output...
//	private void logTestStart() {
//		NullPointerException npe = new NullPointerException();
//		StackTraceElement element = npe.getStackTrace()[1];
//
//		System.out.println("Starting: " + element.getMethodName());
//	}

}
