package hexlet.code.app.config;

import hexlet.code.app.dao.UserRepository;
import hexlet.code.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private UserRepository repository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        // Список полномочий, которые будут предоставлены пользователю после аутентификации
//        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("user"));
//
//        // BEGIN
//        User user = repository.findByFirstName(username)
//                .orElseThrow(() -> new UserNotFoundException("User not found"));
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getFirstName(), user.getPassword(), authorities
//        );
//        // END
//    }
//}
