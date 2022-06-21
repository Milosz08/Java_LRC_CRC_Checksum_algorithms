/*
 * Copyright (c) 2022 by MILOSZ GILGA <https://miloszgilga.pl>
 *
 * File name: Helpers.java
 * Last modified: 18/06/2022, 23:02
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

import java.util.Scanner;

public class Helpers {

    // possible characters in HEX format
    private final static String HEX_CHARS = "0123456789ABCDEF";
    // method for entering data from the console
    private final static Scanner _scanner = new Scanner(System.in);

    private static String _allHexChars; // HEX string entered by the user in sout method

    // method for entering sample frames
    protected static void insertData() {
        boolean ifError; // variable identifying a frame insertion error
        do {
            ifError = false; // reset error
            System.out.println("Wprowadź ciąg znaków w kodzie szesnastkowym (HEX):");
            _allHexChars = _scanner.nextLine(); // insert single line of characters
            // go through all the characters entered
            for(int i = 0; i < _allHexChars.length(); i++) {
                // if the character is not one of the HEX components, set an error and clear the buffer
                if (!HEX_CHARS.toLowerCase().contains(_allHexChars.charAt(i) + "".toLowerCase())) {
                    ifError = true;
                    _allHexChars = "";
                    break;
                }
            }
            // if an odd number of characters is given, add zero before the last character
            if (_allHexChars.length() % 2 != 0) {
                String buffered = _allHexChars.substring(0, _allHexChars.length() - 1) + "0";
                _allHexChars = buffered + _allHexChars.charAt(_allHexChars.length() - 1);
            }
        } while (ifError);
    }

    // method that converts a string to a number in HEX format
    protected static byte[] hexStringToByteArray() {
        // convert tuple to byte array
        byte[] data = new byte[_allHexChars.length() / 2];
        // pass through consecutive binary HEX values and assign the swapped HEX byte value
        for (int i = 0; i < _allHexChars.length(); i += 2) {
            data[i / 2] = (byte) ((Character.digit(_allHexChars.charAt(i), 16) << 4) +
                    Character.digit(_allHexChars.charAt(i + 1), 16));
        }
        return data;
    }
}