package laustrup.sophieglimsagerpsykologi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    private final String _clientDirectory = "/", _adminDirectory = "/admin", _index = "index.html";

    @GetMapping(_clientDirectory) public String preset() { return "redirect:" + _clientDirectory + "welcome"; }
    @GetMapping(_clientDirectory+"welcome") public String welcome() { return _index; }
    @GetMapping(_clientDirectory+"about") public String about() { return _index; }

    @GetMapping(_adminDirectory) public String admin() { return _index; }
}
