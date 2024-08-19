package com.ecwid.counter;

import com.ecwid.IPAddressCounter;
import com.ecwid.IpAddressUtils;

import java.net.UnknownHostException;

public class ArrayIPAddressCounter implements IPAddressCounter {

    private final boolean[][][][] addresses = new boolean[256][][][];
    private long uniqCounter = 0;

    @Override
    public void addAddress(String address) throws UnknownHostException {
        byte[] result = IpAddressUtils.convertToBytes(address);

        int byte1 = Byte.toUnsignedInt(result[0]);
        boolean[][][] array1 = addresses[byte1];
        if (array1 == null) {
            array1 = new boolean[256][][];
            addresses[byte1] = array1;
        }

        int byte2 = Byte.toUnsignedInt(result[1]);
        boolean[][] array2 = array1[byte2];
        if (array2 == null) {
            array2 = new boolean[256][];
            array1[byte2] = array2;
        }

        int byte3 = Byte.toUnsignedInt(result[2]);
        boolean[] array3 = array2[byte3];
        if (array3 == null) {
            array3 = new boolean[256];
            array2[byte3] = array3;
        }

        int byte4 = Byte.toUnsignedInt(result[3]);

        if (!array3[byte4]) {
            array3[byte4] = true;
            uniqCounter++;
        }

    }

    @Override
    public long getUniqueAddrCount() {
        return uniqCounter;
    }
}
