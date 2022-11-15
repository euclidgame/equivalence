package equivalence.judgement.type;

import java.util.Random;

public class StringType implements Type {

    private final int maxLength;

    private final int minLength;

    private static final String repo = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final Random random1 = new Random();

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
        int len = random1.nextInt(maxLength - minLength + 1) + minLength;
        // len is a random number, the length of the generated
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i ++) {
            int index = random1.nextInt(repo.length());
            sb.append(repo.charAt(index));
        }
        return sb.toString();
    }
}
