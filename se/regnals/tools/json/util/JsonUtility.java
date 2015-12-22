package se.regnals.tools.json.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.MessageFormat;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author slanger
 */
public class JsonUtility {

    public static JSONArray getJsonArrayFromFile(String filepath) throws Exception {
        String json = getJsonStringFromFile(filepath);
        JSONArray jsona = new JSONArray(json);
        return jsona;
    }

    public static JSONObject getJsonObjectFromFile(String filepath) throws Exception {
        String json = getJsonStringFromFile(filepath);
        JSONObject jsono = new JSONObject(json);
        return jsono;
    }

    public static String getJsonStringFromFile(String filepath) throws Exception {
        StringBuilder json = new StringBuilder();
        File file = new File(filepath);
        if (!file.exists()) {
            throw new Exception(MessageFormat.format("File {0} not found!", filepath));
        }
        readFromFile(file, json);
        return json.toString();
    }

    private static void readFromFile(File file, java.lang.StringBuilder buffer) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        try {
            readFromStream(bis, buffer);
        } finally {
            fis.close();
        }
    }

    private static void readFromStream(BufferedInputStream bis, java.lang.StringBuilder buffer) throws IOException {
        while (bis.available() > 0) {
            buffer.append((char) bis.read());
        }
    }

}
