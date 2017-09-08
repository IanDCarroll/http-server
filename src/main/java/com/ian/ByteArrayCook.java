package com.ian;

public class ByteArrayCook {
    public static byte[] concatenateByteArrays(byte[][] inputs) {
        final int firstIndexOfInput = 0;
        int appropriateIndexOfConcatenade = 0;
        byte[] concatinade = new byte[getRightSizedPot(inputs)];
        for (byte[] input : inputs) {
            System.arraycopy(input, firstIndexOfInput, concatinade, appropriateIndexOfConcatenade, input.length);
            appropriateIndexOfConcatenade += input.length;
        }
        return concatinade;
    }

    public static int getRightSizedPot(byte[][] inputs) {
        int potSize = 0;
        for (byte[] input : inputs) {
            potSize += input.length;
        }
        return potSize;
    }
}
