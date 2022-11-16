package equivalence.judgement.program;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

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
            p.waitFor(2, SECONDS);
            int x = p.exitValue();
            if (x != 0) {
                executable = null;
            }
        }
        catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
