package elte.datatype;

import java.util.*;

public class MapBag<T> extends Bag<T> {
    private Map<T, Integer> container;

    public MapBag() {
        super();
        container = new HashMap<>();
    }

    @Override
    boolean isEmpty() {
        return container.isEmpty();
    }

    @Override
    int size() {
//        int count = 0;
//        for (int occurence : container.values()) {
//            count += occurence;
//        }

        return container.values().stream().mapToInt(Integer::intValue)
                .sum();
    }

    @Override
    int count() {
        return container.keySet().size();
    }

    @Override
    boolean contains(T item) {
        //1
//        for (T key : container.keySet()) {
//
//            if (key.equals(item)) {
//                return true;
//            }
//        }
//        return false;

        //2
//         container.keySet().contains(item);
        return container.containsKey(item);
    }

    @Override
    int getMultiplicity(T item) {
        return container.get(item);
    }

    @Override
    void add(T item) {
        // 1.
//        for (T key : container.keySet()) {
//            if (key.equals(item)) {
//                container.put(key, container.get(key) + 1);
//                return;
//            }
//        }
//        container.put(item, 1);

        //2.
//        container.putIfAbsent(item, 0);
//        container.put(item, container.get(item) + 1);
        this.add(item, 1);
    }

    @Override
    void add(T item, int times) {
        container.put(item, container.getOrDefault(item, 0) + times);
    }

    @Override
    void remove(T item) {
//        int occurences = container.getOrDefault(item, 0);
//        if (occurences == 1) {
//            container.remove(item);
//        } else {
//            container.put(item, container.get(item) - 1);
//        }

        this.remove(item, 1);
    }

    @Override
    void remove(T item, int times) {
        int occurences = container.getOrDefault(item, 0) - times;
        if (occurences < 1) {
            container.remove(item);
        } else {
            container.put(item, container.get(item) - times);
        }
    }

    @Override
    void removeAll(T item) {
        container.remove(item);
    }

    @Override
    Set<T> getItems() {
        return container.keySet();
    }
}
