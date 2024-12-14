package ru.vsu.cs.course1;

import ru.vsu.cs.utils.ArrayUtils;

import java.util.HashSet;
import java.util.List;

public class Task {
    public static int[][] solve(int[][] arr2) {
        arr2 = deleteRows(arr2);
        arr2 = changeRowsColumns(arr2);
        arr2 = deleteRows(arr2);
        arr2 = changeRowsColumns(arr2);
        return arr2;
    }
    private static int[][] deleteRow(int[][] arr2, int index) {
        int[][] otherArr = new int[arr2.length - 1][];
        int a = 0;
        for (int i = 0; i < arr2.length; i++) {
            if (i == index) {
                a++;
                continue;
            }
            otherArr[i - a] = arr2[i];
        }
        return otherArr;
    }

    private static int[][] changeRowsColumns(int[][] arr2) {
        int[][] otherArr = new int[arr2[0].length][arr2.length];
        for (int i = 0; i < arr2.length; i++) {
            for (int j = 0; j < arr2[i].length; j++) {
                otherArr[j][i] = arr2[i][j];
            }
        }
        return otherArr;
    }

    private static int[][] deleteRows(int[][] arr2) {
        int i = 0;
        while (i < arr2.length) {
            int[] line = arr2[i];
            HashSet<Integer> elements = new HashSet<>(List.of(ArrayUtils.toObject(line)));
            if (elements.size() == 1 && line[0] == 0) {
                arr2 = deleteRow(arr2, i);
                continue;
            }
            i++;
        }
        return arr2;
    }
}
