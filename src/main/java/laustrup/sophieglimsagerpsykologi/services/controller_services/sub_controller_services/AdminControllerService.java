package laustrup.sophieglimsagerpsykologi.services.controller_services.sub_controller_services;

import laustrup.sophieglimsagerpsykologi.models.dtos.AdminDTO;
import laustrup.sophieglimsagerpsykologi.services.controller_services.ControllerService;

import laustrup.sophieglimsagerpsykologi.services.entity_services.AdminService;
import org.springframework.http.ResponseEntity;

public class AdminControllerService extends ControllerService<AdminDTO> {

    private final AdminService _service = new AdminService();

    public ResponseEntity<AdminDTO> login(String password) {
        return entityContent(new AdminDTO(_service.get(password)));
    }
}
