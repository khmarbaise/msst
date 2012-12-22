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

import org.codehaus.plexus.util.StringUtils;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.testng.annotations.Test;

import com.soebes.shared.cli.invoker.InvocationRequest.SVNCommands;

public class DefaultInvokerTest {

	@Test
	public void testBuildShouldSucceed() throws IOException,
			MavenInvocationException, URISyntaxException {
		File basedir = getBasedirForBuild();

		Invoker invoker = newInvoker();

		InvocationRequest request = new DefaultInvocationRequest();
		request.setBaseDirectory(basedir);

		request.setCommand(SVNCommands.none);
		request.setShowVersion(true);

//		List<String> goals = new ArrayList<String>();
//		goals.add("clean");
//		goals.add("package");

//		request.setGoals(goals);

		InvocationResult result = invoker.execute(request);

		assertThat(result.getExitCode()).isEqualTo(0);
	}

	private Invoker newInvoker() throws IOException {
		Invoker invoker = new DefaultInvoker();

//		invoker.setMavenHome(findMavenHome());

		InvokerLogger logger = new SystemOutLogger();
		logger.setThreshold(InvokerLogger.DEBUG);
		invoker.setLogger(logger);

//		invoker.setLocalRepositoryDirectory(findLocalRepo());

		return invoker;
	}

	private File findMavenHome() throws IOException {
		String mavenHome = System.getProperty("maven.home");

		if (mavenHome == null) {
			mavenHome = CommandLineUtils.getSystemEnvVars().getProperty(
					"M2_HOME");
		}

		if (mavenHome == null) {
			throw new IllegalStateException(
					"Cannot find Maven application "
							+ "directory. Either specify \'maven.home\' system property, or M2_HOME environment variable.");
		}

		return new File(mavenHome);
	}

	private File findLocalRepo() {
		String basedir = System.getProperty("maven.repo.local", "");

		if (StringUtils.isNotEmpty(basedir)) {
			return new File(basedir);
		}

		return null;
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
	private void logTestStart() {
		NullPointerException npe = new NullPointerException();
		StackTraceElement element = npe.getStackTrace()[1];

		System.out.println("Starting: " + element.getMethodName());
	}

}
