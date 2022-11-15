package equivalence.judgement.type;

public class IntType implements Type {

    private final int maxLength;

    private final int minLength;

    public IntType(int max, int min) {
        maxLength = max;
        minLength = min;
    }

    @Override
    public String typeName() {
        return "int";
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public String randomValue() {
        return null;
    }
}
