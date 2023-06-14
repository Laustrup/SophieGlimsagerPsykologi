package laustrup.sophieglimsagerpsykologi.controllers.api;

import laustrup.sophieglimsagerpsykologi.models.FAQ;
import laustrup.sophieglimsagerpsykologi.models.dtos.AdminDTO;
import laustrup.sophieglimsagerpsykologi.models.dtos.FAQDTO;
import laustrup.sophieglimsagerpsykologi.services.controller_services.sub_controller_services.AdminControllerService;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {

    private final String _endpointDirectory = "/api/admin/";

    private final AdminControllerService _service = new AdminControllerService();

    @PostMapping(_endpointDirectory + "login/{password}")
    public ResponseEntity<AdminDTO> login(@PathVariable("password") String password) {
        return _service.login(password);
    }

    @PostMapping(value = _endpointDirectory + "faq", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FAQDTO[]> insert(@RequestBody FAQDTO dto) {
        return _service.insert(new FAQ(dto));
    }

    @GetMapping(_endpointDirectory + "faq")
    public ResponseEntity<FAQDTO[]> getFAQ() {
        return _service.getFAQ();
    }

    @DeleteMapping(value = _endpointDirectory + "faq", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FAQDTO[]> delete(@RequestBody FAQDTO dto) { return _service.delete(new FAQ(dto)); }
}
