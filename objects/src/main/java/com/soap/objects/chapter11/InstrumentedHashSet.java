package com.soap.objects.chapter11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

//Set(HashSet)의 인스턴스를 내부에 포함
//HashSet이 제공하는 퍼블릭 인터페이스를 그대로 제공해야함
//다행히 HashSet의 오퍼레이션들은 Set에 정의가 모두 되어있어서 Set을 실체화
public class InstrumentedHashSet<E> implements Set<E> {

    private int addCount = 0;
    private Set<E> set;

    public InstrumentedHashSet(Set<E> set) {
        this.set = set;
    }


    public int getAddCount() {
        return addCount;
    }


    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return set.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return set.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

}