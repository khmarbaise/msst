package com.soebes.msst.cli;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

/**
 * <code>msst synchronize 1.2</code>
 *
 * @author Karl Heinz Marbaise
 */
@Parameters(commandDescription = "Synchronize a branch with an other.", separators = "=")
public class SynchronizeCommand extends BaseCommand implements ICommand {

    @Parameter(
        names = {"--destination", "-D"},
        description = "The destination branch"
    )
    private String destinationBranch;

    
}
