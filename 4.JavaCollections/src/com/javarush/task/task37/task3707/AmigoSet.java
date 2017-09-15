package com.javarush.task.task37.task3707;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

/**
 * Created by user on 27.05.2017.
 */
public class AmigoSet<E> extends AbstractSet implements Serializable, Cloneable, Set {
    private static final Object PRESENT = new Object();
    private transient HashMap<E, Object> map;

    public AmigoSet() {
        map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        int capacity = Math.max(16, (int) ((collection.size()/.75f) + 1));
        map = new HashMap<>(capacity);

        addAll(collection);
    }

    @Override
    public boolean add(Object o) {

        return map.put((E) o, PRESENT) == null;
    }

    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.keySet().contains(o);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean remove(Object o) {
        return !(map.remove(o) == null);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        AmigoSet<E> amigoSet = new AmigoSet<>();
        try {
            amigoSet.map = (HashMap<E, Object>) map.clone();
        } catch (Exception e) {
            throw new InternalError();
        }
        return super.clone();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
//        out.writeObject(HashMapReflectionHelper.callHiddenMethod(map, "loadFactor"));
//        out.writeObject(HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
        out.writeFloat(HashMapReflectionHelper.callHiddenMethod(map, "loadFactor"));
        out.writeInt(HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
        out.writeInt(map.size());
//        out.writeObject(map);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
//        float loadFactor = (float) in.readObject();
//        int capacity = (int) in.readObject();
        float loadFactor = in.readFloat();
        int capacity = in.readInt();
        int size = in.readInt();
//        HashMap<E, Object> inMap = (HashMap<E, Object>) in.readObject();
        map = new HashMap<>(capacity, loadFactor);
//        map.putAll(inMap);
    }

}
