package com.demo;

/**
 * The type of code-snippet.
 *
 * <p>
 * .
 *
 * @author ganxiangyong
 */
public class FinallyReturnDemo {
    public static void main(String[] args) {
        System.out.println(aa());
    }

    public static int aa(){
        int a =10;
        try{
            return a+5;
        }finally {
            return a + 20;
        }
    }

}
