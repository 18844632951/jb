package com.jb.entity;

public class ProductExt {
    private Long id;

    private Long createtime;

    private Long updatetime;

    private String description;

    private Long productid;

    private String richcontent;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Long getProductid() {
        return productid;
    }

    public void setProductid(Long productid) {
        this.productid = productid;
    }

    public String getRichcontent() {
        return richcontent;
    }

    public void setRichcontent(String richcontent) {
        this.richcontent = richcontent == null ? null : richcontent.trim();
    }
}