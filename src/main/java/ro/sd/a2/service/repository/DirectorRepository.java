package ro.sd.a2.service.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.sd.a2.entity.Director;

@Repository
public interface DirectorRepository extends JpaRepository<Director,String> {
    Director findAllById (String id);
}
