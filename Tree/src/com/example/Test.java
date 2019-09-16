package com.example;

public class Test {

    public static void main(String[] args) {
        String a = "abcdefg";
        String b = "abcdeabcdeffabcdefg";
        int result = findPosition(a, b);
        System.out.println("result " + result);
    }

    private static int findPosition(String a, String b) {
        int index = -1;
        int aLength = a.length();
        int bLength = b.length();
        int step = 0;

        while(index < bLength - aLength){
            for (int j = 0; j < aLength; j++) {
                char aChar = a.charAt(j);
                char bChar = b.charAt(j);
                step++;
                if (aChar == bChar) {
                    if (step == aLength) {
                        return index ;
                    }
                } else {
                    break;
                }
            }
            index += step;
            step = 0;
        }

        return index;
    }

}
