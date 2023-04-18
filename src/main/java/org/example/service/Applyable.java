package org.example.service;

@FunctionalInterface
public interface Applyable<T> {
    T apply();
}
