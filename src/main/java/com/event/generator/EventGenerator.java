package com.event.generator;

import com.event.dto.EventDTO;
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

public class EventGenerator {

    public static void main(String[] args) {
        Properties prop = new Properties();

        try (InputStream input = RequestEventGenerator.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            // load a properties file
            prop.load(input);
            String[] apis = prop.getProperty("apis").split(";");
            String[] username = prop.getProperty("username").split(",");
            String[] location = prop.getProperty("location").split(",");
            String[] os = prop.getProperty("os").split(",");
            String[] browser = prop.getProperty("browser").split(",");
            String[] throttleReason = prop.getProperty("throttleReason").split(",");
            String nodeID = prop.getProperty("nodeID");
            String regionalID = prop.getProperty("regionalID");
            int eventCount = Integer.parseInt(prop.getProperty("eventCount"));
            long startTimestamp = Long.parseLong(prop.getProperty("startTimestamp"));
            String fileName = prop.getProperty("fileName");
            String fileType = prop.getProperty("fileType");
            String[] metricsNames = prop.getProperty("metricsNames").split(",");
            generateEvents(apis, username, location, os, browser,
                    throttleReason, nodeID, regionalID, eventCount, startTimestamp, fileName, fileType, metricsNames);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateEvents(String[] apis, String[] username, String[] location, String[] os,
                                      String[] browser,
                                      String[] throttleReason, String nodeID, String regionalID, int eventCount,
                                      long startTimestamp, String fileName, String fileType, String[] metricsNames)
            throws IOException {
        long startTime = startTimestamp;
        String matrixType = metricsNames[0];
        Random rn = new Random();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName + "." + fileType))) {
            for (int eventNo = 0; eventNo < eventCount; eventNo++) {
                EventDTO eventDTO;
                TagsDTO tagsDTO = new TagsDTO();
                JSONObject api = new JSONObject();
                JSONArray apiResources;
                String[] apiResourceDetails;
                JSONArray apps;
                String[] appDetails;
                long value = 0L;
                try {
                    matrixType = metricsNames[eventNo % metricsNames.length];
                    api = new JSONObject(apis[rn.nextInt((apis.length - 1) + 1)]);
                    apiResources = api.getJSONArray("resources");
                    apiResourceDetails = apiResources.getString(rn.nextInt((apiResources.length() - 1) + 1))
                            .split(":");
                    apps = api.getJSONArray("apps");
                    appDetails = apps.getString(rn.nextInt((apps.length() - 1) + 1)).split(":");
                    String usernameTenantDomain = appDetails[2].split("@")[1];
                    switch (matrixType) {
                        case "response count:2xx":
                            value = rn.nextInt(100 + 1);
                            tagsDTO =
                                    new TagsDTO(appDetails[0], appDetails[1], appDetails[2], "", "",
                                            apiResourceDetails[0], apiResourceDetails[1], "", "", "2xx", "", "", "", "",
                                            "", "", "", "");
                            break;
                        case "response count:4xx":
                            value = rn.nextInt(100 + 1);
                            tagsDTO =
                                    new TagsDTO(appDetails[0], appDetails[1], appDetails[2], "", "",
                                            apiResourceDetails[0], apiResourceDetails[1], "", "", "4xx", "", "", "", "",
                                            "", "", "", "");
                            break;
                        case "response count:5xx":
                            value = rn.nextInt(100 + 1);
                            tagsDTO =
                                    new TagsDTO(appDetails[0], appDetails[1], appDetails[2], "", "",
                                            apiResourceDetails[0], apiResourceDetails[1], "", "", "5xx", "", "", "", "",
                                            "", "", "", "");
                            break;
                        case "error count:throttled":
                            value = rn.nextInt(10 + 1);
                            tagsDTO = new TagsDTO(appDetails[0], appDetails[1], appDetails[2],
                                    api.getString("gatewayType"), "", apiResourceDetails[0], apiResourceDetails[1], "",
                                    "", "", "throttled", "", "", "", "",
                                    throttleReason[rn.nextInt((throttleReason.length - 1) + 1)], "", "");
                            break;
                        case "error count:faulty":
                            value = rn.nextInt(10 + 1);
                            tagsDTO =
                                    new TagsDTO(appDetails[0], appDetails[1], appDetails[2], "", "",
                                            apiResourceDetails[0], apiResourceDetails[1], "", "", "", "faulty", "", "",
                                            "", "", "", "", "");
                            break;
                        case "latency:response time":
                            value = rn.nextInt(300 + 1);
                            tagsDTO = new TagsDTO("", "", "", "", "", apiResourceDetails[0], apiResourceDetails[1], "",
                                    "", "", "", "response time", "", "", "", "", "", "");
                            break;
                        case "latency:service time":
                            value = rn.nextInt(300 + 1);
                            tagsDTO = new TagsDTO("", "", "", "", "", apiResourceDetails[0], apiResourceDetails[1], "",
                                    "", "", "", "service time", "", "", "", "", "", "");
                            break;
                        case "latency:backend time":
                            value = rn.nextInt(300 + 1);
                            tagsDTO = new TagsDTO("", "", "", "", "", apiResourceDetails[0], apiResourceDetails[1], "",
                                    "", "", "", "backend time", "", "", "", "", "", "");
                            break;
                        case "latency:security":
                            value = rn.nextInt(300 + 1);
                            tagsDTO = new TagsDTO("", "", "", "", "", apiResourceDetails[0], apiResourceDetails[1], "",
                                    "", "", "", "security", "", "", "", "", "", "");
                            break;
                        case "latency:throttling":
                            value = rn.nextInt(300 + 1);
                            tagsDTO = new TagsDTO("", "", "", "", "", apiResourceDetails[0], apiResourceDetails[1], "",
                                    "", "", "", "throttling", "", "", "", "", "", "");
                            break;
                        case "latency:request mediation":
                            value = rn.nextInt(300 + 1);
                            tagsDTO = new TagsDTO("", "", "", "", "", apiResourceDetails[0], apiResourceDetails[1], "",
                                    "", "", "", "request mediation", "", "", "", "", "", "");
                            break;
                        case "latency:response mediation":
                            value = rn.nextInt(300 + 1);
                            tagsDTO = new TagsDTO("", "", "", "", "", apiResourceDetails[0], apiResourceDetails[1], "",
                                    "", "", "", "response mediation", "", "", "", "", "", "");
                            break;
                        case "latency:backend":
                            value = rn.nextInt(300 + 1);
                            tagsDTO = new TagsDTO("", "", "", "", "", apiResourceDetails[0], apiResourceDetails[1], "",
                                    "", "", "", "backend", "", "", "", "", "", "");
                            break;
                        case "latency:other":
                            value = rn.nextInt(300 + 1);
                            tagsDTO = new TagsDTO("", "", "", "", "", apiResourceDetails[0], apiResourceDetails[1], "",
                                    "", "", "", "other", "", "", "", "", "", "");
                            break;
                        case "request count":
                            value = rn.nextInt(100 + 1);
                            tagsDTO = new TagsDTO(appDetails[0], appDetails[1], appDetails[2],
                                    api.getString("gatewayType"), api.getString("label"), apiResourceDetails[0],
                                    apiResourceDetails[1],
                                    username[rn.nextInt((username.length - 1) + 1)] + "@" + usernameTenantDomain,
                                    usernameTenantDomain, "", "", "", api.getString("destination"), "", "", "", "", "");
                            break;
                        case "geolocation count":
                            value = rn.nextInt(100 + 1);
                            String[] geoLocation = location[rn.nextInt((location.length - 1) + 1)].split(":");
                            tagsDTO = new TagsDTO("", "", "", "", "", "", "",
                                    username[rn.nextInt((username.length - 1) + 1)] + "@" + usernameTenantDomain,
                                    usernameTenantDomain, "", "", "", "", geoLocation[1], geoLocation[0], "", "", "");
                            break;
                        case "platform count":
                            value = rn.nextInt(100 + 1);
                            tagsDTO = new TagsDTO("", "", "", api.getString("gatewayType"), api.getString("label"), "",
                                    "", "", "", "", "", "", "", "", "", "", os[rn.nextInt((os.length - 1) + 1)], "");
                            break;
                        case "browser count":
                            value = rn.nextInt(100 + 1);
                            tagsDTO = new TagsDTO("", "", "", api.getString("gatewayType"), api.getString("label"), "",
                                    "", "", "", "", "", "", "", "", "", "", "",
                                    browser[rn.nextInt((browser.length - 1) + 1)]);
                            break;
                    }
                    eventDTO = new EventDTO(startTime, api.getString("apiName"), api.getString("apiVersion"),
                            nodeID, matrixType.split(":")[0], value, api.getString("apiUUID"),
                            api.getString("apiContext"), api.getString("apiCreator"),
                            api.getString("apiCreatorTenantDomain"), regionalID, tagsDTO);
                    startTime += 120000;
                    writeToFile(eventDTO, writer, fileType);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
//        writeToFile(events, fileName);
    }

    private static void writeToFile(EventDTO eventDTO, BufferedWriter writer, String fileType) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
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
