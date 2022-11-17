package equivalence.judgement.program;

import java.io.File;
import java.io.IOException;

import static java.util.concurrent.TimeUnit.SECONDS;


public class CppProgram extends SimpleProgram {
    public CppProgram(File file) {
        super(file);
    }

    @Override
    public void compile() {
        try {
            String path = program.getAbsolutePath();
            String[] dirs = path.split("/");
            String fileName = dirs[dirs.length - 1];
            executable = "./tmp/" + fileName.replace(".cpp", ".out");
            Process p = builder.command("g++", "-std=c++14", path, "-o", executable).start();
            boolean exit = p.waitFor(4, SECONDS);
            if (!exit || p.exitValue() != 0) {
                executable = null;
            }
        }
        catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
