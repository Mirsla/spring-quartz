package com.alex.job.entity;

public class Company {
    private Long companyId;

    private String companyName;

    private String companyCode;

    private String province;

    private String city;

    private String region;

    private String address;

    private String intro;

    private String lotteryServerId;

    private Byte companyCategory;

    private String packageVersion;

    private Integer projectCount;

    private Byte status;

    private String clientCustomMade;

    private String weChatOfficialAccounts;

    private Long createdTime;

    private String updateBy;

    private Long updateTime;

    private String createdBy;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode == null ? null : companyCode.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    public String getLotteryServerId() {
        return lotteryServerId;
    }

    public void setLotteryServerId(String lotteryServerId) {
        this.lotteryServerId = lotteryServerId == null ? null : lotteryServerId.trim();
    }

    public Byte getCompanyCategory() {
        return companyCategory;
    }

    public void setCompanyCategory(Byte companyCategory) {
        this.companyCategory = companyCategory;
    }

    public String getPackageVersion() {
        return packageVersion;
    }

    public void setPackageVersion(String packageVersion) {
        this.packageVersion = packageVersion == null ? null : packageVersion.trim();
    }

    public Integer getProjectCount() {
        return projectCount;
    }

    public void setProjectCount(Integer projectCount) {
        this.projectCount = projectCount;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getClientCustomMade() {
        return clientCustomMade;
    }

    public void setClientCustomMade(String clientCustomMade) {
        this.clientCustomMade = clientCustomMade == null ? null : clientCustomMade.trim();
    }

    public String getWeChatOfficialAccounts() {
        return weChatOfficialAccounts;
    }

    public void setWeChatOfficialAccounts(String weChatOfficialAccounts) {
        this.weChatOfficialAccounts = weChatOfficialAccounts == null ? null : weChatOfficialAccounts.trim();
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }
}