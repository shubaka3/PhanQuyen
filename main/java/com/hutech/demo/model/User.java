package com.hutech.demo.model;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private int birthDay;
    private boolean isDeleted;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
