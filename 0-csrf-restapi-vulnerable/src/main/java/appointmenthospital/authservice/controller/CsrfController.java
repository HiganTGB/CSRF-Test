package appointmenthospital.authservice.controller;

import appointmenthospital.authservice.auth.AuthenticationController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController

public class CsrfController {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    @GetMapping("/csrf")
    @CrossOrigin("http://localhost:13000")
    public CsrfToken csrf(CsrfToken csrfToken,HttpServletResponse response) {
        logger.info(csrfToken.getToken());
        response.setHeader(csrfToken.getHeaderName(), csrfToken.getToken());
        return csrfToken;
    }

}