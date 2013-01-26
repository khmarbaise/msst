package com.soebes.msst.cli;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

/**
 * <code>msst branch 1.2</code>
 * <code>msst branch --branch-version 2.3-SNAPSHOT 1.2</code>
 *
 * @author Karl Heinz Marbaise
 */
@Parameters(commandDescription = "Create a branch.", separators = "=")
public class BranchCommand extends BaseCommand implements ICommand {

    @Parameter(
        names = {"--source", "-S"},
        description = "The source branch to create the branch from."
    )
    private String sourceBranch;


    @Parameter(
	        names = {"--branch-version" },
	        description = "The branch version."
	    )
    private String branchVersion;
}
