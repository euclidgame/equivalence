package equivalence.judgement.type;

import java.util.Random;

public class CharType implements Type {

    private static final String repo = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final Random random1 = new Random();
    @Override
    public String typeName() {
        return "char";
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public String randomValue() {
        int index = random1.nextInt(repo.length());
        StringBuffer sb = new StringBuffer();
        sb.append(repo.charAt(index));
        return sb.toString();
    }
}
