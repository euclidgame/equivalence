package equivalence.judgement.type;

public class CharType implements Type {
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
        return null;
    }
}
