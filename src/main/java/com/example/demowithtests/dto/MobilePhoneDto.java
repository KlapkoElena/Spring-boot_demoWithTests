package com.example.demowithtests.dto;

import lombok.experimental.Accessors;

@Accessors(chain = true)
public class MobilePhoneDto {

    public Long id;

    public Boolean mobilePhoneActive = Boolean.TRUE;

    public String brand;

    public String model;
}
