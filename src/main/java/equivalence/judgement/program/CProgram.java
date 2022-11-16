package equivalence.judgement.program;

import java.io.File;

public class CProgram extends SimpleProgram {

    public CProgram(File file) {
        super(file);
    }

    @Override
    public void compile() {
        String path = program.getAbsolutePath();
        String[] dirs = path.split("/");
        String fileName = dirs[dirs.length - 1];
        executable = "/tmp/" + fileName.replace(".c", ".out");
        builder.command("gcc", path, "-o", executable);
    }

}
