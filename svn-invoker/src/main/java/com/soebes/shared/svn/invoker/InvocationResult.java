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

import org.codehaus.plexus.util.cli.CommandLineException;

/**
 * Describes the result of a Subversion invocation.
 * 
 * @author jdcasey
 * @version $Id: InvocationResult.java 1401842 2012-10-24 19:49:47Z rfscholte $
 */
public interface InvocationResult {

    /**
     * Gets the exception that possibly occurred during the execution of the
     * command line.
     * 
     * @return The exception that prevented to invoke Subversion or
     *         <code>null</code> if the command line was successfully processed
     *         by the operating system.
     */
    CommandLineException getExecutionException();

    /**
     * Gets the exit code from the Subversion invocation. A non-zero value
     * indicates a failure. <strong>Note:</strong> This value is undefined if
     * {@link #getExecutionException()} reports an exception.
     * 
     * @return The exit code from the Subversion invocation.
     */
    int getExitCode();

}
