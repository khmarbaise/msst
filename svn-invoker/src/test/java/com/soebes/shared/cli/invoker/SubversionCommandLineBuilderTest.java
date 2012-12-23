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
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.codehaus.plexus.util.FileUtils;
import org.codehaus.plexus.util.IOUtil;
import org.codehaus.plexus.util.cli.Commandline;
import org.testng.annotations.Test;

import com.google.common.base.Joiner;
import com.soebes.shared.cli.invoker.InvocationRequest.SVNCommands;

public class SubversionCommandLineBuilderTest {

	private List<File> toDelete = new ArrayList<File>();

	private Properties sysProps;

	@Test(expectedExceptions = { IllegalStateException.class })
	public void testShouldFailIfLoggerSetToNull() throws IOException {
		logTestStart();

		TestCommandLineBuilder tclb = new TestCommandLineBuilder();
		tclb.setLogger(null);
		tclb.checkRequiredState();

	}

	@Test
	public void testSubversionCommand() throws Exception {
		SubversionCommandLineBuilder commandLineBuilder = new SubversionCommandLineBuilder();
		File subversionExecutable = new File("svn");
		commandLineBuilder.setSubversionExecutable(subversionExecutable);
		File executable = commandLineBuilder.findSVNExecutable();
		System.out.println("Exe:" + executable.getAbsolutePath());
		assertThat(executable.exists()).isTrue();
		assertThat(executable.isAbsolute()).isTrue();
	}

	@Test
	public void testSubversionCommandVersion() throws CommandLineConfigurationException {
		SubversionCommandLineBuilder commandLineBuilder = new SubversionCommandLineBuilder();

		DefaultInvocationRequest request = new DefaultInvocationRequest();
		request.setCommand(SVNCommands.none);
		request.setShowVersion(true);
//		request.getParameters().add("file:///");

		Commandline cli = commandLineBuilder.build(request);

		System.out.println("CLI: " + Joiner.on(" " ).join(cli.getCommandline()));
	}

	public void setUp() {
		sysProps = System.getProperties();

		Properties p = new Properties(sysProps);

		System.setProperties(p);
	}

	public void tearDown() throws IOException {
		System.setProperties(sysProps);

		for (File file : toDelete) {
			if (file.exists()) {
				if (file.isDirectory()) {
					FileUtils.deleteDirectory(file);
				} else {
					file.delete();
				}
			}
		}
	}

	// this is just a debugging helper for separating unit test output...
	private void logTestStart() {
		NullPointerException npe = new NullPointerException();
		StackTraceElement element = npe.getStackTrace()[1];

		System.out.println("Starting: " + element.getMethodName());
	}

	private void assertArgumentsPresentInOrder(Commandline cli,
			String... expected) {
		assertArgumentsPresentInOrder(cli, Arrays.asList(expected));
	}

	private void assertArgumentsPresentInOrder(Commandline cli,
			List<String> expected) {
		String[] arguments = cli.getArguments();

		int expectedCounter = 0;

		for (int i = 0; i < arguments.length; i++) {
			if (arguments[i].equals(expected.get(expectedCounter))) {
				expectedCounter++;
			}
		}


//		assertEquals(
//				"Arguments: " + expected
//						+ " were not found or are in the wrong order: "
//						+ Arrays.asList(arguments), expected.size(),
//				expectedCounter);
	}

	private void assertArgumentsPresent(Commandline cli,
			Set<String> requiredArgs) {
		String[] argv = cli.getArguments();
		List<String> args = Arrays.asList(argv);

		for (String arg : requiredArgs) {
//			assertTrue("Command-line argument: \'" + arg + "\' is missing in "
//					+ args, args.contains(arg));
		}
	}

	private void assertArgumentsNotPresent(Commandline cli,
			Set<String> bannedArgs) {
		String[] argv = cli.getArguments();
		List<String> args = Arrays.asList(argv);

		for (String arg : bannedArgs) {
//			assertFalse("Command-line argument: \'" + arg
//					+ "\' should not be present.", args.contains(arg));
		}
	}

	private File createDummyFile(File directory, String filename)
			throws IOException {
		File dummyFile = new File(directory, filename);

		FileWriter writer = null;
		try {
			writer = new FileWriter(dummyFile);
			writer.write("This is a dummy file.");
		} finally {
			IOUtil.close(writer);
		}

		toDelete.add(dummyFile);

		return dummyFile;
	}

	private static final class TestCommandLineBuilder extends
			SubversionCommandLineBuilder {
		public void checkRequiredState() throws IOException {
			super.checkRequiredState();
		}

		public File findSVNExecutable()
				throws CommandLineConfigurationException {
			return super.findSVNExecutable();
		}

		public void setFlags(InvocationRequest request, Commandline cli) {
			super.setFlags(request, cli);
		}

	}

	private File getTempDir() throws Exception {
		return new File(System.getProperty("java.io.tmpdir"))
				.getCanonicalFile();
	}

	private InvocationRequest newRequest() {
		return new DefaultInvocationRequest();
	}

}
