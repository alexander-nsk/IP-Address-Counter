package com.ecwid.counter;

import com.ecwid.IPAddressCounter;
import com.ecwid.IpAddressUtils;

import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

public class MurmurSetIPAddressCounter implements IPAddressCounter {

    private final MurmurHashSet addressedMap = new MurmurHashSet();

    @Override
    public void addAddress(String address) throws UnknownHostException {

        int result = IpAddressUtils.convertToInt(address);

        addressedMap.add(result);
    }

    @Override
    public long getUniqueAddrCount() {
        return addressedMap.size();
    }
}
