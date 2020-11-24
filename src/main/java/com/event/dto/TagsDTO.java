package com.event.dto;

public class TagsDTO {

    private String applicationId;
    private String applicationName;
    private String applicationOwner;
    private String gatewayType;
    private String label;
    private String apiResourceTemplate;
    private String apiMethod;
    private String username;
    private String userTenantDomain;
    private String responseType;
    private String requestType;
    private String latencyType;
    private String destination;
    private String city;
    private String country;
    private String throttledOutReason;
    private String operatingSystem;
    private String userAgent;

    public TagsDTO() {
    }

    public TagsDTO(String applicationId, String applicationName, String applicationOwner, String gatewayType,
                   String label, String apiResourceTemplate, String apiMethod, String username,
                   String userTenantDomain, String responseType, String requestType, String latencyType,
                   String destination, String city, String country, String throttledOutReason, String operatingSystem,
                   String userAgent) {
        this.applicationId = applicationId;
        this.applicationName = applicationName;
        this.applicationOwner = applicationOwner;
        this.gatewayType = gatewayType;
        this.label = label;
        this.apiResourceTemplate = apiResourceTemplate;
        this.apiMethod = apiMethod;
        this.username = username;
        this.userTenantDomain = userTenantDomain;
        this.responseType = responseType;
        this.requestType = requestType;
        this.latencyType = latencyType;
        this.destination = destination;
        this.city = city;
        this.country = country;
        this.operatingSystem = operatingSystem;
        this.userAgent = userAgent;
        this.throttledOutReason = throttledOutReason;
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

    public String getResponseType() {

        return responseType;
    }

    public void setResponseType(String responseType) {

        this.responseType = responseType;
    }

    public String getRequestType() {

        return requestType;
    }

    public void setRequestType(String requestType) {

        this.requestType = requestType;
    }

    public String getLatencyType() {

        return latencyType;
    }

    public void setLatencyType(String latencyType) {

        this.latencyType = latencyType;
    }

    public String getDestination() {

        return destination;
    }

    public void setDestination(String destination) {

        this.destination = destination;
    }

    public String getCity() {

        return city;
    }

    public void setCity(String city) {

        this.city = city;
    }

    public String getCountry() {

        return country;
    }

    public void setCountry(String country) {

        this.country = country;
    }

    public String getOperatingSystem() {

        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {

        this.operatingSystem = operatingSystem;
    }

    public String getUserAgent() {

        return userAgent;
    }

    public void setUserAgent(String userAgent) {

        this.userAgent = userAgent;
    }

    public String getThrottledOutReason() {

        return throttledOutReason;
    }

    public void setThrottledOutReason(String throttledOutReason) {

        this.throttledOutReason = throttledOutReason;
    }
}
