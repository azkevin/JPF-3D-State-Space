package org.lwjglb.engine;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class Utils {

    public static String loadResource(String fileName) throws Exception {
        String result;
        try (//InputStream in = Class.forName(Utils.class.getName()).getResourceAsStream(fileName);
        	InputStream in = new BufferedInputStream(new FileInputStream(new File("src/resources/" + fileName)));
            Scanner scanner = new Scanner(in, "UTF-8")) {
            result = scanner.useDelimiter("\\A").next();
        }
        return result;
    }
}