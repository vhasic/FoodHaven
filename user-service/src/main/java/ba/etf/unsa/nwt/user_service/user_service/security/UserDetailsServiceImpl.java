package ba.etf.unsa.nwt.user_service.user_service.security;

import ba.etf.unsa.nwt.user_service.user_service.model.UserDTO;
import ba.etf.unsa.nwt.user_service.user_service.repos.RoleRepository;
import ba.etf.unsa.nwt.user_service.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService  {
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userDTO;
        try {
            userDTO= userService.getUserByUsername(username);
        } catch ( Exception ignored){
            // If user not found. Throw this exception.
            throw new UsernameNotFoundException("Username: " + username + " not found");
        }
        String roleName=roleRepository.findById(userDTO.getRole()).get().getName();
        if (roleName.equals("Administrator")) roleName="Admin"; //radi lakšeg rada na gatewayu

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_" + roleName);

        return new User(userDTO.getId().toString(), encoder.encode(userDTO.getPassword()), grantedAuthorities);
    }

    @Bean
    public JwtConfig jwtConfig() {
        return new JwtConfig();
    }
}