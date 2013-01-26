/**
 */
package com.soebes.msst.cli;

import org.testng.annotations.Test;

/**
 * @author Karl Heinz Marbaise
 * 
 */
public class MSSTCLITest {

    @Test
    public void firstTest() throws Exception {
	MSSTCLI cli = new MSSTCLI();
	cli.run(new String[] {"A"});
    }

}
