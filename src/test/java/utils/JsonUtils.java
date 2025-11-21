package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    /**
     * Read test data from JSON file
     * @param filePath Path to JSON file
     * @return 2D Object array for DataProvider
     */
    public static Object[][] getTestData(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        List<Object[]> dataList = new ArrayList<>();
        
        try {
            JsonNode rootNode = mapper.readTree(new File(filePath));
            JsonNode customersNode = rootNode.get("customers");
            
            if (customersNode != null && customersNode.isArray()) {
                for (JsonNode customerNode : customersNode) {
                    Object[] row = new Object[12];
                    row[0] = customerNode.get("company").asText("");
                    row[1] = customerNode.get("vatNumber").asText("");
                    row[2] = customerNode.get("phone").asText("");
                    row[3] = customerNode.get("website").asText("");
                    row[4] = customerNode.get("group").asText("");
                    row[5] = customerNode.get("address").asText("");
                    row[6] = customerNode.get("city").asText("");
                    row[7] = customerNode.get("state").asText("");
                    row[8] = customerNode.get("zipCode").asText("");
                    row[9] = customerNode.get("currency").asText("");
                    row[10] = customerNode.get("language").asText("");
                    row[11] = customerNode.get("country").asText("");
                    
                    dataList.add(row);
                }
            }
            
            LogUtils.info("Successfully read " + dataList.size() + " customers from JSON: " + filePath);
            
        } catch (IOException e) {
            LogUtils.error("Error reading JSON file: " + e.getMessage());
            e.printStackTrace();
            return new Object[0][0];
        }
        
        return dataList.toArray(new Object[0][]);
    }
}
