/*
 * Copyright (c) 2022 by MILOSZ GILGA <https://miloszgilga.pl>
 *
 * File name: LRC.java
 * Last modified: 18/06/2022, 23:01
 * Project name: crc-check-sum
 *
 * Licensed under the GNU GPL 3.0 license; you may not use this file except in compliance with the License.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * THE ABOVE COPYRIGHT NOTICE AND THIS PERMISSION NOTICE SHALL BE INCLUDED IN ALL
 * COPIES OR SUBSTANTIAL PORTIONS OF THE SOFTWARE.
 */

public class LRC extends Helpers {

    private static byte _lrlData; // HEX value of the calculated LRC checksum

    private static void lrcAlgorithm() {
        byte[] allHexValues = hexStringToByteArray();
        // go through all the hex values and add them together
        for (byte allHexValue : allHexValues) {
            _lrlData += allHexValue;
        }
        // subtract the value 256 (in HEX it is 0x100)
        _lrlData = (byte)(0x100 - _lrlData);
        System.out.println("Obliczona ramka (HEX):");
        // show the calculated frame in HEX format
        System.out.printf("%02X %n", _lrlData);
    }

    public static void main(String[] args) {
        insertData();
        lrcAlgorithm();
    }
}