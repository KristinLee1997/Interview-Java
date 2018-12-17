package com.kristin.design_pattern.strategy;

public class SubStrategy implements Strategy {
    @Override
    public int calculate(int a, int b) {
        return a - b;
    }
}
