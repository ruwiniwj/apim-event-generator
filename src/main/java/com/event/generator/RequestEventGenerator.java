package com.event.generator;

import com.event.constants.Constants;
import com.event.models.RequestData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

public class RequestEventGenerator {

    public static RequestData createRequestEvent(String consumerKey, String tenant, String requestPath, long addedTime) {
        RequestData requestData = new RequestData(
                                        Constants.defaultApplicationConsumerKey,
                                        Constants.defaultApplicationName,
                                        Constants.defaultApplicationId,
                                        Constants.defaultApplicationOwner,
                                        Constants.defaultApiContext,
                                        Constants.defaultApiName,
                                        Constants.defaultApiVersion,
                                        Constants.defaultApiResourcePath,
                                        Constants.defaultApiResourceTemplate,
                                        Constants.defaultApiMethod,
                                        Constants.defaultApiCreator,
                                        Constants.defaultApiCreatorTenantDomain,
                                        Constants.defaultApiTier,
                                        Constants.defaultApiHostname,
                                        Constants.defaultUsername,
                                        Constants.defaultUserTenantDomain,
                                        Constants.defaultUserIp,
                                        Constants.defaultUserAgent,
                                        Constants.defaultRequestTimestamp,
                                        Constants.defaultThrottledOut,
                                        Constants.defaultResponseTime,
                                        Constants.defaultServiceTime,
                                        Constants.defaultBackendTime,
                                        Constants.defaultResponseCacheHit,
                                        Constants.defaultResponseSize,
                                        Constants.defaultProtocol,
                                        Constants.defaultResponseCode,
                                        Constants.defaultDestination,
                                        Constants.defaultSecurityLatency,
                                        Constants.defaultThrottlingLatency,
                                        Constants.defaultRequestMedLat,
                                        Constants.defaultResponseMedLat,
                                        Constants.defaultBackendLatency,
                                        Constants.defaultOtherLatency,
                                        Constants.defaultGatewayType,
                                        Constants.defaultLabel
        );
        requestData.setApplicationConsumerKey(consumerKey);
        requestData.setApiCreatorTenantDomain(tenant);
        requestData.setUserTenantDomain(tenant);
        requestData.setApiResourcePath(requestPath);
        requestData.setApiResourceTemplate(requestPath);
        requestData.setRequestTimestamp(Constants.defaultRequestTimestamp + addedTime);
        return requestData;
    }

    // used to write to a json file
//    public static ArrayList<RequestData> generateRequestEvent(String[] tenants,
//                                                               String[] requestPaths,
//                                                               long numberOfEvents,
//                                                               int timeFrame) {
//        ArrayList<RequestData> requestEvents = new ArrayList<RequestData>();
//        for (int i=0; i<numberOfEvents; i++) {
//            int tenantIndex = (int) Math.round(Math.random()*(tenants.length-1));
//            int requestIndex = (int) Math.round(Math.random()*(requestPaths.length-1));
//            long addedTime = getAddedTime(timeFrame);
////            requestEvents.add(createRequestEvent(tenants[i%tenants.length], requestPaths[i%requestPaths.length], addedTime));
//            requestEvents.add(createRequestEvent(tenants[tenantIndex], requestPaths[requestIndex], addedTime));
//        }
//        return requestEvents;
//    }

//    public static void writeToJSONFile(ArrayList<RequestData> requestEvents, String fileName) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonString = objectMapper.writeValueAsString(requestEvents);
//        FileWriter file = new FileWriter(fileName);
//        file.write(jsonString);
//        file.close();
//    }

    // used to write to json file
    public static void writeToJSONFile(String[] consumerKeys, String[] consumerKeyWeights, String[] tenants, String[] tenantWeights, String[] requestPaths, String[] requestPathWeights, long numberOfEvents, int timeFrame, String filePath) throws IOException {
        FileWriter file = new FileWriter(filePath);
        file.write("["+"\n");
        writeToFile(consumerKeys, consumerKeyWeights, tenants, tenantWeights, requestPaths, requestPathWeights, numberOfEvents, timeFrame, file, "json");
        file.write("]");
        file.close();
    }

    private static int getInterval(int index, int[] intervals) {
        for (int i=0; i<intervals.length; i++) {
            if (index<intervals[i]) {
                return i;
            }
        }
        return 0;
    }

    // refactored method
    private static void writeToFile(String[] consumerKeys, String[] consumerKeyWeights, String[] tenants, String[] tenantWeights, String[] requestPaths, String[] requestPathWeights, long numberOfEvents, int timeFrame, FileWriter file, String fileType) throws IOException {
        int[] consumerKeyIndexIntervals = new int[consumerKeyWeights.length];
        for (int j=0; j<consumerKeyWeights.length; j++) {
            consumerKeyIndexIntervals[j] = (int) (numberOfEvents * Double.parseDouble(consumerKeyWeights[j]));
        }
        int[] tenantIndexIntervals = new int[tenantWeights.length];
        for (int j=0; j<tenantWeights.length; j++) {
            tenantIndexIntervals[j] = (int) (numberOfEvents * Double.parseDouble(tenantWeights[j]));
        }
        int[] requestIndexIntervals = new int[requestPathWeights.length];
        for (int j=0; j<requestPathWeights.length; j++) {
            requestIndexIntervals[j] = (int) (numberOfEvents * Double.parseDouble(requestPathWeights[j]));
        }
        for (int i=0; i<numberOfEvents; i++) {
            int consumerKeyIndex = getInterval(i, consumerKeyIndexIntervals);
            int tenantIndex = getInterval(i, tenantIndexIntervals);
            int requestIndex = getInterval(i, requestIndexIntervals);

            // generate random values
//            int consumerKeyIndex = (int) Math.round(Math.random()*(consumerKeys.length-1));
//            int tenantIndex = (int) Math.round(Math.random()*(tenants.length-1));
//            int requestIndex = (int) Math.round(Math.random()*(requestPaths.length-1));
            long addedTime = getAddedTime(timeFrame);
            RequestData requestEvent = createRequestEvent(consumerKeys[consumerKeyIndex], tenants[tenantIndex], requestPaths[requestIndex], addedTime);
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(requestEvent);
            if (fileType.equals("json")) {
                if (i==numberOfEvents-1) {
                    file.write(jsonString+"\n");
                } else {
                    file.write(jsonString+",\n");
                }
            } else if (fileType.equals("csv")) {
                JSONObject jsonObject = new JSONObject(jsonString);
                Set<String> keys = jsonObject.keySet();
                ArrayList<String> valueList = new ArrayList<>();
                // uncomment if titles are required
//                if (i==0) {
//                    String keysString = String.join(",", keys);
//                    file.write(keysString+"\n");
//                }
                keys.forEach(key -> {
                    String valueStr = jsonObject.get(key).toString();
                    if (valueStr.contains(",")) {
                        valueStr = "\"" + valueStr + "\"";
                    }
                    valueList.add(valueStr);
                });
                file.write(String.join(",", valueList) + "\n");
            } else {
                file.write(jsonString+"\n");
            }

        }
    }

    // used to write line by line to a file
    public static void writeToEmptyFile(String[] consumerKeys, String[] consumerKeyWeights, String[] tenants, String[] tenantWeights, String[] requestPaths, String[] requestPathWeights, long numberOfEvents, int timeFrame, String filePath, String fileType)
            throws IOException {
        FileWriter file = new FileWriter(filePath);
        writeToFile(consumerKeys, consumerKeyWeights, tenants, tenantWeights, requestPaths, requestPathWeights, numberOfEvents, timeFrame, file, fileType);
        file.close();
    }

    private static long getAddedTime(int timeFrame) {
        int month;
        int day;
        int hour;
        int minutes;
        int seconds;
        if (timeFrame==-1) {
            month = 0;
            day = 0;
        } else {
            month = (int) Math.round(Math.random()*(timeFrame));
            day = (int) Math.round(Math.random()*30);
        }
        hour = (int) Math.round(Math.random()*23);
        minutes = (int) Math.round(Math.random()*59);
        seconds = (int) Math.round(Math.random()*59);
        return (seconds+minutes*60+hour*60*60+day*24*60*60+month*30*24*60*60)*1000;
    }

    public static void main(String[] args) {
        String[] consumerKeys;
        String[] consumerKeyWeights;
        String[] tenants;
        String[] tenantWeights;
        String[] requestPaths;
        String[] requestPathWeights;
        int numberOfEvents;
        int timeFrame;
        String fileName;
        String fileType;
        Properties prop = new Properties();
        InputStream input;
        try {
            input = RequestEventGenerator.class.getClassLoader().getResourceAsStream("config.properties");
            // load a properties file
            prop.load(input);
            // get the property value and print it out
            consumerKeys = prop.getProperty("consumerKeys", "consumerKey1").split(",");
            consumerKeyWeights = prop.getProperty("consumerKeyWeights", "").split(",");
            if (consumerKeyWeights.length == 0) {
                consumerKeyWeights = new String[consumerKeys.length];
                for (int i=0; i<consumerKeys.length; i++) {
                    if (i==0) {
                        consumerKeyWeights[i] = String.valueOf(1.0/consumerKeys.length);
                    } else {
                        consumerKeyWeights[i] = String.valueOf(Double.parseDouble(consumerKeyWeights[i-1]) + 1.0/consumerKeys.length);
                    }
                }
            }
            tenants = prop.getProperty("tenants", "tenant1,tenant2").split(",");
            tenantWeights = prop.getProperty("tenantWeights", "").split(",");
            if (tenantWeights.length == 0) {
                tenantWeights = new String[tenants.length];
                for (int i=0; i<tenants.length; i++) {
                    if (i==0) {
                        tenantWeights[i] = String.valueOf(1.0/tenants.length);
                    } else {
                        tenantWeights[i] = String.valueOf(Double.parseDouble(tenantWeights[i-1]) + 1.0/tenants.length);
                    }
                }
            }
            requestPaths = prop.getProperty("reqPaths", "/order,/menu").split(",");
            requestPathWeights = prop.getProperty("reqPathWeights", "").split(",");
            if (requestPathWeights.length == 0) {
                requestPathWeights = new String[requestPaths.length];
                for (int i=0; i<requestPaths.length; i++) {
                    if (i==0) {
                        requestPathWeights[i] = String.valueOf(1.0/requestPaths.length);
                    } else {
                        requestPathWeights[i] = String.valueOf(Double.parseDouble(requestPathWeights[i-1]) + 1.0/requestPaths.length);
                    }
                }
            }
            numberOfEvents = Integer.parseInt(prop.getProperty("eventCount", "100"));
            timeFrame = Integer.parseInt(prop.getProperty("monthVariation", "3"));
            fileName = prop.getProperty("fileName", "events");
            fileType = prop.getProperty("fileType", "json");
            if (input != null) {
                input.close();
            }
            if (fileType.equals("json")) {
                writeToJSONFile(consumerKeys, consumerKeyWeights, tenants, tenantWeights, requestPaths, requestPathWeights, numberOfEvents, timeFrame, fileName+".json");
            } else if (fileType.equals("csv")) {
                writeToEmptyFile(consumerKeys, consumerKeyWeights, tenants, tenantWeights, requestPaths, requestPathWeights, numberOfEvents, timeFrame, fileName+".csv", "csv");
            } else {
                writeToEmptyFile(consumerKeys, consumerKeyWeights, tenants, tenantWeights, requestPaths, requestPathWeights, numberOfEvents, timeFrame, fileName, "file");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
