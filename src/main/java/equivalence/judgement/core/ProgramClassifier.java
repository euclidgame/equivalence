package equivalence.judgement.core;

import equivalence.judgement.program.Program;
import equivalence.judgement.program.SimpleProgram;
import equivalence.judgement.type.Type;
import equivalence.judgement.type.TypeExtractor;
import equivalence.judgement.util.ResultHolder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProgramClassifier {

    private final String workDir;

    private List<Type> inputTypes;

    private List<Program> programs;

    private final ResultHolder<Program> result = new ResultHolder<>();

    public ProgramClassifier(String dir) {
        workDir = dir;
        initialize();
    }

    private void initialize() {
        // get inputTypes
        inputTypes = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(workDir + "/stdin_format.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] types = line.split(" ");
                for (String type: types) {
                    inputTypes.add(TypeExtractor.extract(type));
                }
            }
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException("Unfounded Dir: " + workDir);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // get programs
        File dirFile = new File(workDir);
        programs = new ArrayList<>();
        for (String file: Objects.requireNonNull(dirFile.list())) {
            if (file.endsWith(".cpp")) {
                programs.add(new SimpleProgram(workDir + "/" + file));
            }
        }
    }

    public void classify() {
        for (Program program: programs) {
            boolean classified = false;

        }
    }

}
