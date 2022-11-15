package equivalence.judgement.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ResultHolder<T> {

    private final List<Element<T>> elements = new ArrayList<>();

    private final List<Element<T>> representatives = new ArrayList<>();

    public ResultHolder() {}

    public void addRepresentative(T representative) {
        Element<T> newElement = new Element<>(representative, representative);
        elements.add(newElement);
        representatives.add(newElement);
    }

    public void addElement(T element, T representative) {
        Element<T> newElement = new Element<>(element, representative);
        elements.add(newElement);
    }

    public Stream<T> getRepresentatives() {
        return representatives.stream().map(Element::getElement);
    }

}
