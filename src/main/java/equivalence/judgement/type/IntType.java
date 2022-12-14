package equivalence.judgement.type;

import java.util.Random;

public class IntType implements Type {

    private final int maxLength;

    private final int minLength;

    private static final Random random1 = new Random();

    public IntType(int max, int min) {
        maxLength = max;
        minLength = min;
        // min<=x<=max
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
        int len = random1.nextInt(maxLength - minLength + 1) + minLength;
        return Integer.toString(len);
    }
}
