package com.ecwid;

import com.ecwid.counter.ArrayIPAddressCounter;
import com.ecwid.counter.MurmurSetIPAddressCounter;
import com.ecwid.counter.SetIPAddressCounter;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Two parameters should be provided!");
        }

        IPAddressCounter counter;
        if ("a".equals(args[1])) {
            counter = new ArrayIPAddressCounter();
        } else if ("s".equals(args[1])) {
            counter = new SetIPAddressCounter();
        } else if ("m".equals(args[1])) {
            counter = new MurmurSetIPAddressCounter();
        } else {
            counter = new MurmurSetIPAddressCounter();
        }

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(args[0]))) {
            reader.lines().forEach(s -> {
                try {
                    counter.addAddress(s);
                } catch (UnknownHostException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Unique ip addresses:  " + counter.getUniqueAddrCount());
    }
}