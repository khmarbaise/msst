package com.soebes.msst.cli;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

/**
 * <code>msst rebase 1.2</code>
 *
 * @author Karl Heinz Marbaise
 */
@Parameters(commandDescription = "Rebase a branch onto an other.", separators = "=")
public class RebaseCommand extends BaseCommand implements ICommand {

    @Parameter(
        names = {"--destination", "-D"},
        description = "The destination branch"
    )
    private String destinationBranch;

    
}
