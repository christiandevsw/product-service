package com.dojo.product.service.app.utility;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

public abstract class ProductMixin {
    @JsonIgnore private BigDecimal dscto;
    @JsonIgnore private Integer stock;
    @JsonIgnore private Boolean available;
    @JsonIgnore private byte[] photo;


}
