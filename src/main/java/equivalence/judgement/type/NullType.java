package equivalence.judgement.type;

public class NullType implements Type {
    @Override
    public String typeName() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public String randomValue() {
        return "";
    }
}
