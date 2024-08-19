### Documentation of Unique IP Address Counting Test Results

#### Overview:
The testing was conducted for three algorithms to count unique IP addresses using a file **ip.txt** with a size of **90 MB**. Each algorithm was tested on a dataset containing 6 million lines. The following metrics were recorded during the tests:

- **Execution time** (in milliseconds)
- **Memory usage** (in bytes)
- **Number of unique IP addresses**

#### Test Results:

1. **SetIPAddressCounter**
    - **Unique IP addresses count**: 999868
    - **Execution time**: 1659 ms
    - **Memory usage**: 58,533,712 bytes

2. **ArrayIPAddressCounter**
    - **Unique IP addresses count**: 999868
    - **Execution time**: 2567 ms
    - **Memory usage**: 332,578,832 bytes

3. **MurmurSetIPAddressCounter**
    - **Unique IP addresses count**: 999868
    - **Execution time**: 1072 ms
    - **Memory usage**: 14,689,008 bytes