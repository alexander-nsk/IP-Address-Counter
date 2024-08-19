package com.ecwid;

import java.net.UnknownHostException;

/**
 * An interface for counting unique IP addresses.
 */
public interface IPAddressCounter {
    /**
     * Adds an IP address to the counter.
     *
     * @param addr the IP address in string format
     * @throws UnknownHostException if the IP address is invalid
     */
    void addAddress(String addr) throws UnknownHostException;

    /**
     * Retrieves the count of unique IP addresses added.
     *
     * @return the number of unique IP addresses
     */
    long getUniqueAddrCount();
}

