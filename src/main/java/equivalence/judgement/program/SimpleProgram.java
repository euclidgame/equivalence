package equivalence.judgement.program;

import equivalence.judgement.type.Type;

import java.util.List;

public class SimpleProgram implements Program {
    private final String path;

    private String executable;

    private static final ProcessBuilder builder = new ProcessBuilder();

    public SimpleProgram(String relativePath) {
        path = relativePath;
    }

    @Override
    public String relativePath() {
        return path;
    }

    @Override
    public String absolutePath() {
        return null;
    }

    @Override
    public List<Type> inputTypes() {
        return null;
    }

    @Override
    public void compile() {
        if (path.endsWith(".c")) {
            executable = path.replace(".c", ".out");
            builder.command("gcc", path);
        }
        if (path.endsWith(".cpp")) {
            executable = path.replace(".cpp", ".out");
            builder.command("g++", path);
        }
    }

    @Override
    public String getExecutable() {
        return executable;
    }
}
