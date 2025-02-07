package com.nextgen.user_management_system.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_db")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "email_id", unique = true, nullable = false)
    private String emailId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String password;

    @Column(name = "phone_no")
    private long phoneNo;

    private String interests;

    private String role;

    @Column(name = "about_me")
    private String aboutMe;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_address_id")
    private Address address;

}
