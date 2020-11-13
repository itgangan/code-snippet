/**
 * Copyright (c) 2020,TravelSky.
 * All Rights Reserved.
 * TravelSky CONFIDENTIAL
 */

package com.algorithm;

/**
 * The type of code-snippet.
 *
 * <p>
 * .
 *
 * @author ganxiangyong
 */
public class FindClosetPoint {

    /**
     * 我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
     * <p>
     * <p>
     * （这里，平面上两点之间的距离是欧几里德距离。）
     * <p>
     * <p>
     * 你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * 输入：points = [[1,3],[-2,2]], K = 1
     * 输出：[[-2,2]]
     * 解释：
     * (1, 3) 和原点之间的距离为 sqrt(10)，
     * (-2, 2) 和原点之间的距离为 sqrt(8)，
     * 由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
     * 我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
     * <p>
     * 示例 2：
     * <p>
     * <p>
     * 输入：points = [[3,3],[5,-1],[-2,4]], K = 2
     * 输出：[[3,3],[-2,4]]
     * （答案 [[-2,4],[3,3]] 也会被接受。）
     * <p>
     * <p>
     * <p>
     * <p>
     * 提示：
     * <p>
     * <p>
     * <p>
     * 1 <= K <= points.length <= 10000
     * <p>
     * -10000 < points[i][0] < 10000
     * <p>
     * -10000 < points[i][1] < 10000
     *
     * @param points
     * @param k
     * @return
     */

    public static void main(String[] args) {
        int[][] points = {{68, 97}, {34, -84}, {60, 100}, {2, 31}, {-27, -38}, {-73, -74}, {-55, -39}, {62, 91}, {62, 92}, {-57, -67}};
        int[][] expect = {{-57, -67}, {34, -84}, {-55, -39}, {2, 31}, {-27, -38}};

        FindClosetPoint c = new FindClosetPoint();
        int[][] result = c.kClosest(points, 5);

        c.validation(result, expect);
    }


    // 方案一：for循环解
    public int[][] kClosest(int[][] points, int k) {
        // 当前最新的k个最小的边长
        int[] temporaryMinLength = new int[k];
        int[][] result = new int[k][2];
        for (int i = 0; i < points.length; i++) {
            int x = points[i][0];
            int y = points[i][1];
            // 勾股定理（应该再开方，但是因为只比较大小，所以可以不再开方计算）
            int r = x * x + y * y;
            int resultArrayIndex = fillToTemporaryResult(temporaryMinLength, r);
            if (resultArrayIndex > -1) {
                result[resultArrayIndex][0] = x;
                result[resultArrayIndex][1] = y;
            }
        }

        return result;
    }

    private int fillToTemporaryResult(int[] temporaryLength, int r) {
        int tempMaxIndex = -1;
        int tempMaxValue = 0;

        for (int i = 0; i < temporaryLength.length; i++) {
            if (temporaryLength[i] == 0) {
                temporaryLength[i] = r;
                return i;
            }
            if (temporaryLength[i] > r && temporaryLength[i] > tempMaxValue) {
                tempMaxValue = temporaryLength[i];
                tempMaxIndex = i;
            }
        }

        if (tempMaxIndex > -1) {
            temporaryLength[tempMaxIndex] = r;
        }
        return tempMaxIndex;

    }

    // 用于测试结果正确性
    private void validation(int[][] result, int[][] expectd) {
        if (result.length == expectd.length) {
            for (int i = 0; i < result.length; i++) {
                if (!contains(expectd, result[i])) {
                    throw new RuntimeException("结果错误");
                }
            }
        }
    }

    private boolean contains(int[][] expect, int[] result) {
        for (int i = 0; i < expect.length; i++) {
            boolean x = (expect[i][0] == result[0]);
            boolean y = (expect[i][1] == result[1]);
            if (x && y) {
                return true;
            }

        }
        return false;
    }


}
