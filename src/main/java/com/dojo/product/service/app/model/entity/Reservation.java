//package com.dojo.dish.service.app.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@Entity
//@Table(name = "reservations")
//@NoArgsConstructor
//@AllArgsConstructor
//@Data
//public class Reservation {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "date")
//    private Date reservationDate;
//    @OneToMany(fetch = FetchType.LAZY)
//    private List<Product> Products=new ArrayList<>();
//    @ManyToOne
//    private Customer customer;
//}
