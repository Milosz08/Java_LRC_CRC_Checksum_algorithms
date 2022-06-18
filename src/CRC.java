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

    private static final byte _crcGenerator = 0x31; // mnożnik CRC (polynomial generator)
    private static int _crcData; // suma kontrolna CRC

    private static void crcAlgorithm() {
        // tablica bajtów z wartościami wprowadzonymi przez użytkownika
        byte[] convertedValues = hexStringToByteArray();
        // tablica bajtów powiększona o wartość 0x00
        byte[] inputStream = new byte[convertedValues.length + 1];
        for(int i = 0; i < convertedValues.length; i++) {
            inputStream[i] = convertedValues[i];
        }
        inputStream[convertedValues.length] = 0x00;
        // przejdź przez wszystkie bajty wprowadzone przez użytkownika
        for(byte value : inputStream) {
            // przejdź przez wszystkie bity w iterowanym bajcie
            for(int i = 7; i >= 0; i--) {
                // sprawdź, czy został ustawiony najstarszy bit (MSB)
                if ((_crcData & 0x80) != 0) {
                    _crcData <<= 1; // przesuń wartość o jeden bit w lewo
                    // jeśli 1 to ustaw najmłodszy bit na 1, jeśli 0 to ustaw najmłodszy bit na 0
                    _crcData = ((value & (1 << i)) != 0) ? (_crcData | 0x01) : (_crcData & 0xFE);
                    _crcData ^= _crcGenerator; // wykonaj operację XOR na wartości crc i generatorze
                } else {
                    // jeśli najstarszy bit nie został ustawiony, wykonaj tylko przesunięcie bitu w lewo
                    _crcData <<= 1;
                    _crcData = ((value & (1 << i)) != 0) ? (_crcData | 0x01) : (_crcData & 0xFE);
                }
            }
        }
        System.out.println("Obliczony CRC (HEX):");
        // pokaż obliczony CRC w formacie HEX
        System.out.printf("%02X %n", _crcData);
    }

    public static void main(String[] args) {
        insertData();
        crcAlgorithm();
    }
}