package appointmenthospital.authservice.controller;

import appointmenthospital.authservice.log.CustomLogger;
import appointmenthospital.authservice.model.dto.TransferRequest;
import appointmenthospital.authservice.model.entity.User;
import appointmenthospital.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final CustomLogger logger;
    private final UserService userService;
    @PostMapping(path ="/transfer", consumes = "application/json", produces = "application/json")
    @ResponseBody
 //   @ResponseStatus(HttpStatus.OK)
    public Boolean transfer(@RequestBody TransferRequest request, Principal principal) {
        return userService.transfer(principal,request);
    }
    @GetMapping("/user")
    public User transferForm(Principal principal) {
        return userService.get(principal.getName());
    }
}
