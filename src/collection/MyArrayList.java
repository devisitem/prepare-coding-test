package collection;


import java.util.*;

public class MyArrayList<E>  implements List<E> {

    int size;
    private E [] array;

    public MyArrayList() {
        array = (E []) new Object[10];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<E> iterator() {
        E[] copy = Arrays.copyOf(array, size);

        return Arrays.asList(copy).iterator();
    }

    @Override
    public E [] toArray() {
        return Arrays.copyOf(array, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(E element) {
        if(size >=  array.length) {
            E [] bigger = (E []) new Object[array.length * 2];
            System.arraycopy(array, 0, bigger, 0, array.length);
            array = bigger;
        }
        array[size] = element;
        size++;
        return true;
    }



    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if(index == -1) {
            return false;
        }
        remove(index);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for(Object element : c) {
            if(!contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean flag = true;
        for(E element : c) {
            boolean result = add(element);
            boolean temp = flag;
            flag &= result;
            System.out.printf("%b &= %b : %b", temp, result, flag);
        }
        return flag;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean flag = true;
        for(Object obj : c) {
            flag  &= remove(obj);
        }
        return flag;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public E get(int index) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        return array[index];
    }

    @Override
    public E set(int index, E element) {
        E old = get(index);
        array[index] = element;
        return old;
    }

    @Override
    public void add(int index, E element) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        add(element);

        for(int i = size -1;i > index; i--) {
            array[i] = array[i -1];
            System.out.printf("at add(i, e) : ", Arrays.toString(array));
        }
        array[index] = element;
    }

    @Override
    public E remove(int index) {
        E element = get(index);
        for(int i = 0;i < size -1;i++) {
            array[i] = array[i + 1];
        }
        size--;
        return element;
    }

    @Override
    public int indexOf(Object target) {
        for(int i = 0;i < size;i++) {
            if(equals(target, array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for(int i = size -1;i >= 0;i--) {
            if(equals(o, array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        E [] copy = Arrays.copyOf(array, size);

        return Arrays.asList(copy).listIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        E [] copy = Arrays.copyOf(array, size);
        return Arrays.asList(copy).listIterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        if(fromIndex < 0|| toIndex >= size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        }
        E [] copy = Arrays.copyOfRange(array, fromIndex, toIndex);
        return Arrays.asList(copy);
    }

    private boolean equals(Object target, Object element) {
        if(target == null) {
            return element == null;
        } else {
            return target.equals(element);
        }

    }

}
