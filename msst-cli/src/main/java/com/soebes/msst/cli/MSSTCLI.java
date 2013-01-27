package com.soebes.msst.cli;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * This will define the command line of MSST.
 *
 * @author Karl Heinz Marbaise
 *
 */
public class MSSTCLI {
    private static Logger LOGGER = LogManager.getLogger(MSSTCLI.class.getName());

    private int returnCode = 0;

    public MSSTCLI() {
	LOGGER.info("Test message.");
    }

    public void run(String[] args) {
        setReturnCode(0);
    }

    /**
     * This will do the command argument extraction and give the parameter to
     * the scanRepository class which will do the real repository scan.
     *
     * @param scanCommand
     *            The command line.
     * @throws SVNException
     */
    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public int getReturnCode() {
        return returnCode;
    }

    
    public static void main(String [] args) {
	Injector injector = Guice.createInjector(new CLIModule());
	MSSTCLI cli = injector.getInstance(MSSTCLI.class);
        cli.run(args);
        System.exit(cli.getReturnCode());
    }
}
