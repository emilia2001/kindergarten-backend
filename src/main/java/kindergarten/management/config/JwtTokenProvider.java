package kindergarten.management.config;

import io.jsonwebtoken.*;
import kindergarten.management.model.enums.EUserRole;
import kindergarten.management.model.entity.Parent;
import kindergarten.management.model.entity.User;
import kindergarten.management.service.AdminService;
import kindergarten.management.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey;

    @Value("${security.jwt.token.expire-length:960000000}")
    private long validityInMilliseconds; // 26,666666667 h

    private AdminService adminService;

    private ParentService parentService;

    @Autowired
    public JwtTokenProvider(AdminService adminService, ParentService parentService) {
        this.adminService = adminService;
        this.parentService = parentService;
    }

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("username", user.getUsername());
        if (user instanceof Parent) {
//        claims.put("firstName",parent.getFirstName());
//        claims.put("lastName",parent.getLastName());
//        claims.put("email", ((Parent) user).getEmail());
            claims.put("id", user.getId());
            claims.put("role", EUserRole.PARENT);
        } else {
//        claims.put("firstName",parent.getFirstName());
//        claims.put("lastName",parent.getLastName());
//        claims.put("email", parent.getEmail());
        claims.put("role", EUserRole.ADMIN);
        }
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        String token = Jwts.builder()//
                .setClaims(claims)//
                .setIssuedAt(now)//
                .setExpiration(validity)//
                .signWith(SignatureAlgorithm.HS256, secretKey)//
                .compact();
        UsernamePasswordAuthenticationToken auth = getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(auth);
        return token;
    }

    UsernamePasswordAuthenticationToken getAuthentication(String token) {
        EUserRole role = getRole(token);
        UserDetails userDetails = EUserRole.ADMIN == role ?
                adminService.findByUsername(getUsername(token)) : parentService.findByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    private String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    private EUserRole getRole(String token) {
        return EUserRole.valueOf((String) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("role"));
    }


    boolean validateToken(final String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new RuntimeException("Expired or invalid JWT token", e);
        }
    }
}
