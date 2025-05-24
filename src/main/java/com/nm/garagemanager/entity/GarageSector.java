package com.nm.garagemanager.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name ="garagesector")
public class GarageSector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String sector;
    private double base_price;
    private int max_capacity;
    private String open_hour;
    private  String close_hour;
    private int duration_limit_minutes;


}