package equivalence;

import equivalence.judgement.core.ProgramClassifier;
import equivalence.judgement.program.Program;
import equivalence.judgement.util.ResultDumper;
import equivalence.judgement.util.ResultHolder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        File workDir = new File(args[0]);
        File outputDir = new File("output");
        String[] head = new String[]{"file1", "file2"};
        List<String[]> equalResult = new ArrayList<>();
        List<String[]> inequalResult = new ArrayList<>();
        equalResult.add(head);
        inequalResult.add(head);
        if (!outputDir.exists()) {
            outputDir.mkdir();
        }
        File tempDir = new File("tmp");
        if (!tempDir.exists()) {
            tempDir.mkdir();
        }
        for (String sub: Objects.requireNonNull(workDir.list())) {
            File subDir = new File(workDir, sub);
            if (subDir.isDirectory()) {
                ProgramClassifier classifier = new ProgramClassifier(subDir, outputDir, tempDir);
                classifier.classify();
                ResultHolder<Program> result = classifier.getResult();
                processResult(result, equalResult, inequalResult);
            }
        }
        try {
            ResultDumper.dump("output/equal.csv", equalResult);
            ResultDumper.dump("output/inequal.csv", inequalResult);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void processResult(ResultHolder<Program> result, List<String[]> equal, List<String[]> inequal) {
        result.elements().forEach(p -> {
            Program rep = result.getRepresentativeOf(p);
            int order = result.getOrderOf(p);
            result.elements().forEach(p1 -> {
                if (result.getOrderOf(p1) > order) {
                    Program rep1 = result.getRepresentativeOf(p1);
                    String[] temp = new String[]{rep.relativePath(), rep1.relativePath()};
                    if (rep.equals(rep1)) {
                        equal.add(temp);
                    }
                    else {
                        inequal.add(temp);
                    }
                }
            });
        });
    }

}