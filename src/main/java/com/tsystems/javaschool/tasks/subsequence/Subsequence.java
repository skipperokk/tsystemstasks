package com.tsystems.javaschool.tasks.subsequence;

import java.util.List;
import java.util.regex.Pattern;

public class Subsequence {

    /**
     * Checks if it is possible to get a sequence which is equal to the first
     * one by removing some elements from the second one.
     *
     * @param x first sequence
     * @param y second sequence
     * @return <code>true</code> if possible, otherwise <code>false</code>
     */
    @SuppressWarnings("rawtypes")
    public boolean find(List x, List y) {
        if (x == null || y == null) {
            throw new IllegalArgumentException();
        }

        if (x.isEmpty()) {
            return true;
        } else if (y.isEmpty()) {
            return false;
        }

        if (x.size() > y.size()) {
            return false;
        }

        for (int i = 0, j = 0; i < y.size(); i++) {
            if (y.get(i).equals(x.get(j))) {
                j++;
                if (j == x.size()) {
                    return true;
                }
            }
        }
        return false;
    }
}
