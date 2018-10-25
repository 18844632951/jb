package com.jb.entity;

public class SkuProperty {
    private Long id;

    private Long cretaetime;

    private Long updatetime;

    private Long skuid;

    private Long propid;

    private String propname;

    private Long optionid;

    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCretaetime() {
        return cretaetime;
    }

    public void setCretaetime(Long cretaetime) {
        this.cretaetime = cretaetime;
    }

    public Long getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Long updatetime) {
        this.updatetime = updatetime;
    }

    public Long getSkuid() {
        return skuid;
    }

    public void setSkuid(Long skuid) {
        this.skuid = skuid;
    }

    public Long getPropid() {
        return propid;
    }

    public void setPropid(Long propid) {
        this.propid = propid;
    }

    public String getPropname() {
        return propname;
    }

    public void setPropname(String propname) {
        this.propname = propname == null ? null : propname.trim();
    }

    public Long getOptionid() {
        return optionid;
    }

    public void setOptionid(Long optionid) {
        this.optionid = optionid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }
}