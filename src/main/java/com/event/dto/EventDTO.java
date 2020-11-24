package com.event.dto;

public class EventDTO {

    private long timestamp;
    private String apiName;
    private String apiVersion;
    private String nodeID;
    private String metricsName;
    private long value;
    private String apiUUID;
    private String apiContext;
    private String apiCreator;
    private String apiCreatorTenantDomain;
    private String regionalID;
    private TagsDTO tags;

    public EventDTO(long timestamp, String apiName, String apiVersion, String nodeID, String metricsName, long value,
                    String apiUUID, String apiContext, String apiCreator, String apiCreatorTenantDomain,
                    String regionalID, TagsDTO tags) {
        this.timestamp = timestamp;
        this.apiName = apiName;
        this.apiVersion = apiVersion;
        this.nodeID = nodeID;
        this.metricsName = metricsName;
        this.value = value;
        this.apiUUID = apiUUID;
        this.apiContext = apiContext;
        this.apiCreator = apiCreator;
        this.apiCreatorTenantDomain = apiCreatorTenantDomain;
        this.regionalID = regionalID;
        this.tags = tags;
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

    public String getMetricsName() {
        return metricsName;
    }

    public void setMetricsName(String metricsName) {
        this.metricsName = metricsName;
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

    public TagsDTO getTags() {
        return tags;
    }

    public void setTags(TagsDTO tags) {
        this.tags = tags;
    }
}

