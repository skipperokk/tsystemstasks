package com.tsystems.javaschool.tasks.pyramid;

import java.util.Arrays;
import java.util.List;

public class PyramidBuilder {

    /**
     * Builds a pyramid with sorted values (with minimum value at the top line and maximum at the bottom,
     * from left to right). All vacant positions in the array are zeros.
     *
     * @param inputNumbers to be used in the pyramid
     * @return 2d array with pyramid inside
     * @throws {@link CannotBuildPyramidException} if the pyramid cannot be build with given input
     */
    public int[][] buildPyramid(List<Integer> inputNumbers) {
        try {
            int weight = 0;
            int height = 0;
            int count = 0;
            int arrSize = inputNumbers.size();

            int[] arrayFromList = new int[arrSize];

            int[] sortedArray = getSortedArray(arrayFromList, inputNumbers);

            for (int i = 1; i < arrayFromList.length; i++) {
                if (arrSize > 0) {
                    arrSize = arrSize - i;
                    weight = 2 * i - 1;
                    height++;
                } else if (arrSize != 0) {
                    throw new CannotBuildPyramidException();
                }
            }

            int[][] pyramid = new int[height][weight];
            int halfWeight = weight / 2;

            for (int i = 0; i < height; i++) {
                for (int j = halfWeight - i; j < halfWeight + i + 1; j = j + 2) {
                    pyramid[i][j] = sortedArray[count++];
                }
            }
            return pyramid;
        } catch (Error | Exception e) {
            throw new CannotBuildPyramidException();
        }
    }

    private int[] getSortedArray(int[] array, List<Integer> list) {
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
        Arrays.sort(array);
        return array;
    }
}
