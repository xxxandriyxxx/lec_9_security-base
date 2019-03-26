package owu2018.lec_9_securitybase.services;

import org.springframework.stereotype.Service;
import owu2018.lec_9_securitybase.dao.PhoneDAO;
import owu2018.lec_9_securitybase.models.Phone;

@Service
public class PhoneService {

    private PhoneDAO phoneDAO;

    public PhoneService(PhoneDAO phoneDAO) {
        this.phoneDAO = phoneDAO;
    }

    public void save(Phone phone) {
        phoneDAO.save(phone);
    }

}
