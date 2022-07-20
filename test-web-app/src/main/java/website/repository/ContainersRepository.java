package website.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import website.model.Container;

@Repository
public interface ContainersRepository extends CrudRepository<Container, Long> {

}
