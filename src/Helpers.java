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

    // możliwe znaki w formacie HEX
    private final static String HEX_CHARS = "0123456789ABCDEF";
    // metoda do wprowadzania danych z konsoli
    private final static Scanner _scanner = new Scanner(System.in);

    private static String _allHexChars; // ciąg znaków HEX wprowadzonych przez użytkownika

    // metoda do wprowadzania przykładowych ramek
    protected static void insertData() {
        boolean ifError; // zmienna identyfikująca błąd wprowadzania ramki
        do {
            ifError = false; // resetuj błąd
            System.out.println("Wprowadź ciąg znaków w kodzie szesnastkowym (HEX):");
            _allHexChars = _scanner.nextLine(); // wczytaj linię znaków
            // przejdź przez wszystkie wprowadzone znaki
            for(int i = 0; i < _allHexChars.length(); i++) {
                // jeśli znak nie jest jednym ze składowych HEX, ustaw błąd oraz wyczyść bufor
                if (!HEX_CHARS.toLowerCase().contains(_allHexChars.charAt(i) + "".toLowerCase())) {
                    ifError = true;
                    _allHexChars = "";
                    break;
                }
            }
            // jeśli podano nieparzystą ilość znaków, dodaj zero przed ostatnim znakiem
            if (_allHexChars.length() % 2 != 0) {
                String buffered = _allHexChars.substring(0, _allHexChars.length() - 1) + "0";
                _allHexChars = buffered + _allHexChars.charAt(_allHexChars.length() - 1);
            }
        } while (ifError);
    }

    // metoda zamieniająca ciąg znaków na liczbę w formacie HEX
    protected static byte[] hexStringToByteArray() {
        // skonwertuj tuple na tablicę bajtów
        byte[] data = new byte[_allHexChars.length() / 2];
        // przejdź przez kolejne dwójki wartości HEX i przypisz zamienioną wartość bajtową typu HEX
        for (int i = 0; i < _allHexChars.length(); i += 2) {
            data[i / 2] = (byte) ((Character.digit(_allHexChars.charAt(i), 16) << 4) +
                    Character.digit(_allHexChars.charAt(i + 1), 16));
        }
        return data;
    }
}