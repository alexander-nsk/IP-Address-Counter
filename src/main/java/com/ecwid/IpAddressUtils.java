package com.ecwid;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpAddressUtils {
    /**
     * Converts an IP address to an integer.
     *
     * @param ipAddress the IP address in string format
     * @return the integer representation of the IP address
     * @throws UnknownHostException if the IP address is invalid
     */
    public static int convertToInt(String ipAddress) throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getByName(ipAddress);

        int result = 0;
        for (byte b : inetAddress.getAddress()) {
            result = result << 8 | (b & 0xFF);
        }

        return result;
    }

    /**
     * Converts an IP address to byte array.
     *
     * @param ipAddress the IP address in string format
     * @return the byte array representation of the IP address
     * @throws UnknownHostException if the IP address is invalid
     */
    public static byte[] convertToBytes(String ipAddress) throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getByName(ipAddress);

        return inetAddress.getAddress();
    }
}
