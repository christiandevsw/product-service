package com.dojo.product.service.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Products")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
    private BigDecimal dscto;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler","name","description"})
    private Category category;
    private String description;
    //tengo duda cual sea el tipo mas adecuado para beneficios
    //private List<String> benefits=new ArrayList<>();
    private Integer stock;
    private Boolean available;
    @Lob
    @JsonIgnore
    private byte[] photo;

}
