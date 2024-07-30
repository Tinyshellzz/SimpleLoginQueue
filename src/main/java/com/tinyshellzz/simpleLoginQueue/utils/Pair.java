package com.tinyshellzz.simpleLoginQueue.utils;

public class Pair<K, V> {

    private K first;
    private V second;

    public static <K, V> Pair<K, V> createPair(K first, V second) {
        return new Pair<K, V>(first, second);
    }

    public Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }

    public K getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }

    public void setSecond(V v){
        this.second = v;
    }

    public void setFirst(K v){
        this.first = v;
    }
}
