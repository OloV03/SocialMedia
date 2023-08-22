package com.IlyaJukov.SocialMedia.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Entity @Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "login") })
@NoArgsConstructor
public class User implements UserDetails {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     private String name;
     @Column(unique = true)
     private String login;
     @JsonIgnore
     private String pass;

     public User(String name, String login, String pass) {
          this.name = name;
          this.login = login;
          this.pass = pass;
     }

     @ManyToMany @JsonIgnore
     private List<User> friends;
     @ManyToMany @JsonIgnore
     private List<User> following;
     @ManyToMany @JsonIgnore
     private List<User> followers;

     public void addFriend(User friend) {
          friend.addFriend(friend);
     }
     public void deleteFriend(User friend) {
          friends.remove(friend);
     }

     public void addFollowing(User user){
          following.add(user);
     }
     public void deleteFollowing(User user) {
          following.remove(user);
     }

     public void addFollower(User user) {
          followers.add(user);
     }
     public void deleteFollower(User user) {
          followers.remove(user);
     }

     @Override
     public Collection<? extends GrantedAuthority> getAuthorities() {
          return List.of(new SimpleGrantedAuthority("user"));
     }

     @Override
     public String getPassword() {
          return pass;
     }

     @Override
     public String getUsername() {
          return login;
     }

     @Override
     public boolean isAccountNonExpired() {
          return true;
     }

     @Override
     public boolean isAccountNonLocked() {
          return true;
     }

     @Override
     public boolean isCredentialsNonExpired() {
          return true;
     }

     @Override
     public boolean isEnabled() {
          return true;
     }
}
