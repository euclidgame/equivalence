package equivalence.judgement.type;

import java.util.Random;

public class IntType implements Type {

    private final int maxLength;

    private final int minLength;

    private static final String repo = "0123456789";

    private static final Random random1 = new Random();

    public IntType(int max, int min) {
        maxLength = max;
        minLength = min;
        // min<=length(x)<=max
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
        // len is a random number, the length of the generated
        StringBuffer sb = new StringBuffer();
        if (len > 0) {
            int index = random1.nextInt(repo.length() - 1) + 1;
            sb.append(repo.charAt(index));
            for (int i = 0; i < len - 1; i ++) {
                index = random1.nextInt(repo.length());
                sb.append(repo.charAt(index));
            }
        }
        return sb.toString();
    }
}
