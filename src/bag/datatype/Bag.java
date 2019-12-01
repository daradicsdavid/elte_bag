package bag.datatype;

import java.util.*;

public abstract class Bag<T> {

    abstract boolean isEmpty();

    abstract int size();

    abstract int count();

    abstract boolean contains(T item);

    abstract int getMultiplicity(T item);

    abstract void add(T item);

    abstract void add(T item, int times);

    abstract void remove(T item);

    abstract void remove(T item, int times);

    abstract void removeAll(T item);

    abstract Set<T> getItems();

    void addAll(Bag<T> other) {
        for (T item : other.getItems()) {
            add(item, other.getMultiplicity(item));
        }
    }

    boolean isSubBagOf(Bag<T> other) {
        for (T item : getItems()) {
            if (!other.contains(item) || other.getMultiplicity(item) >= getMultiplicity(item)) {
                return false;
            }
        }
        return true;
    }

    void setMultiplicity(T item, int multiplicity) {
        if (multiplicity < 0) {
            throw new IllegalArgumentException("Multiplicity must be positive or zero.");
        }
        int internalMultiplicity = getMultiplicity(item);

        if (internalMultiplicity < multiplicity) {
            add(item, multiplicity - internalMultiplicity);
        } else if (internalMultiplicity > multiplicity) {
            remove(item, internalMultiplicity - multiplicity);
        }
    }

    @Override
    public String toString() {
        Set<T> items = getItems();
        int i = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (T item : items) {
            sb.append(item);
            sb.append(" (");
            sb.append(getMultiplicity(item));
            sb.append(")");
            if (i < items.size() - 1) {
                sb.append(", ");
            }
            i++;
        }
        sb.append("]");
        return sb.toString();
    }

    public List<T> getOrderedItems() {
        List<T> items = new ArrayList<>(getItems());
//        items.sort(new Comparator<T>() {
//            @Override
//            public int compare(T i1, T i2) {
//                return new Integer(getMultiplicity(i1)).compareTo(new Integer(getMultiplicity(i2)));
//            }
//        });
//
//        items.sort(new Comparator<T>() {
//            @Override
//            public int compare(T i1, T i2) {
//                return Integer.compare(getMultiplicity(i1), getMultiplicity(i2));
//            }
//        });
//
//        items.sort((i1, i2) -> Integer.compare(getMultiplicity(i1), getMultiplicity(i2)));
        items.sort(Comparator.comparingInt(this::getMultiplicity));
        return items;
    }
}
