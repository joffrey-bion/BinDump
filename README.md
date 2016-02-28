# BinDump
[![Bintray](https://img.shields.io/bintray/v/joffrey-bion/applications/bindump.svg)](https://bintray.com/joffrey-bion/applications/bindump/_latestVersion)
[![Travis Build](https://img.shields.io/travis/joffrey-bion/bindump/master.svg)](https://travis-ci.org/joffrey-bion/bindump)
[![Dependency Status](https://www.versioneye.com/user/projects/56d2f560157a69140da0019e/badge.svg)](https://www.versioneye.com/user/projects/56d2f560157a69140da0019e)
[![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)](https://github.com/joffrey-bion/bindump/blob/master/LICENSE)

A binary file dumper. This is a simple program to display the bits of a binary file. Especially useful to debug encoding programs.

## Usage

You can run the program in 3 different ways, as it is a java program:
```
java org.hildan.tools.bindump.BinDump <filename> [nbBytesPerLine]
```

Using the provided runnable JAR file:
```
java -jar bindump-<version>.jar <filename> [nbBytesPerLine]
```

Using the provided Windows executable file:
```
bindump-<version>.exe <filename> [nbBytesPerLine]
```

## Example

Here is an example of a short UTF-8 text file named `testfile.txt`:
```
abcdefg
```

Here is how to dump it with BinDump (no option):
```
bindump-<version>.exe testfile.txt
```

Output:
```
Reading in binary file named : testfile.txt
File size: 10
11101111
10111011
10111111
01100001
01100010
01100011
01100100
01100101
01100110
01100111
Num bytes read: 10
```

### Bytes per line option

If 1 byte per line produces too many lines, you may add the number of bytes per line as a second argument:
```
bindump-<version>.exe testfile.txt 2
```

Output:
```
Reading in binary file named : testfile.txt
File size: 10
1110111110111011
1011111101100001
0110001001100011
0110010001100101
0110011001100111
Num bytes read: 10
```

## License

Code released under [the MIT license](https://github.com/joffrey-bion/bindump/blob/master/LICENSE)