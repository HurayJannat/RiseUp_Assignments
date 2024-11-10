package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {

    public void scrollVertically(int pixels, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, arguments[0]);", pixels);
    }


    public static void saveUserInfo(String filePath, JSONObject jsonObject) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = new JSONArray();

        // Try to read the existing file; if it doesn't exist or is empty, initialize an empty array
        try (FileReader fileReader = new FileReader(filePath)) {
            jsonArray = (JSONArray) jsonParser.parse(fileReader);
        } catch (FileNotFoundException | ParseException e) {
            // File does not exist or is empty, start with a new JSON array
        }

        // Add the new JSON object
        jsonArray.add(jsonObject);

        // Write back to the file
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(jsonArray.toJSONString());
            fileWriter.flush();
        }

    }




}