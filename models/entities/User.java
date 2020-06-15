package techno.be.agencesoeur.models.entities;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


    @Entity
    @Getter
    @Setter
    @Data
    @ToString
    public class User implements UserDetails {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idUser;

        @Column(nullable = false)
        private String nom;

        @Column( nullable = false)
        @NotBlank(message = "The username is mandatory !")
        private String userName;

        @Column(nullable = false)
        @NotBlank(message = "The password is mandatory !")
        private String password;

        @NotBlank(message = "The email is mandatory !")
        @Email(message = "The email is invalid !")
        private String email;

        @OneToMany(mappedBy = "user")
        private Set<UserRoles> userRoles = new HashSet<>();

        @ManyToMany(targetEntity =Groupe.class)
        private Set<Groupe> groupes = new HashSet<>();


        @ManyToMany(fetch = FetchType.EAGER)
        private Set<Client> clientsEnregistrer = new HashSet<>();

        @OneToMany(mappedBy = "user")
        private Set<Paquets> paquets = new HashSet<>();


        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            Set<GrantedAuthority> authorities = new HashSet<>();

            this.userRoles.parallelStream().forEach(ur -> {
                if (ur.getDateFin() == null && (ur.getDateDeb().isBefore(LocalDate.now()) || ur.getDateDeb().isEqual(LocalDate.now()))) {
                    authorities.add(ur.getRole());
                }
            });
            this.groupes.parallelStream().forEach(g -> authorities.addAll(g.getRoles()));

            return authorities;
        }

        @Override
        public String getUsername() {
            return userName;
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
