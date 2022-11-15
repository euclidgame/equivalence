package equivalence.judgement.type;

public class StringType implements Type {

    private final int maxLength;

    private final int minLength;

    public StringType(int max, int min) {
        maxLength = max;
        minLength = min;
    }

    @Override
    public String typeName() {
        return "string";
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
