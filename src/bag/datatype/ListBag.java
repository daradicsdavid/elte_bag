package bag.datatype;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListBag<T> extends Bag<T> {
    private List<T> bag;

    public ListBag() {
        List<T> bag = new ArrayList<>();
    }


    @Override
    boolean isEmpty() {
        return bag.isEmpty();
    }

    @Override
    int size() {
        return bag.size();
    }

    @Override
    int count() {
//        int count = 0;
//        List<T> seenItems = new ArrayList<>();
//        for (T item : bag) {
//            if (!seenItems.contains(item)) {
//                count++;
//                seenItems.add(item);
//            }
//        }
//        return count;
        return new HashSet<>(bag).size();
    }

    @Override
    boolean contains(T item) {
        return bag.contains(item);
    }

    @Override
    int getMultiplicity(T item) {
        int count = 0;
        for (T i : bag) {
            if (i.equals(item)) {
                count++;
            }
        }

        return count;
//        return bag.stream().filter((i) -> i.equals(item)).count();
    }

    @Override
    void add(T item) {
        bag.add(item);
    }

    @Override
    void add(T item, int times) {
        for (int i = 0; i < times; i++) {
            bag.add(item);
        }
    }

    @Override
    void remove(T item) {
        bag.remove(item);
    }

    @Override
    void remove(T item, int times) {
        for (int i = 0; i < times; i++) {
            bag.remove(item);
        }
    }

    @Override
    void removeAll(T item) {
        List<T> newBag = new ArrayList<>();
        for (T i : bag) {
            if (!i.equals(item)) {
                newBag.add(i);
            }
        }
        bag = newBag;

//        bag = bag.stream().filter(i -> !i.equals(item)).collect(Collectors.toList());

//        bag.removeIf(i -> i.equals(item));
    }

    @Override
    Set<T> getItems() {
        return new HashSet<>(bag);
    }
}
