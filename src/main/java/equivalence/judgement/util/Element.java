package equivalence.judgement.util;

public class Element<T> {

    private final T element;

    private T representative;

    public Element(T element, T representative) {
        this.element = element;
        this.representative = representative;
    }

    public T getElement() {
        return element;
    }

    public void setRepresentative(T representative) {
        this.representative = representative;
    }

}
