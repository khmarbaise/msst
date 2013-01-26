package com.soebes.msst.cli;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

/**
 * <code>msst reintegrate 1.2</code>
 *
 * @author Karl Heinz Marbaise
 */
@Parameters(commandDescription = "Reintegrate a branch into an other.", separators = "=")
public class ReintegrateCommand extends BaseCommand implements ICommand {

    @Parameter(
        names = {"--destination", "-D"},
        description = "The destination branch"
    )
    private String destinationBranch;

    
}
