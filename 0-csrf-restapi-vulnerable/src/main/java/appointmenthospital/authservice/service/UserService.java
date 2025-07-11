package appointmenthospital.authservice.service;

import appointmenthospital.authservice.model.dto.TransferRequest;
import appointmenthospital.authservice.model.dto.UserDTO;
import appointmenthospital.authservice.model.entity.User;
import appointmenthospital.authservice.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final Type pageType = new TypeToken<Page<UserDTO>>(){}.getType();
    @Value("${application.security.password.default-prefix}")
    private long passwordPrefix;
   @PostConstruct
    public void initUser()
    {
        var alice=User.builder().username("Alice").password(passwordEncoder.encode("alice")).money(100000L).build();
        var bob=User.builder().username("Bob").password(passwordEncoder.encode("bob")).money(0L).build();
        var hacker=User.builder().username("Hacker").password(passwordEncoder.encode("hacker")).money(0L).build();
        userRepository.save(alice);
        userRepository.save(bob);
        userRepository.save(hacker);
    }
    public Boolean transfer(Principal send, TransferRequest request)
    {
        var user = (User) ((UsernamePasswordAuthenticationToken) send).getPrincipal();
        var sender= userRepository.findFirstByUsername(user.getUsername());
        var receiver= userRepository.findFirstByUsername(request.getReceiver());
        sender.setMoney(sender.getMoney()-request.getAmount());
        receiver.setMoney(receiver.getMoney()+request.getAmount());
        userRepository.save(sender);
        userRepository.save(receiver);
        return true;
    }
    public User get(String username)
    {
        return userRepository.findFirstByUsername(username);
    }

}