package com.jb.entity;

public class Property {
    private Long id;

    private Long createtime;

    private Long updatetime;

    private String propname;

    private Long producttype;

    private Byte type;

    private Byte inputmode;

    private Byte inputtype;

    private String validatepattem;

    private String remake;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public Long getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Long updatetime) {
        this.updatetime = updatetime;
    }

    public String getPropname() {
        return propname;
    }

    public void setPropname(String propname) {
        this.propname = propname == null ? null : propname.trim();
    }

    public Long getProducttype() {
        return producttype;
    }

    public void setProducttype(Long producttype) {
        this.producttype = producttype;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getInputmode() {
        return inputmode;
    }

    public void setInputmode(Byte inputmode) {
        this.inputmode = inputmode;
    }

    public Byte getInputtype() {
        return inputtype;
    }

    public void setInputtype(Byte inputtype) {
        this.inputtype = inputtype;
    }

    public String getValidatepattem() {
        return validatepattem;
    }

    public void setValidatepattem(String validatepattem) {
        this.validatepattem = validatepattem == null ? null : validatepattem.trim();
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake == null ? null : remake.trim();
    }
}