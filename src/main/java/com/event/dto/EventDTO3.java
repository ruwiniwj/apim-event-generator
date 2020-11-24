package com.event.dto;

public class EventDTO3 {

    private long timestamp;
    private String apiName;
    private String apiVersion;
    private String nodeID;
    private long value;
    private String apiUUID;
    private String apiContext;
    private String apiCreator;
    private String apiCreatorTenantDomain;
    private String regionalID;
    private String applicationId;
    private String applicationName;
    private String applicationOwner;
    private String gatewayType;
    private String label;
    private String apiResourceTemplate;
    private String apiMethod;
    private String username;
    private String userTenantDomain;
    private String destination;

    public EventDTO3(EventDTO eventDTO) {
        this.timestamp = eventDTO.getTimestamp();
        this.apiName = eventDTO.getApiName();
        this.apiVersion = eventDTO.getApiVersion();
        this.nodeID = eventDTO.getNodeID();
        this.value = eventDTO.getValue();
        this.apiUUID = eventDTO.getApiUUID();
        this.apiContext = eventDTO.getApiContext();
        this.apiCreator = eventDTO.getApiCreator();
        this.apiCreatorTenantDomain = eventDTO.getApiCreatorTenantDomain();
        this.regionalID = eventDTO.getRegionalID();
        this.applicationId = eventDTO.getTags().getApplicationId();
        this.applicationName = eventDTO.getTags().getApplicationName();
        this.applicationOwner = eventDTO.getTags().getApplicationOwner();
        this.gatewayType = eventDTO.getTags().getGatewayType();
        this.label = eventDTO.getTags().getLabel();
        this.apiResourceTemplate = eventDTO.getTags().getApiResourceTemplate();
        this.apiMethod = eventDTO.getTags().getApiMethod();
        this.username = eventDTO.getTags().getUsername();
        this.userTenantDomain = eventDTO.getTags().getUserTenantDomain();
        this.destination = eventDTO.getTags().getDestination();
    }

    public EventDTO3(long timestamp, String apiName, String apiVersion, String nodeID, long value, String apiUUID,
                     String apiContext, String apiCreator, String apiCreatorTenantDomain, String regionalID,
                     String applicationId, String applicationName, String applicationOwner, String gatewayType,
                     String label, String apiResourceTemplate, String apiMethod, String username,
                     String userTenantDomain, String destination) {
        this.timestamp = timestamp;
        this.apiName = apiName;
        this.apiVersion = apiVersion;
        this.nodeID = nodeID;
        this.value = value;
        this.apiUUID = apiUUID;
        this.apiContext = apiContext;
        this.apiCreator = apiCreator;
        this.apiCreatorTenantDomain = apiCreatorTenantDomain;
        this.regionalID = regionalID;
        this.applicationId = applicationId;
        this.applicationName = applicationName;
        this.applicationOwner = applicationOwner;
        this.gatewayType = gatewayType;
        this.label = label;
        this.apiResourceTemplate = apiResourceTemplate;
        this.apiMethod = apiMethod;
        this.username = username;
        this.userTenantDomain = userTenantDomain;
        this.destination = destination;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getNodeID() {
        return nodeID;
    }

    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public String getApiUUID() {
        return apiUUID;
    }

    public void setApiUUID(String apiUUID) {
        this.apiUUID = apiUUID;
    }

    public String getApiContext() {
        return apiContext;
    }

    public void setApiContext(String apiContext) {
        this.apiContext = apiContext;
    }

    public String getApiCreator() {
        return apiCreator;
    }

    public void setApiCreator(String apiCreator) {
        this.apiCreator = apiCreator;
    }

    public String getApiCreatorTenantDomain() {
        return apiCreatorTenantDomain;
    }

    public void setApiCreatorTenantDomain(String apiCreatorTenantDomain) {
        this.apiCreatorTenantDomain = apiCreatorTenantDomain;
    }

    public String getRegionalID() {
        return regionalID;
    }

    public void setRegionalID(String regionalID) {
        this.regionalID = regionalID;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationOwner() {
        return applicationOwner;
    }

    public void setApplicationOwner(String applicationOwner) {
        this.applicationOwner = applicationOwner;
    }

    public String getGatewayType() {
        return gatewayType;
    }

    public void setGatewayType(String gatewayType) {
        this.gatewayType = gatewayType;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getApiResourceTemplate() {
        return apiResourceTemplate;
    }

    public void setApiResourceTemplate(String apiResourceTemplate) {
        this.apiResourceTemplate = apiResourceTemplate;
    }

    public String getApiMethod() {
        return apiMethod;
    }

    public void setApiMethod(String apiMethod) {
        this.apiMethod = apiMethod;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserTenantDomain() {
        return userTenantDomain;
    }

    public void setUserTenantDomain(String userTenantDomain) {
        this.userTenantDomain = userTenantDomain;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}

