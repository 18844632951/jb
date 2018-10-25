package com.jb.entity;

import java.math.BigDecimal;

public class SKU {
    private Long id;

    private Long createtime;

    private Long updatetime;

    private Long productid;

    private String skucode;

    private String skuname;

    private BigDecimal markeprice;

    private BigDecimal price;

    private BigDecimal costprice;

    private Integer salecount;

    private Integer sortindex;

    private Integer availablestock;

    private Integer frozenstock;

    private String skuproperty;

    private String skumainpic;

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

    public Long getProductid() {
        return productid;
    }

    public void setProductid(Long productid) {
        this.productid = productid;
    }

    public String getSkucode() {
        return skucode;
    }

    public void setSkucode(String skucode) {
        this.skucode = skucode == null ? null : skucode.trim();
    }

    public String getSkuname() {
        return skuname;
    }

    public void setSkuname(String skuname) {
        this.skuname = skuname == null ? null : skuname.trim();
    }

    public BigDecimal getMarkeprice() {
        return markeprice;
    }

    public void setMarkeprice(BigDecimal markeprice) {
        this.markeprice = markeprice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getCostprice() {
        return costprice;
    }

    public void setCostprice(BigDecimal costprice) {
        this.costprice = costprice;
    }

    public Integer getSalecount() {
        return salecount;
    }

    public void setSalecount(Integer salecount) {
        this.salecount = salecount;
    }

    public Integer getSortindex() {
        return sortindex;
    }

    public void setSortindex(Integer sortindex) {
        this.sortindex = sortindex;
    }

    public Integer getAvailablestock() {
        return availablestock;
    }

    public void setAvailablestock(Integer availablestock) {
        this.availablestock = availablestock;
    }

    public Integer getFrozenstock() {
        return frozenstock;
    }

    public void setFrozenstock(Integer frozenstock) {
        this.frozenstock = frozenstock;
    }

    public String getSkuproperty() {
        return skuproperty;
    }

    public void setSkuproperty(String skuproperty) {
        this.skuproperty = skuproperty == null ? null : skuproperty.trim();
    }

    public String getSkumainpic() {
        return skumainpic;
    }

    public void setSkumainpic(String skumainpic) {
        this.skumainpic = skumainpic == null ? null : skumainpic.trim();
    }
}