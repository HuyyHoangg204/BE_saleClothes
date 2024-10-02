package com.sale_clothes.nhom11.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "InvalidtedToken")
public class InvalidatedToken {
    @Id
    @Column(name = "tokenId")
    String id;

    @Column(name = "expiryTime")
    Date expiryTime;
}
