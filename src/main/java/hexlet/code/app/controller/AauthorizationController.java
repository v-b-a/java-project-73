//package hexlet.code.app.controller;
//
//import hexlet.code.app.dto.AuthRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.validation.Valid;
//
//@RestController
//@RequestMapping("${base-url}")
//public class AauthorizationController {
//    public static final String AUTH_PATH = "login";
//
//    @Autowired
//    private JWTUtils jwtUtils;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @PostMapping(AUTH_PATH)
//    public ResponseEntity getAuthToken(@Valid @RequestBody AuthRequest authRequest) {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
//            )
//        } catch (Exception exception) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid email/password");
//        }
//        return ResponseEntity.ok().body(jwtUtils.geterateToken(authRequest.getEmail()));
//    }
//}
