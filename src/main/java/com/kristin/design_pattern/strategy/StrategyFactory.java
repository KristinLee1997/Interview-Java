package com.kristin.design_pattern.strategy;

public class StrategyFactory {
    public static int strategy(int type, int a, int b) {
        switch (type) {
            case 1:
                return new Context(new AddStrategy()).calculate(a, b);
            case 2:
                return new Context(new SubStrategy()).calculate(a, b);
            default:
                return 0;
        }
    }
}
