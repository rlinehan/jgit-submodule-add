package jgittest;

import junit.framework.TestCase;
import org.eclipse.jgit.api.*;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;

public class AppTest
    extends TestCase
{
    /**
     * Test adding a submodule to parent repo when the submodule's repository
     * is within the parent repo's working tree.
     */
    public void testApp() throws GitAPIException {

        InitCommand initCommand = new InitCommand();
        initCommand.setDirectory(new File("/tmp/foo"));
        Git git = initCommand.call();

        InitCommand initCommand2 = new InitCommand();
        initCommand2.setDirectory(new File("/tmp/foo/submodules/a"));
        Git git2 = initCommand2.call();

        git2.add().addFilepattern(".").call();
        git2.commit().setMessage("First commit in submodule a").call();

        SubmoduleAddCommand addCommand = git.submoduleAdd();
        addCommand.setURI("./submodules/a");
        addCommand.setPath("submodules/a");
        addCommand.call();

    }
}
