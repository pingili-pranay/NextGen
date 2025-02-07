package com.nextgen.user_management_system.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "post_db")
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "content_of_post")
    private String contentOfPost;

    private List<String> tags;

}
