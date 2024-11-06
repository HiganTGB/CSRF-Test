package appointmenthospital.csrfform.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = {"/", "/home"})
    public String home() {
        return "home";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
    @RequestMapping("/transfer")
    public String transferForm(Model model) {
        model.addAttribute("xss","<script>alert('XSS')</script>");
        return "money-transfer";
    }
    @PostMapping("/transfer")
    public String transfer(@RequestParam String recipientUsername, @RequestParam Long amount, Principal principal) {
        logger.info("Test");
        userService.transfer(principal,recipientUsername,amount);
        return "transfer-success"; //
    }
    @GetMapping("/transfer2")
    public String transfer2(@RequestParam String recipientUsername, @RequestParam Long amount, Principal principal) {
        logger.info("Test");
        userService.transfer(principal,recipientUsername,amount);
        return "transfer-success"; //
    }
    @RequestMapping("/login")
    public String login() {
        return "login";

    }
}