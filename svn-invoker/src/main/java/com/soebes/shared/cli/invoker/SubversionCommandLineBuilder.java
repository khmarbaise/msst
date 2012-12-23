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
import java.io.IOException;

import org.codehaus.plexus.util.cli.Commandline;

import com.soebes.shared.cli.invoker.InvocationRequest.SVNCommands;

/**
 */
public class SubversionCommandLineBuilder {

	private static final InvokerLogger DEFAULT_LOGGER = new SystemOutLogger();

	private InvokerLogger logger = DEFAULT_LOGGER;

	private File workingDirectory;

	private File svnExecutable;

	public Commandline build(InvocationRequest request)
			throws CommandLineConfigurationException {
		try {
			checkRequiredState();
		} catch (IOException e) {
			throw new CommandLineConfigurationException(e.getMessage(), e);
		}
		File svn = null;
		try {
			svn = findSVNExecutable();
		} catch (CommandLineConfigurationException e) {
			throw new CommandLineConfigurationException(e.getMessage(), e);
		}

		Commandline cli = new Commandline();

		cli.setExecutable(svn.getAbsolutePath());

		// global options
		setFlags(request, cli);

		// Things like svn list, svn checkout etc.
		setCommand(request, cli);

		// // handling for OS-level envars
		// setShellEnvironment(request, cli);

		setParameter(request, cli);

		return cli;
	}

	protected void checkRequiredState() throws IOException {
		if (logger == null) {
			throw new IllegalStateException("A logger instance is required.");
		}

		// if ((svnHome == null) && (System.getProperty("maven.home") == null))
		// // can be restored with 1.5
		// // && ( System.getenv( "M2_HOME" ) != null ) )
		// {
		// if (!getSystemEnvVars().containsKey("M2_HOME")) {
		// throw new IllegalStateException(
		// "Maven application directory was not "
		// + "specified, and ${maven.home} is not provided in the system "
		// + "properties. Please specify at least on of these.");
		// }
		// }
	}

	protected void setFlags(InvocationRequest request, Commandline cli) {
		if (request.isNoAuthCache()) {
			cli.createArg().setValue("--no-auth-cache");
		}
		if (request.isNonInteractive()) {
			cli.createArg().setValue("--non-interactive");
		}
		if (request.isShowVersion()) {
			cli.createArg().setValue("--version");
		}
		if (request.isTrustServerCert()) {
			cli.createArg().setValue("--trust-server-cert");
		}

	}

	protected void setCommand(InvocationRequest request, Commandline cli) {
		if (!request.getCommand().equals(SVNCommands.none)) {
			String cmd = request.getCommand().toString();
			cli.createArg().setValue(cmd);
		}
	}

	protected void setParameter(InvocationRequest request, Commandline cli) {
		for (String item : request.getParameters()) {
			cli.createArg().setValue(item);
		}
	}

	protected File findSVNExecutable() throws CommandLineConfigurationException {
		String executableName = "svn";
		File fullyQualifiedExecutable = findExecutableOnPath(executableName);

		if (fullyQualifiedExecutable != null) {
			logger.debug("File location "
					+ fullyQualifiedExecutable.getAbsolutePath());
		} else {
			throw new CommandLineConfigurationException(
					"svn executable not found at: " + fullyQualifiedExecutable);

		}

		return fullyQualifiedExecutable;
	}

	private static File findExecutableOnPath(String executableName) {
		String systemPath = System.getenv("PATH");
		String[] pathDirs = systemPath.split(File.pathSeparator);

		File fullyQualifiedExecutable = null;
		for (String pathDir : pathDirs) {
			File file = new File(pathDir, executableName);
			if (file.isFile()) {
				fullyQualifiedExecutable = file;
				break;
			}
		}
		return fullyQualifiedExecutable;
	}

	public InvokerLogger getLogger() {
		return logger;
	}

	public void setLogger(InvokerLogger logger) {
		this.logger = logger;
	}

	public File getWorkingDirectory() {
		return workingDirectory;
	}

	public void setWorkingDirectory(File workingDirectory) {
		this.workingDirectory = workingDirectory;
	}

	/**
	 * {@code mavenExecutable} can either be relative to ${maven.home}/bin/ or
	 * absolute
	 * 
	 * @param mavenExecutable
	 *            the executable
	 */
	public void setSubversionExecutable(File mavenExecutable) {
		this.svnExecutable = mavenExecutable;
	}

	public File getSubversionExecutable() {
		return svnExecutable;
	}

}
