package techno.be.agencesoeur.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import techno.be.agencesoeur.config.JwtTokenProvider;
import techno.be.agencesoeur.models.dto.AuthentificationDTO;
import techno.be.agencesoeur.reposositories.UserRepository;
import techno.be.agencesoeur.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/Api/auth")
public class AuthRestController {

        AuthenticationManager authenticationManager;
        JwtTokenProvider jwtTokenProvider;
        UserRepository userRepository;

        @Autowired
        public AuthRestController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
                                  UserRepository userRepository) {
            this.authenticationManager = authenticationManager;
            this.jwtTokenProvider = jwtTokenProvider;
            this.userRepository = userRepository;
        }

    /**
     * renvoie mon username et password de mon token il est enregistré
     * @param data
     * @return
     */
    @RequestMapping(value = {"/signin"},method = RequestMethod.POST)
        public ResponseEntity signin(@RequestBody AuthentificationDTO data) {
            try {
                String login = data.getUsername();
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, data.getPassword()));
                String token = jwtTokenProvider
                        .createToken(login, this.userRepository.findByUserName(login).getAuthorities().stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()));
                Map<Object, Object> model = new HashMap<>();
                model.put("username", login);
                model.put("token", token);
                return ResponseEntity.ok(model);
            } catch (Exception e) {
                throw new BadCredentialsException("Invalid username/password supplied");
            }
        }


    }


