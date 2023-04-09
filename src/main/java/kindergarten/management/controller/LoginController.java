package kindergarten.management.controller;

import kindergarten.management.config.JwtTokenProvider;
import kindergarten.management.constants.LoginEndpoints;
import kindergarten.management.model.dto.TokenDto;
import kindergarten.management.model.dto.UserLoginDto;
import kindergarten.management.model.entity.User;
import kindergarten.management.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
public class LoginController {

    private AuthenticationService authService;
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping(value = LoginEndpoints.LOGIN,
            produces = APPLICATION_JSON_VALUE,
            consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(@RequestBody UserLoginDto loginDto){
        try {
            User user = authService.findUser(loginDto);
            String token = jwtTokenProvider.createToken(user);
//            TokenDto tokenDto = new TokenDto(token);
            return new ResponseEntity<>(token, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("Invalid username/password supplied!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
