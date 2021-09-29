package guru.sfg.brewery.domain.security;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by jt on 6/21/20.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Authority {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String role;

  @ManyToMany(mappedBy = "authorities")
  private Set<User> users;
}
