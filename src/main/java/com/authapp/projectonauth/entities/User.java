package com.authapp.projectonauth.entities;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static java.time.Instant.now;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID id;
    @Column(name = "email",unique = true,length = 300)
    private String email;
    @Column(name = "user_name",length = 500)
    private String name;
    private String password;
    private String image;
    private boolean enable = true;
    private Instant createdAt= now();
    private Instant updatedAt= now();
//    private String gender;
//    private String address
@Enumerated(EnumType.STRING)
    private Provider provider=Provider.LOCAL;
@ManyToMany(fetch= FetchType.EAGER)
@JoinTable(name = "user_roles",
joinColumns=@JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<Role> roles=new HashSet<>();
@PrePersist
    protected void onCreate(){
    Instant now= now();
    if(createdAt==null) createdAt=now;
    updatedAt=now;
}
@PreUpdate
    protected void onUpdate(){
    updatedAt=Instant.now();
}






}
