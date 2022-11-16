package equivalence.judgement.program;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.util.concurrent.TimeUnit.SECONDS;

public class CProgram extends SimpleProgram {

    public CProgram(File file) {
        super(file);
    }

    @Override
    public void compile() {
        try {
            String path = program.getAbsolutePath();
            String[] dirs = path.split("/");
            String fileName = dirs[dirs.length - 1];
            executable = "./tmp/" + fileName.replace(".c", ".out");
            Process p = builder.command("gcc", path, "-o", executable).start();
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
