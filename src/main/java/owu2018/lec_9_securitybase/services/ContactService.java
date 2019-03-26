package owu2018.lec_9_securitybase.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import owu2018.lec_9_securitybase.dao.ContactDAO;
import owu2018.lec_9_securitybase.models.Contact;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ContactService {

    private ContactDAO contactDAO;

    public ContactService(ContactDAO contactDAO) {
        this.contactDAO = contactDAO;
    }

    public void save(Contact contact) {
        if (contact != null) {
            contactDAO.save(contact);
        }
    }

    public List<Contact> findAll() {
        return contactDAO.findAll();
    }

    public List<Contact> findAllByName(String name) {
        if (name.isEmpty()) {
            return contactDAO.findAllByName(name);
        }
        return null;
    }

    public Contact getOne(int id) {
        return contactDAO.getOne(id);
    }

    public void transferFile(MultipartFile file) {
        String pathToFolder = System.getProperty("user.home") + File.separator + "Pictures" + File.separator;
        try {
            file.transferTo(new File(pathToFolder + file.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
