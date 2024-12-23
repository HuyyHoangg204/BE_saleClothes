package com.sale_clothes.nhom11.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "color")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString()
@Getter
@Setter
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int colorID;
    private String colorName;
    private String colorCode;
}
