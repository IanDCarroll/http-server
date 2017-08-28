package com.ian;

public class LineCook {
    public static byte[] marinateBytes(byte[][] inputs) {
        final int firstIndex = 0;
        int runningIndex = 0;
        byte[] marinade = new byte[getRightSizedPot(inputs)];
        for (byte[] input : inputs) {
            System.arraycopy(input, firstIndex, marinade, runningIndex, input.length);
            runningIndex += input.length;
        }
        return marinade;
    }

    public static int getRightSizedPot(byte[][] inputs) {
        int potSize = 0;
        for (byte[] input : inputs) {
            potSize += input.length;
        }
        return potSize;
    }
}
