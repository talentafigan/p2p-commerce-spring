package p2p.commerce.commerceapi.configuration.web;

import org.springframework.web.bind.annotation.*;
import p2p.commerce.commerceapi.configuration.exception.BussinesException;

import java.util.Date;

@RestController
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public String home() {
        return "Server p2p commerce running at " + new Date();
    }
}
