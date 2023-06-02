package laustrup.sophieglimsagerpsykologi.controllers.api;

import laustrup.sophieglimsagerpsykologi.models.dtos.AdminDTO;
import laustrup.sophieglimsagerpsykologi.service.controller_services.sub_controller_services.AdminControllerService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    private final String _endpointDirectory = "/api/admin/";

    private final AdminControllerService _service = new AdminControllerService();

    @PostMapping(_endpointDirectory + "login/{password}")
    public ResponseEntity<AdminDTO> login(@PathVariable("password") String password) {
        return _service.login(password);
    }
}
