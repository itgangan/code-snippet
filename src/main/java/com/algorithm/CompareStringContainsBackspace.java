package com.algorithm;

import org.apache.commons.lang3.Validate;

/**
 * The type of code-snippet.
 *
 * <p>
 * .
 *
 * @author ganxiangyong
 */
public class CompareStringContainsBackspace {
    /**
     * 844. 比较含退格的字符串
     * 给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
     * <p>
     * 注意：如果对空文本输入退格字符，文本继续为空。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：S = "ab#c", T = "ad#c"
     * 输出：true
     * 解释：S 和 T 都会变成 “ac”。
     * 示例 2：
     * <p>
     * 输入：S = "ab##", T = "c#d#"
     * 输出：true
     * 解释：S 和 T 都会变成 “”。
     * 示例 3：
     * <p>
     * 输入：S = "a##c", T = "#a#c"
     * 输出：true
     * 解释：S 和 T 都会变成 “c”。
     * 示例 4：
     * <p>
     * 输入：S = "a#c", T = "b"
     * 输出：false
     * 解释：S 会变成 “c”，但 T 仍然是 “b”。
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= S.length <= 200
     * 1 <= T.length <= 200
     * S 和 T 只含有小写字母以及字符 '#'。
     * <p>
     * <p>
     * 进阶：
     * <p>
     * 你可以用 O(N) 的时间复杂度和 O(1) 的空间复杂度解决该问题吗？
     */

    public static void main(String[] args) {
        String s = "ab#c";
        String t = "ad#c";
        Validate.isTrue(backspaceCompare(s, t));

        s = "ab##";
        t = "c#d#";
        Validate.isTrue(backspaceCompare(s, t));

        s = "a##c";
        t = "#a#c";
        Validate.isTrue(backspaceCompare(s, t));

        s = "a#c";
        t = "b";
        Validate.isTrue(!backspaceCompare(s, t));

        s = "ab#c##";
        t = "";
        Validate.isTrue(backspaceCompare(s, t));

        s = "abcd#ef####";
        t = "a";
        Validate.isTrue(backspaceCompare(s, t));
    }

    /**
     * 分别从S,T的尾部依次往前找一个有效的字符（没有被删的），然后比较是否相等，直到比完所有。时间O(N),空间O(1)
     * @param s
     * @param t
     * @return
     */
    public static boolean backspaceCompare(String s, String t) {
        int spos = s.length() - 1;
        int tpos = t.length() - 1;

        while (spos > -1 || tpos > -1) {
            int sindex = getOne(s, spos);
            int tindex = getOne(t, tpos);
            // s遍历完之后（找不到有效字符了），那么t也必须遍历完，没有有效字符，反之亦然
            if ((sindex == -1 || tindex == -1)) {
                return sindex == tindex;
            }
            // 比较s与t的有效字符是否相等
            if (s.charAt(sindex) != t.charAt(tindex)) {
                return false;
            }
            // 改变s/t的当前指针位置，下一次循环，接着往前找一个有效字符
            spos = sindex - 1;
            tpos = tindex - 1;
        }

        return true;

    }

    // 返回最后一个有效的字符位置，无则返回-1
    private static int getOne(String s, int pos) {
        int asciiCount = 0;
        int backspaceCount = 0;
        for (int i = pos; i >= 0; i--) {
            if (s.charAt(i) == '#') {
                backspaceCount++;
            } else {
                asciiCount++;
            }
            if (asciiCount > backspaceCount) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 常规方法，把#号删了，形成一个新的字符串，再比较是否相等，需要用额外的空间，时间：O(M+N),空间O(M+N)
     * @param s
     * @param t
     * @return
     */
    public static boolean backspaceCompare2(String s, String t) {
        String sc = getString(s);
        String tc = getString(t);
        return sc.equalsIgnoreCase(tc);
    }

    private static String getString(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != '#') {
                sb.append(c);
            } else {
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }
        return sb.toString();
    }


}


