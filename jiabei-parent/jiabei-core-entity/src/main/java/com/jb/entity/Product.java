package com.jb.entity;

public class Product {
    private Long id;

    private String name;

    private String subname;

    private String code;

    private Long producttype;

    private Long onsaletime;

    private Long offsaletime;

    private Byte state;

    private Long maxprice;

    private Long minprice;

    private Integer salecount;

    private Integer viewcount;

    private Integer commentcount;

    private Integer commentscore;

    private String viewproperties;

    private Integer goodcommnetcount;

    private Integer commoncommentcount;

    private Integer badcommentcount;

    private Long brandid;

    private Long createtime;

    private Long updatetime;

    private String storeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname == null ? null : subname.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Long getProducttype() {
        return producttype;
    }

    public void setProducttype(Long producttype) {
        this.producttype = producttype;
    }

    public Long getOnsaletime() {
        return onsaletime;
    }

    public void setOnsaletime(Long onsaletime) {
        this.onsaletime = onsaletime;
    }

    public Long getOffsaletime() {
        return offsaletime;
    }

    public void setOffsaletime(Long offsaletime) {
        this.offsaletime = offsaletime;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Long getMaxprice() {
        return maxprice;
    }

    public void setMaxprice(Long maxprice) {
        this.maxprice = maxprice;
    }

    public Long getMinprice() {
        return minprice;
    }

    public void setMinprice(Long minprice) {
        this.minprice = minprice;
    }

    public Integer getSalecount() {
        return salecount;
    }

    public void setSalecount(Integer salecount) {
        this.salecount = salecount;
    }

    public Integer getViewcount() {
        return viewcount;
    }

    public void setViewcount(Integer viewcount) {
        this.viewcount = viewcount;
    }

    public Integer getCommentcount() {
        return commentcount;
    }

    public void setCommentcount(Integer commentcount) {
        this.commentcount = commentcount;
    }

    public Integer getCommentscore() {
        return commentscore;
    }

    public void setCommentscore(Integer commentscore) {
        this.commentscore = commentscore;
    }

    public String getViewproperties() {
        return viewproperties;
    }

    public void setViewproperties(String viewproperties) {
        this.viewproperties = viewproperties == null ? null : viewproperties.trim();
    }

    public Integer getGoodcommnetcount() {
        return goodcommnetcount;
    }

    public void setGoodcommnetcount(Integer goodcommnetcount) {
        this.goodcommnetcount = goodcommnetcount;
    }

    public Integer getCommoncommentcount() {
        return commoncommentcount;
    }

    public void setCommoncommentcount(Integer commoncommentcount) {
        this.commoncommentcount = commoncommentcount;
    }

    public Integer getBadcommentcount() {
        return badcommentcount;
    }

    public void setBadcommentcount(Integer badcommentcount) {
        this.badcommentcount = badcommentcount;
    }

    public Long getBrandid() {
        return brandid;
    }

    public void setBrandid(Long brandid) {
        this.brandid = brandid;
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

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
}