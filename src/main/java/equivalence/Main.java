package equivalence;

import equivalence.judgement.core.ProgramClassifier;
import equivalence.judgement.program.Program;
import equivalence.judgement.util.ResultHolder;

import java.io.File;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        File workDir = new File(args[0]);
        for (String sub: Objects.requireNonNull(workDir.list())) {
            File subDir = new File(workDir, sub);
            if (subDir.isDirectory()) {
                ProgramClassifier classifier = new ProgramClassifier(subDir);
                classifier.classify();
                ResultHolder<Program> result = classifier.getResult();
            }
        }
    }
}