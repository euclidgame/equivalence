package equivalence.judgement.core;

import equivalence.judgement.program.CProgram;
import equivalence.judgement.program.CppProgram;
import equivalence.judgement.program.Program;
import equivalence.judgement.type.Type;
import equivalence.judgement.type.TypeExtractor;
import equivalence.judgement.util.ResultHolder;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.concurrent.TimeUnit.SECONDS;

public class ProgramClassifier {

    private final File workDir;

    private final File tempDir;

    private final String inputPath;

    private List<Type> inputTypes;

    private List<Program> programs;

    private final ProcessBuilder builder = new ProcessBuilder();

    private final ResultHolder<Program> result = new ResultHolder<>();

    private final File inputFile;

    public ProgramClassifier(File dir, File temp) {
        workDir = dir;
        tempDir = temp;
        inputPath = tempDir.getAbsolutePath() + "/input.txt";
        inputFile = new File(inputPath);
        initialize();
    }

    private void initialize() {
        // get inputTypes
        inputTypes = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(workDir.getAbsoluteFile()
                    + "/stdin_format.txt"));
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
        programs = new ArrayList<>();
        for (String file: Objects.requireNonNull(workDir.list())) {
            if (file.endsWith(".c")) {
                Program newProgram = new CProgram(new File(workDir, file));
                newProgram.compile();
                programs.add(newProgram);
            }
            else if (file.endsWith(".cpp")) {
                Program newProgram = new CppProgram(new File(workDir, file));
                newProgram.compile();
                programs.add(newProgram);
            }
        }
    }

    public void classify() {
        try {
            for (Program program: programs) {
                boolean classified = false;
                if (program.getExecutable() != null) {
                    System.out.println(program.absolutePath());
                    for (Program representative: result.getRepresentatives()) {
                        boolean eqFlag = true;
                        for (int i = 0; i < 100; i ++) {
                            generateInput();
                            String output1 = tempDir.getAbsolutePath() + "/output1.txt",
                                    output2 = tempDir.getAbsolutePath() + "/output2.txt";
                            builder.command(program.getExecutable());
                            builder.redirectInput(inputFile);
                            File o1 = new File(output1);
                            builder.redirectOutput(o1);
                            builder.redirectError(o1);
                            Process p1 = builder.start();
                            p1.waitFor(10, SECONDS);

                            builder.command(representative.getExecutable());
                            builder.redirectInput(inputFile);
                            File o2 = new File(output2);
                            builder.redirectOutput(o2);
                            builder.redirectError(o2);
                            Process p2 = builder.start();
                            p2.waitFor(10, SECONDS);

                            if (p1.exitValue() == p2.exitValue()) {
                                if (p1.exitValue() == 0 && !compareFiles(output1, output2)) {
                                    eqFlag = false;
                                    break;
                                }
                            }
                            else {
                                eqFlag = false;
                                break;
                            }
                        }
                        if (eqFlag) {
                            classified = true;
                            result.addElement(program, representative);
                            break;
                        }
                    }
                    if (!classified) {
                        result.addRepresentative(program);
                    }
                }

            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private void generateInput() {
        try {
            PrintStream out = new PrintStream(new FileOutputStream(inputPath));
            inputTypes.forEach(type -> out.println(type.randomValue()));
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean compareFiles(String file1, String file2) {
        try {
            BufferedInputStream fileStream1 = new BufferedInputStream(new FileInputStream(file1));
            BufferedInputStream fileStream2 = new BufferedInputStream(new FileInputStream(file2));
            if (fileStream1.available() == fileStream2.available()) {
                while (true) {
                    int c1 = fileStream1.read(), c2 = fileStream2.read();
                    if (c1 == -1 || c2 == -1) {
                        return true;
                    }
                    if (c1 != c2) {
                        return false;
                    }
                }
            }
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultHolder<Program> getResult() {
        return result;
    }

}
