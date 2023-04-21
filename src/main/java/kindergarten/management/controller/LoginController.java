package kindergarten.management.controller;

import kindergarten.management.config.JwtTokenProvider;
import kindergarten.management.constants.Endpoints;
import kindergarten.management.model.dto.TokenDto;
import kindergarten.management.model.dto.UserLoginDto;
import kindergarten.management.model.entity.User;
import kindergarten.management.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin()
@AllArgsConstructor
public class LoginController {

    private AuthenticationService authService;
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping(value = Endpoints.LOGIN,
            produces = APPLICATION_JSON_VALUE,
            consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenDto> login(final @RequestBody UserLoginDto loginDto){
        try {
            User user = authService.findUser(loginDto);
            String token = jwtTokenProvider.createToken(user);
            TokenDto tokenDto = new TokenDto();
            tokenDto.setToken(token);
            return ResponseEntity.ok(tokenDto);
        } catch (Exception e) {
//            return new ResponseEntity<>(new TokenDto("Invalid username/password supplied!"), HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(new TokenDto(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
