package equivalence.judgement.program;

import java.io.File;

public class CppProgram extends SimpleProgram {
    public CppProgram(File file) {
        super(file);
    }

    @Override
    public void compile() {
        String path = program.getAbsolutePath();
        executable = path.replace(".cpp", ".out");
        builder.command("g++", path);
    }
}
