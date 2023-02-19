package server.generic;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
@CrossOrigin(origins = "*", maxAge = 3600)
public abstract class GenericController<T extends GenericEntity> {

    private final GenericService<T> service;

    protected GenericController(GenericRepository<T> repository) {
        this.service = new GenericService<T>(repository) {
        };
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Page<T>> getPage(Pageable pageable) {
        return ResponseEntity.ok(service.getPage(pageable));
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<T>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<T> getOne(@PathVariable String id) {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping("/history/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Map<String, T>> gethistory(@PathVariable String id) {
        return ResponseEntity.ok(service.getHistory(id));
    }

    @PutMapping("")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ResponseEntity<T> update(@RequestBody T updated) {
        return ResponseEntity.ok(service.update(updated));
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ResponseEntity<T> create(@RequestBody T created) {
        return ResponseEntity.ok(service.create(created));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ResponseEntity<String> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok("Ok");
    }
    @PutMapping("/deactivate")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ResponseEntity<T> deactivate(@RequestBody T updated) {
        return ResponseEntity.ok(service.deactivate(updated));
    }
}
