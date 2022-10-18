package com.example.demowithtests.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mobilephones")
public class MobilePhone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "mobilePhone_active")
    private Boolean mobilePhoneActive = Boolean.TRUE;
    @Column(name = "brand")
    private String brand;
    @Column(name = "model")
    private String model;
}
