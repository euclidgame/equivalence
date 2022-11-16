package equivalence.judgement.program;

import java.io.File;
import java.io.IOException;

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
            builder.command("g++", path, "-o", executable).start().waitFor();
        }
        catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
