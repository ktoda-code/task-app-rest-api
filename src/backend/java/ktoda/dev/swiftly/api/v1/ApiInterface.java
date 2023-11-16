package ktoda.dev.swiftly.api.v1;

import org.springframework.http.ResponseEntity;

/**
 * Generic interface for Restful services with basic methods for saving, deleting, finding and updating.
 *
 * @param <CE> Create request entity - Data Transfer Object
 * @param <UE> Update request entity - Data Transfer Object
 * @param <ID> Data type of the id of the entity
 */
public interface ApiInterface<CE, UE, ID> {
    ResponseEntity<?> findAll();

    ResponseEntity<?> findById(ID id);

    ResponseEntity<?> save(CE entity);

    ResponseEntity<?> update(UE entity);

    ResponseEntity<?> delete(ID id);
}
