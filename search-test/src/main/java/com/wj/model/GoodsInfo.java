package com.wj.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoodsInfo {


    public GoodsInfo(String goodsId, String goodsName, String goodsImgUrl,double goodsPrice) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsImgUrl = goodsImgUrl;
        this.goodsDescription = goodsDescription;
        this.goodsPrice = goodsPrice;
    }

    private long id;
    private String goodsId;
    private String goodsName;
    private String goodsImgUrl;
    private String goodsDescription;
    private double goodsPrice;
}
