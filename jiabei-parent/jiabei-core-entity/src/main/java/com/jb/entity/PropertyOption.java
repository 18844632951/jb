package com.jb.entity;

public class PropertyOption {
    private Long id;

    private Long cretaetime;

    private Long updatetime;

    private Long propid;

    private String optionvalue;

    private String optionpic;

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

    public Long getPropid() {
        return propid;
    }

    public void setPropid(Long propid) {
        this.propid = propid;
    }

    public String getOptionvalue() {
        return optionvalue;
    }

    public void setOptionvalue(String optionvalue) {
        this.optionvalue = optionvalue == null ? null : optionvalue.trim();
    }

    public String getOptionpic() {
        return optionpic;
    }

    public void setOptionpic(String optionpic) {
        this.optionpic = optionpic == null ? null : optionpic.trim();
    }
}