//package ba.etf.unsa.nwt.user_service.user_service.security;
//
//import ba.etf.unsa.nwt.user_service.user_service.model.UserDTO;
//import ba.etf.unsa.nwt.user_service.user_service.repos.RoleRepository;
//import ba.etf.unsa.nwt.user_service.user_service.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service   // It has to be annotated with @Service.
//public class UserDetailsServiceImpl implements UserDetailsService  {
//    @Autowired
//    private BCryptPasswordEncoder encoder;
//    @Autowired
//    private RoleRepository roleRepository;
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserDTO userDTO;
//        try {
//            userDTO= userService.getUserByUsername(username);
//        } catch ( Exception ignored){
//            // If user not found. Throw this exception.
//            throw new UsernameNotFoundException("Username: " + username + " not found");
//        }
//        // todo ovdje umjesto naziva role staviti id
//        String roleName=roleRepository.findById(userDTO.getRole()).get().getName();
//
//        // Remember that Spring needs roles to be in this format: "ROLE_" + userRole (i.e. "ROLE_ADMIN")
//        // So, we need to set it to that format, so we can verify and compare roles (i.e. hasRole("ADMIN")).
//        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
//                .commaSeparatedStringToAuthorityList("ROLE_" + roleName);
//
//        // The "User" class is provided by Spring and represents a model class for user to be returned by UserDetailsService
//        // And used by auth manager to verify and check user authentication.
//        return new User(userDTO.getUsername(), encoder.encode(userDTO.getPassword()), grantedAuthorities);
////        return new User(userDTO.getUsername(), userDTO.getPassword(), grantedAuthorities);
//    }
//
//    @Bean
//    public JwtConfig jwtConfig() {
//        return new JwtConfig();
//    }
//}