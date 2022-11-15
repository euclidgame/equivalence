package equivalence.judgement.type;

public class TypeExtractor {

    private static final Type nullType = new NullType();

    private static final Type charType = new CharType();

    public static Type extract(String type) {
        if (type.startsWith("char")) {
            return charType;
        }
        else if (type.startsWith("int")) {
            String range = type.substring(4, type.length() - 1);
            String[] nums = range.split(",");
            return new IntType(Integer.parseInt(nums[1]), Integer.parseInt(nums[0]));
        }
        else if (type.startsWith("string")) {
            String range = type.substring(7, type.length() - 1);
            String[] nums = range.split(",");
            return new StringType(Integer.parseInt(nums[1]), Integer.parseInt(nums[0]));
        }
        return nullType;
    }
}
