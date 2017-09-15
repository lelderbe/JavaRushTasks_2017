package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/* 
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {

    static class Entry<T> implements Serializable {
        String elementName;
        int lineNumber;
        boolean availableToAddLeftChildren;
        boolean availableToAddRightChildren;
        Entry<T> parent;
        Entry<T> leftChild;
        Entry<T> rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        void checkChildren() {
            availableToAddLeftChildren = leftChild == null ? true : false;
            availableToAddRightChildren = rightChild == null ? true : false;
        }

        boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren || availableToAddRightChildren;
        }


//        void add(String s) {
//            if (availableToAddLeftChildren) {
//                Entry<T> entry = new Entry<>(s);
//                entry.parent = this;
//                leftChild = entry;
//            } else if (availableToAddRightChildren) {
//                rightChild = new Entry<>(s);
//            } else {
//                leftChild.add(s);
//            }
//
//            checkChildren();
//        }

        int size() {
//            return (availableToAddLeftChildren ? 0 : leftChild.size() + 1) + (availableToAddRightChildren ? 0 : rightChild.size() + 1);
            return (leftChild == null ? 0 : leftChild.size() + 1) + (rightChild == null ? 0 : rightChild.size() + 1);
        }

    }


    Entry<String> root = new Entry<>("0");
    static int lines = 0;
    static int[] levels = new int[100];
    static {
        levels[0] = 1;
    }


    public static void main(String[] args) {
        List<String> list = new CustomTree();
        for (int i = 1; i < 16; i++) {
            list.add(String.valueOf(i));
        }
        System.out.println(list.size());

        ((CustomTree) list).print();
//        list.remove("3");
//        System.out.println(list.size());

//        list.remove("2");
//        System.out.println(list.size());

//        list.remove("4");
//        System.out.println(list.size());

//        ((CustomTree) list).print();
//        list.add("16");
//        ((CustomTree) list).print();


//        System.out.println("Expected 3, actual is " + ((CustomTree) list).getParent("8"));
        list.remove("5");
//        System.out.println("Expected null, actual is " + ((CustomTree) list).getParent("11"));
//        System.out.println(list.size());
        ((CustomTree) list).print();

    }

    void print() {
        LinkedList<Entry<String>> queue = new LinkedList<>();

        queue.add(root);

        int elementsPerRow = 0;

        for (int i = 0; i < levels.length; i++) {
            if (levels[i] == 0) {
                elementsPerRow = ((int) Math.round(Math.pow(2, i))) - 1; break;
            }
        }
        System.out.println("elements = " + elementsPerRow);


        Entry<String> entry = null;
        int currentLevel = 0;

        while (!queue.isEmpty()) {
            entry = queue.poll();
            if (entry.lineNumber > currentLevel) {
                currentLevel = entry.lineNumber;
                System.out.println();
            }

            double step = elementsPerRow * 3 / (Math.pow(2, entry.lineNumber) + 1);

            String format = "%" + (int) step + "s";
            System.out.printf(format, entry.elementName);

//            for (int i = 0; i < (int) step; i++) {
//                System.out.print(" ");
//            }
//
//            System.out.print(entry.elementName);

//            System.out.printf("%s <-- %s\n", entry.elementName, entry.parent == null ? null : entry.parent.elementName);

//            if (!entry.availableToAddLeftChildren) {
            if (entry.leftChild != null) {
                queue.add(entry.leftChild);
            }

//            if (!entry.availableToAddRightChildren) {
            if (entry.rightChild != null) {
                queue.add(entry.rightChild);
            }
        }

    }

    @Override
    public boolean remove(Object o) {
        remove((String) o);
        return true;
    }

    void remove(String s) {
        LinkedList<Entry<String>> queue = new LinkedList<>();

        queue.add(root);

        Entry<String> entry = null;
        Entry<String> parent = null;

        while (!queue.isEmpty()) {
            entry = queue.poll();

            if (entry.elementName.equals(s)) {
                parent = entry.parent;
                if (parent.leftChild == entry) {
                    parent.leftChild = null;
                } else if (parent.rightChild == entry) {
                    parent.rightChild = null;
                }
//                parent.checkChildren();
                break;
            }

            if (!entry.availableToAddLeftChildren) {
                queue.add(entry.leftChild);
            }

            if (!entry.availableToAddRightChildren) {
                queue.add(entry.rightChild);
            }
        }
    }

    String getParent(String s) {
        LinkedList<Entry<String>> queue = new LinkedList<>();

        queue.add(root);

        Entry<String> entry = null;
        Entry<String> result = null;

        while (!queue.isEmpty()) {
            entry = queue.poll();

            if (entry.elementName.equals(s)) {
                result = entry.parent;
                break;
            }

            if (!entry.availableToAddLeftChildren) {
                queue.add(entry.leftChild);
            }

            if (!entry.availableToAddRightChildren) {
                queue.add(entry.rightChild);
            }

        }

        return result == null ? null : result.elementName;
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return root.size();
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }


    @Override
    public void add(int index, String element) {
        LinkedList<Entry<String>> queue = new LinkedList<>();

        queue.add(root);

        Entry<String> entry = null;
        Entry<String> newElement = new Entry<>(element);

        while (!queue.isEmpty()) {
            entry = queue.poll();
            if (entry.availableToAddLeftChildren) {
                // add to the left child
                entry.leftChild = newElement;
                newElement.parent = entry;
                newElement.lineNumber = entry.lineNumber + 1;
                levels[newElement.lineNumber]++;
//                if (lines < newElement.lineNumber) {
//                    lines = newElement.lineNumber;
//                }
                entry.checkChildren();
                break;
            } else {
                if (entry.leftChild != null) { queue.add(entry.leftChild); }
            }

            if (entry.availableToAddRightChildren) {
                // add to the right child
                entry.rightChild = newElement;
                newElement.parent = entry;
                newElement.lineNumber = entry.lineNumber + 1;
                levels[newElement.lineNumber]++;
//                if (lines < newElement.lineNumber) {
//                    lines = newElement.lineNumber;
//                }
                entry.checkChildren();
                break;
            } else {
                if (entry.rightChild != null) { queue.add(entry.rightChild); }
            }
        }

    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }
}
