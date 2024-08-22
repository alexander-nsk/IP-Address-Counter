import com.ecwid.IPAddressCounter;
import com.ecwid.Main;
import com.ecwid.counter.ArrayIPAddressCounter;
import com.ecwid.counter.MurmurSetIPAddressCounter;
import com.ecwid.counter.SetIPAddressCounter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicLong;


public class AppTest {
    private static final String IP_FILE_PATH = ".\\src\\test\\resources\\ip.txt";

    @Test
    void mainTest() {
        Main.main(new String[]{IP_FILE_PATH, "m"});
    }

    @Test
    void testDifferentIPAddressCounters() {
        IPAddressCounter setIPAddressCounter = new SetIPAddressCounter();
        testCounter(setIPAddressCounter);

        IPAddressCounter arrayIPAddressCounter = new ArrayIPAddressCounter();
        testCounter(arrayIPAddressCounter);

        IPAddressCounter murmurSetIPAddressCounter = new MurmurSetIPAddressCounter();
        testCounter(murmurSetIPAddressCounter);

        long expectedAddrCount = setIPAddressCounter.getUniqueAddrCount();
        Assertions.assertEquals(expectedAddrCount, arrayIPAddressCounter.getUniqueAddrCount());
        Assertions.assertEquals(expectedAddrCount, murmurSetIPAddressCounter.getUniqueAddrCount());
    }

    private static void testCounter(IPAddressCounter addressCounter) {
        long startTime = System.nanoTime();
        AtomicLong totalLines = new AtomicLong();

        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();

        Path path = Paths.get(IP_FILE_PATH);

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            reader.lines().forEach(s -> {
                try {
                    addressCounter.addAddress(s);
                    totalLines.getAndIncrement();
                } catch (UnknownHostException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        runtime.gc();
        long usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
        long memoryUsedByMethod = usedMemoryAfter - usedMemoryBefore;

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;

        System.out.println(addressCounter.getClass().getSimpleName());
        System.out.println("Unique ip addresses count is: " + addressCounter.getUniqueAddrCount());
        System.out.println("Total lines: " + totalLines);
        System.out.println("Time millis: " + duration);
        System.out.println("Memory used bytes: " + memoryUsedByMethod);
        System.out.println();
    }
}
