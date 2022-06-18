/*
 * Copyright (c) 2022 by MILOSZ GILGA <https://miloszgilga.pl>
 *
 * File name: CRC.java
 * Last modified: 18/06/2022, 23:20
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

public class CRC extends Helpers {

    private static final byte _crcGenerator = 0x31; // CRC polynomial generator
    private static int _crcData; // CRC checksum

    private static void crcAlgorithm() {
        // array of bytes with user input values
        byte[] convertedValues = hexStringToByteArray();
        // byte array incremented by 0x00
        byte[] inputStream = new byte[convertedValues.length + 1];
        for(int i = 0; i < convertedValues.length; i++) {
            inputStream[i] = convertedValues[i];
        }
        inputStream[convertedValues.length] = 0x00;
        // go through all the bytes entered by the user
        for(byte value : inputStream) {
            // go through all the bits in the iterated byte
            for(int i = 7; i >= 0; i--) {
                // check if the most significiant bit (MSB) has been set
                if ((_crcData & 0x80) != 0) {
                    _crcData <<= 1; // shift the value one bit to the left
                    // if 1 then set LSB bit to 1, if 0 then set the youngest bit to 0
                    _crcData = ((value & (1 << i)) != 0) ? (_crcData | 0x01) : (_crcData & 0xFE);
                    _crcData ^= _crcGenerator; // wykonaj operację XOR na wartości crc i generatorze
                } else {
                    // if MSB has not been set, only shift the bit to the left
                    _crcData <<= 1;
                    _crcData = ((value & (1 << i)) != 0) ? (_crcData | 0x01) : (_crcData & 0xFE);
                }
            }
        }
        System.out.println("Obliczony CRC (HEX):");
        // show the calculated CRC in HEX format
        System.out.printf("%02X %n", _crcData);
    }

    public static void main(String[] args) {
        insertData();
        crcAlgorithm();
    }
}