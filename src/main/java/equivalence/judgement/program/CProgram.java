package equivalence.judgement.program;

import java.io.File;

public class CProgram extends SimpleProgram {

    public CProgram(File file) {
        super(file);
    }

    @Override
    public void compile() {
        String path = program.getAbsolutePath();
        executable = path.replace(".c", ".out");
        builder.command("gcc", path);
    }

}
