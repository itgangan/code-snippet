package com.algorithm;

/**
 * 有一个二维矩阵 A 其中每个元素的值为 0 或 1 。
 *
 *
 * 移动是指选择任一行或列，并转换该行或列中的每一个值：将所有 0 都更改为 1，将所有 1 都更改为 0。
 *
 *
 * 在做出任意次数的移动后，将该矩阵的每一行都按照二进制数来解释，矩阵的得分就是这些数字的总和。
 *
 *
 * 返回尽可能高的分数。
 *
 *
 *
 *
 *
 *
 *
 * 示例：
 *
 *
 * 输入：[[0,0,1,1],[1,0,1,0],[1,1,0,0]]
 * 输出：39
 * 解释：
 * 转换为 [[1,1,1,1],[1,0,0,1],[1,1,1,1]]
 * 0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
 *
 *
 *
 *
 * 提示：
 *
 *
 *
 * 1 <= A.length <= 20
 *
 * 1 <= A[0].length <= 20
 *
 * A[i][j] 是 0 或 1
 */
public class ScoreAfterFlippingMatrix {

    public int matrixScore(int[][] a) {
        // 最终结果
        int result = 0;

        // 总行数,因为题目说明了a不为null,a[0]不为null，所以不用判空，直接使用a[0].length
        int totolRowCount = a.length;
        int totolColumnCount = a[0].length;

        // 转行，将第一列全置为1
        for (int i = 0; i < a.length; i++) {
            if (a[i][0] == 0) {
                swapRow(a, i);
            }
        }
        // 当第一列全为1时，计算result的值
        result += ((1 << totolColumnCount - 1) * totolRowCount);

        // 从第二列开始，转列
        for (int i = 1; i < totolColumnCount; i++) {
            int countOfOne = 0;
            int countOfZero = 0;
            for (int[] r : a) {
                if (r[i] == 0) {
                    countOfZero++;
                } else {
                    countOfOne++;
                }
            }

            // 取出现数量多的，直接计算结果
            int max = countOfOne > countOfZero ? countOfOne : countOfZero;
            result += ((1 << totolColumnCount - (i + 1)) * max);
        }

        return result;
    }

    // 行转换
    private void swapRow(int[][] a, int index) {
        int[] r = a[index];
        for (int i = 0; i < r.length; i++) {
            r[i] = r[i] == 0 ? 1 : 0;
        }
    }
}
