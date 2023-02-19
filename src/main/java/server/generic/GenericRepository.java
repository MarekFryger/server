package server.generic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
@Repository
public interface GenericRepository<T extends GenericEntity>
        extends JpaRepository<T, String>, RevisionRepository<T, String, Long> {
}
