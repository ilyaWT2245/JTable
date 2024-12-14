package ru.vsu.cs.course1;

import ru.vsu.cs.utils.ArrayUtils;

import java.util.Arrays;
import java.util.List;

public class Task {
    public static int[] solve(int[] arr) {
        List<Integer> list = Arrays.asList(ArrayUtils.toObject(arr));
        process(list);
        arr = ArrayUtils.toPrimitive(list.toArray(new Integer[0]));
        return arr;
    }

    public static void process(List<Integer> list) {
        int left = firstIndexOf(list, findMax(list));
        int right = lastIndexOf(list, findMin(list));
        if (left != -1 && right != -1) {
            for (int i = left + 1; i <= (left + right) / 2; i++) {
                Integer temp = list.get(i);
                list.set(i, list.get(left + right - i));
                list.set(left + right - i, temp);
            }
        }
    }

    private static int findMax(List<Integer> list) {
        int a = Integer.MIN_VALUE;
        for (Integer i : list) {
            if (i > a) {
                a = i;
            }
        }

        return a;
    }

    private static int findMin(List<Integer> list) {
        int a = Integer.MAX_VALUE;
        for (Integer i : list) {
            if (i < a) {
                a = i;
            }
        }

        return a;
    }

    private static int firstIndexOf(List<Integer> list, int value) {
        int index = 0;
        for (Integer i : list) {
            if (i.equals(value)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    private static int lastIndexOf(List<Integer> list, int value) {
        int index = 0;
        int last = -1;
        for (Integer i : list) {
            if (i.equals(value)) {
                last = index;
            }
            index++;
        }
        return last;
    }
}
