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

import org.codehaus.plexus.util.cli.CommandLineException;

/**
 * Describes the result of a Subversion invocation.
 * 
 */
public final class DefaultInvocationResult implements InvocationResult {

	/**
	 * The exception that prevented to execute the command line, will be
	 * <code>null</code> if Subversion could be successfully started.
	 */
	private CommandLineException executionException;

	/**
	 * The exit code reported by the Subversion invocation.
	 */
	private int exitCode = Integer.MIN_VALUE;

	/**
	 * Creates a new invocation result
	 */
	DefaultInvocationResult() {
		// hide constructor
	}

	public int getExitCode() {
		return exitCode;
	}

	public CommandLineException getExecutionException() {
		return executionException;
	}

	/**
	 * Sets the exit code reported by the SVN invocation.
	 * 
	 * @param exitCode
	 *            The exit code reported by the SVN invocation.
	 */
	void setExitCode(int exitCode) {
		this.exitCode = exitCode;
	}

	/**
	 * Sets the exception that prevented to execute the command line.
	 * 
	 * @param executionException
	 *            The exception that prevented to execute the command line, may
	 *            be <code>null</code>.
	 */
	void setExecutionException(CommandLineException executionException) {
		this.executionException = executionException;
	}

}
