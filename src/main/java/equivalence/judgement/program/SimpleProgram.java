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
        return program.getPath();
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
