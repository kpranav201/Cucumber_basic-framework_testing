package utility;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;

public class JSONReader {
    private JSONObject jsonData;
    private List<Map<String, String>> testDataList = new ArrayList<>();
    private Iterator<Map<String, String>> dataIterator;

    public JSONReader(String credentialType) {
        initializeTestData(credentialType);
        this.dataIterator = testDataList.iterator();
    }

    private void initializeTestData(String credentialType) {
        try {
            JSONParser parser = new JSONParser();
            String filePath = "src/test/resources/testdata/login_data.json";
            jsonData = (JSONObject) parser.parse(new FileReader(filePath));

            // Get the data for the specified credential type
            Object data = jsonData.get(credentialType);
            if (data instanceof JSONArray) {
                // Handle array of objects
                JSONArray credentials = (JSONArray) data;
                for (Object credential : credentials) {
                    JSONObject testData = (JSONObject) credential;
                    Map<String, String> dataMap = new HashMap<>();

                    for (Object key : testData.keySet()) {
                        String keyStr = (String) key;
                        String value = String.valueOf(testData.get(keyStr));
                        dataMap.put(keyStr, value);
                    }
                    testDataList.add(dataMap);
                }
            } else if (data instanceof JSONObject) {
                // Handle single object
                JSONObject testData = (JSONObject) data;
                Map<String, String> dataMap = new HashMap<>();

                for (Object key : testData.keySet()) {
                    String keyStr = (String) key;
                    String value = String.valueOf(testData.get(keyStr));
                    dataMap.put(keyStr, value);
                }
                testDataList.add(dataMap);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize test data", e);
        }
    }

    public String get(String attribute) {
        if (!dataIterator.hasNext()) {
            return null;
        }
        return dataIterator.next().get(attribute);
    }

    public void reset() {
        this.dataIterator = testDataList.iterator();
    }

    public List<Map<String, String>> getAllTestData() {
        return new ArrayList<>(testDataList);
    }
}
