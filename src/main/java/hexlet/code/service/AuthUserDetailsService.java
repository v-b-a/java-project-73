package hexlet.code.service;

//@Service
//@RequiredArgsConstructor
//public class AuthUserDetailsService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        org.springframework.security.core.userdetails.User user = userRepository.findByEmail(username)
//                .map(x -> new org.springframework.security.core.userdetails.User(
//                        x.getEmail(),
//                        x.getPassword(),
//                        SecurityConfig.DEFAULT_AUTHORITIES)
//                )
//                .orElseThrow(() -> new UsernameNotFoundException("Not found user with 'email': " + username));
//        return user;
//    }
//}