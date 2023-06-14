package laustrup.sophieglimsagerpsykologi.services.controller_services.sub_controller_services;

import laustrup.sophieglimsagerpsykologi.models.FAQ;
import laustrup.sophieglimsagerpsykologi.models.dtos.AdminDTO;
import laustrup.sophieglimsagerpsykologi.models.dtos.FAQDTO;
import laustrup.sophieglimsagerpsykologi.services.controller_services.ControllerService;
import laustrup.sophieglimsagerpsykologi.services.entity_services.AdminService;
import laustrup.utilities.collections.lists.Liszt;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AdminControllerService extends ControllerService<AdminDTO> {

    private final AdminService _service = new AdminService();

    public ResponseEntity<AdminDTO> login(String password) {
        return entityContent(new AdminDTO(_service.get(password)));
    }

    public ResponseEntity<FAQDTO[]> insert(FAQ faq) {
        return convertFAQ(_service.insert(faq));
    }

    public ResponseEntity<FAQDTO[]> getFAQ() {
        return convertFAQ(_service.getFAQ());
    }

    public ResponseEntity<FAQDTO[]> delete(FAQ faq) {
        return convertFAQ(_service.deleteFAQ(faq));
    }

    private ResponseEntity<FAQDTO[]> convertFAQ(Liszt<FAQ> faq) {
        FAQDTO[] dtos = new FAQDTO[faq.size()];

        for (int i = 0; i < dtos.length;i++)
            dtos[i] = new FAQDTO(faq.get(i));

        return new ResponseEntity<>(dtos,dtos.length == 0 ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }
}
