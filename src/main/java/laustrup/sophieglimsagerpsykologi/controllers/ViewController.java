package laustrup.sophieglimsagerpsykologi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewController {

    private final String _clientDirectory = "/", _adminDirectory = "/admin", _index = "index.html";

    @GetMapping(_clientDirectory) public String preset() { return "redirect:" + _clientDirectory + "velkommen"; }
    @GetMapping(_clientDirectory+"velkommen") public String welcome() { return _index; }
    @GetMapping(_clientDirectory+"om_mig") public String about() { return _index; }
    @GetMapping(_clientDirectory+"priser_og_betaling") public String payments() { return _index; }
    @GetMapping(_clientDirectory+"kontakt") public String contact() { return _index; }
    @GetMapping(_clientDirectory+"behandlingsområder") public String fields() { return _index; }
    @GetMapping(_clientDirectory+"faq") public String faq() { return _index; }
    @GetMapping(_clientDirectory+"booking/afmeld_tider") public String booking() { return _index; }
    @GetMapping(_clientDirectory+"?start={_start}/?slut={_slut}") public String booking(
            @PathVariable String _slut, @PathVariable String _start) {
        return _index;
    }
    @GetMapping(_clientDirectory+"?start={_start}/?slut={_slut}/?klient={_client_id}") public String booking(
            @PathVariable String _slut, @PathVariable String _start, @PathVariable String _client_id) {
        return _index;
    }

    @GetMapping(_adminDirectory) public String admin() { return _index; }
}
