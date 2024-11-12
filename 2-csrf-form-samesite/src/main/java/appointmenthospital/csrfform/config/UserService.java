package appointmenthospital.csrfform.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostConstruct
    private void init()
    {
        var alice=User.builder().username("Alice").password(passwordEncoder.encode("alice")).money(100000L).build();
        var bob=User.builder().username("Bob").password(passwordEncoder.encode("bob")).money(0L).build();
        var hacker=User.builder().username("Hacker").password(passwordEncoder.encode("hacker")).money(0L).build();
        userRepository.save(alice);
        userRepository.save(bob);
        userRepository.save(hacker);
    }
    public Boolean transfer(Principal send,String receiver_username,Long money)
    {
        var user = (User) ((UsernamePasswordAuthenticationToken) send).getPrincipal();
        User sender= userRepository.findFirstByUsername(user.getUsername());
        User receiver=userRepository.findFirstByUsername(receiver_username);
        sender.setMoney(sender.getMoney()-money);
        receiver.setMoney(receiver.getMoney()+money);
        userRepository.save(sender);
        userRepository.save(receiver);
        return true;
    }
    public User get(String username)
    {
        return userRepository.findFirstByUsername(username);
    }
}
