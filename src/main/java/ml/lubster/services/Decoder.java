package ml.lubster.services;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Decoder {

    /**
     * Decodes the given string use the given encoding.
     *
     * @param input the given string to decode.
     * @param encoding The given encoding.
     * @return Decoded string from the given string.
     */
    public static String decodeText(String input, String encoding) {
        try {
            return
                    new BufferedReader(
                            new InputStreamReader(
                                    new ByteArrayInputStream(input.getBytes()),
                                    Charset.forName(encoding)))
                            .readLine();
        } catch (IOException e) {
            System.err.printf("Exception was occurred while decoding '%s' (%s)\n", input, encoding);
        }
        return input;
    }
}
