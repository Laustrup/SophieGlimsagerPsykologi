package laustrup.sophieglimsagerpsykologi.service.controller_services;

import laustrup.utilities.collections.lists.Liszt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class ControllerService<E> {

    /**
     * Will create a ResponseEntity with status of whether the content is null or not.
     * @param element The E element that is either null or not and should be returned.
     * @return The created ResponseEntity of an E element.
     */
    protected ResponseEntity<E> entityContent(E element) {
        if (element != null)
            return new ResponseEntity<>(element, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    /**
     * Will create a ResponseEntity with status of whether the content is null or not.
     * @param elements The E elements that is either null or not and should be returned.
     * @return The created ResponseEntity of E elements.
     */
    protected ResponseEntity<E[]> entityContent(Liszt<Object> elements) {
        if (elements != null)
            return new ResponseEntity<>(toDTOs(elements.get_data()), HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    /**
     * Will create a ResponseEntity with status of whether the content is null or not.
     * @param elements The E elements that is either null or not and should be returned.
     * @return The created ResponseEntity of E elements.
     */
    protected ResponseEntity<E[]> entityContent(E[] elements) {
        if (elements != null)
            return new ResponseEntity<>(elements, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    protected E[] toDTOs(Object[] elements) {
        E[] dtos = (E[]) new Object[elements.length];

        for (int i = 0; i < dtos.length; i++)
            dtos[i] = (E) elements[i];

        return dtos;
    }
}
