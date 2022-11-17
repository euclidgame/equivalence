package equivalence.judgement.program;

import equivalence.judgement.type.Type;

import java.io.File;
import java.util.List;

public abstract class SimpleProgram implements Program {
    protected final File program;

    protected String executable;

    protected static final ProcessBuilder builder = new ProcessBuilder();

    public SimpleProgram(File file) {
        program = file;
    }

    @Override
    public String relativePath() {
        String path = program.getAbsolutePath();
        String[] dirs = path.split("/");
        int tot = dirs.length;
        return dirs[tot - 3] + "/" + dirs[tot - 2] + "/" + dirs[tot - 1];
    }

    @Override
    public String absolutePath() {
        return program.getAbsolutePath();
    }

    @Override
    public List<Type> inputTypes() {
        return null;
    }

    @Override
    public String getExecutable() {
        return executable;
    }
}
