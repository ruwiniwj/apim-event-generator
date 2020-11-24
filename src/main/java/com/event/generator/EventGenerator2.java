package com.event.generator;

import com.event.dto.EventDTO;
import com.event.dto.EventDTO2;
import com.event.dto.EventDTO3;
import com.event.dto.TagsDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

public class EventGenerator2 {

    public static void main(String[] args) {
        Properties prop = new Properties();

        try (InputStream input = RequestEventGenerator.class.getClassLoader()
                .getResourceAsStream("config2.properties")) {
            // load a properties file
            prop.load(input);
            String[] apis = prop.getProperty("apis").split(";");
            String[] username = prop.getProperty("username").split(",");
            String nodeID = prop.getProperty("nodeID");
            String regionalID = prop.getProperty("regionalID");
            int tps = Integer.parseInt(prop.getProperty("tps"));
            int noOfSecs = Integer.parseInt(prop.getProperty("noOfSecs"));
            long startTimestamp = Long.parseLong(prop.getProperty("startTimestamp"));
            String[] tenants = prop.getProperty("tenants").split(",");
            String fileName = prop.getProperty("fileName");
            String fileType = prop.getProperty("fileType");
            generateEvents(apis, username, nodeID, regionalID, tps, noOfSecs, startTimestamp, tenants,
                    fileName, fileType);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateEvents(String[] apis, String[] username,
                                      String nodeID, String regionalID, int tps, int noOfSecs,
                                      long startTimestamp, String[] tenants, String fileName, String fileType)
            throws IOException {
        long startTime = startTimestamp;
        Random rn = new Random();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName + "." + fileType))) {
            for (int sec = 0; sec < noOfSecs; sec++) {
                for (int count = 0; count < tps; count++) {
                    EventDTO3 eventDTO;
                    TagsDTO tagsDTO = new TagsDTO();
                    JSONObject api = new JSONObject();
                    JSONArray apiResources;
                    String[] apiResourceDetails;
                    JSONArray apps;
                    String[] appDetails;

                    long value = 0L;
                    try {
                        api = new JSONObject(apis[count % 10].replace("{{tenant}}",
                                tenants[(int)(count / 2.5)]));
                        apiResources = api.getJSONArray("resources");
                        apiResourceDetails = apiResources.getString(count / 10).split(":");
                        apps = api.getJSONArray("apps");
                        appDetails = apps.getString((int) (count / 16.6)).split(":");
                        String usernameTenantDomain = appDetails[2].split("@")[1];
                        value = rn.nextInt(100 + 1);
                        eventDTO = new EventDTO3(startTime, api.getString("apiName"), api.getString("apiVersion"),
                                nodeID, value, api.getString("apiUUID"),
                                api.getString("apiContext"), api.getString("apiCreator"),
                                api.getString("apiCreatorTenantDomain"), regionalID, appDetails[0], appDetails[1],
                                appDetails[2],
                                api.getString("gatewayType"), api.getString("label"), apiResourceDetails[0],
                                apiResourceDetails[1],
                                username[rn.nextInt((username.length - 1) + 1)] + "@" + usernameTenantDomain,
                                usernameTenantDomain, api.getString("destination"));
                        writeToFile(eventDTO, writer, fileType);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                startTime += 1000;
            }
        }
    }

    private static void writeToFile(EventDTO3 eventDTO, BufferedWriter writer, String fileType) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
//        EventDTO3 eventDTO3 = new EventDTO3(eventDTO);
        String jsonString = objectMapper.writeValueAsString(eventDTO);
        if (fileType.equals("json")) {
            writer.write(jsonString + ",\n");
        } else if (fileType.equals("csv")) {
            JSONObject jsonObject = new JSONObject(jsonString);
            Set<String> keys = jsonObject.keySet();
            ArrayList<String> valueList = new ArrayList<>();
            keys.forEach(key -> {
                String valueStr = jsonObject.get(key).toString();
                if (valueStr.contains(",")) {
                    valueStr = "\"" + valueStr.replace("\"", "\"\"") + "\"";
                }
                valueList.add(valueStr);
            });
//                String tags = valueList.remove(valueList.size()-1);
//                valueList.add(tags.substring(1,tags.length()-1));
            writer.write(String.join(",", valueList) + "\n");
        }
    }
}
