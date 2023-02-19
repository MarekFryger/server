package server.generic;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jakarta.transaction.Transactional;

public abstract class GenericService<T extends GenericEntity> {

    private final GenericRepository<T> repository;

    protected GenericService(GenericRepository<T> repository) {
        this.repository = repository;
    }

    public Page<T> getPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<T> getAll() {
        return repository.findAll();
    }

    public T get(String id) {
        return repository.findById(id).orElseThrow();
    }

 
    public Map<String, T> getHistory(String id) {
       Map<String, T> result = new TreeMap<>();
        repository.findRevisions(id).getContent().forEach(
                item -> 
                    result.put(item.getMetadata().getRevisionType().name() + " " + item.getRequiredRevisionInstant(),
                            item.getEntity())
                );
        return result;

    }

    @Transactional
    public T update(T updated) {
        T dbDomain = get(updated.getId());
        return repository.save(dbDomain);
    }

    @Transactional
    public T create(T newDomain) {
        return repository.save(newDomain);
    }

    @Transactional
    public void delete(String id) {
        get(id);
        repository.deleteById(id);
    }
    @Transactional
    public T deactivate(T updated) {
        T dbDomain = get(updated.getId());
        dbDomain.setActive(false);
        return repository.save(dbDomain);
    }

}
