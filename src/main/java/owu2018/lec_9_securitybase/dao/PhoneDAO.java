package owu2018.lec_9_securitybase.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import owu2018.lec_9_securitybase.models.Phone;


public interface PhoneDAO extends JpaRepository<Phone,Integer> {
}
