package guru.sfg.brewery.domain.security;

import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by jt on 6/21/20.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String username;
  private String password;

  @Singular
  @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
  @JoinTable(name = "user_role",
      joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
      inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")})
  private Set<Role> roles;

  @Transient // Mean calculated, not coming from database
  private Set<Authority> authorities;

  public Set<Authority> getAuthorities() {
    return this.roles.stream()
        .map(Role::getAuthorities)
        .flatMap(Set::stream)
        .collect(Collectors.toSet());
  }

  @Builder.Default
  private Boolean accountNonExpired = true;

  @Builder.Default
  private Boolean accountNonLocked = true;

  @Builder.Default
  private Boolean credentialsNonExpired = true;

  @Builder.Default
  private Boolean enabled = true;

}