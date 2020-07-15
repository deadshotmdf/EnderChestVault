package com.deadshotmdf.EnderChestVault.Utils;

public class Pair<T, L> {
    private T key;
    private L value;

    public Pair(T key, L value) {
        this.key = key;
        this.value = value;
    }

    public T getKey() {
        return key;
    }

    public L getValue() {
        return value;
    }
}
