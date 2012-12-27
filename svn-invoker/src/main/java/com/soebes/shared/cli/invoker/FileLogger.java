package com.soebes.shared.cli.invoker;

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.shared.scriptinterpreter.ExecutionLogger;

import java.io.File;
import java.io.IOException;

class FileLogger extends org.apache.maven.shared.scriptinterpreter.FileLogger
        implements InvocationOutputHandler, ExecutionLogger {

    /**
     * Creates a new logger that writes to the specified file.
     *
     * @param outputFile The path to the output file, must not be <code>null</code>.
     * @throws IOException If the output file could not be created.
     */
    public FileLogger(File outputFile) throws IOException {
        super(outputFile, null);
    }

    /**
     * Creates a new logger that writes to the specified file and optionally
     * mirrors messages to the given mojo logger.
     *
     * @param outputFile The path to the output file, must not be <code>null</code>.
     * @param log        The mojo logger to additionally output messages to, may be
     *                   <code>null</code> if not used.
     * @throws IOException If the output file could not be created.
     */
    public FileLogger(File outputFile, Log log) throws IOException {
        super(outputFile, log);
    }

}
