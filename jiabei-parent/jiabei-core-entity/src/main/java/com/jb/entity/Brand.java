package com.jb.entity;

import java.io.Serializable;

public class Brand implements Serializable{
    private Long id;

    private Long createtime;

    private Long updatetime;

    private String name;

    private String englishname;

    private String fristletter;

    private String description;

    private Long producttype;

    private Integer sortindex;

    private String logo;

    /**
     * 关联分类
     */
    private ProductType types;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getEnglishname() {
        return englishname;
    }

    public void setEnglishname(String englishname) {
        this.englishname = englishname == null ? null : englishname.trim();
    }

    public String getFristletter() {
        return fristletter;
    }

    public void setFristletter(String fristletter) {
        this.fristletter = fristletter == null ? null : fristletter.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Long getProducttype() {
        return producttype;
    }

    public void setProducttype(Long producttype) {
        this.producttype = producttype;
    }

    public Integer getSortindex() {
        return sortindex;
    }

    public void setSortindex(Integer sortindex) {
        this.sortindex = sortindex;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public ProductType getTypes() {
        return types;
    }

    public void setTypes(ProductType types) {
        this.types = types;
    }
}