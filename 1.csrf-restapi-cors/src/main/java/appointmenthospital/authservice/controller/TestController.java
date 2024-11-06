package appointmenthospital.authservice.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class TestController {

    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public String get(HttpServletRequest request, HttpServletResponse response) {
        return "GET Received";
    }
    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping
    public String post(HttpServletRequest request, HttpServletResponse response) {
        return "POST Received";
    }
}