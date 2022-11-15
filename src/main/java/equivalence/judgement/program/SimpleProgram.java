package equivalence.judgement.program;

import equivalence.judgement.type.Type;

import java.util.List;

public class SimpleProgram implements Program {
    private final String path;

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
    public String compile() {
        return null;
    }
}
