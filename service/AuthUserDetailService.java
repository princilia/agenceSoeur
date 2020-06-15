package techno.be.agencesoeur.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import techno.be.agencesoeur.mapping.AuthMapper;
import techno.be.agencesoeur.reposositories.UserRepository;
@Service
public class AuthUserDetailService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public AuthUserDetailService(UserRepository userRepository){
        this.userRepository=userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
        //if (userRepository.findByUserName(username).equals(username)) {
            return userRepository.findByUserName(username);

//        } else {
//            throw new UsernameNotFoundException("User not found with username: " + username);
////        }
//        }


    }

}
