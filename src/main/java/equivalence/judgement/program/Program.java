package equivalence.judgement.program;

import equivalence.judgement.type.Type;

import java.util.List;

public interface Program {

    String relativePath();

    String absolutePath();

    List<Type> inputTypes();

    String compile();
}
