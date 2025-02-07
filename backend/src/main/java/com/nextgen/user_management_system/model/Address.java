package com.nextgen.user_management_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address_db")
@Data
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id")
    private Integer id;

    @NotNull
    private String address;

    @NotNull
    @Column(name = "address_type")
    private String addressType;

}
