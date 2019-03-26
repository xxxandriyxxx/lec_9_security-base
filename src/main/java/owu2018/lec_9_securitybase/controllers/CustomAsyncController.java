package owu2018.lec_9_securitybase.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import owu2018.lec_9_securitybase.models.Contact;
import owu2018.lec_9_securitybase.services.ContactService;
import owu2018.lec_9_securitybase.services.EmailService;


import javax.mail.MessagingException;
import java.util.List;

@RestController
public class CustomAsyncController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/saveAsync")
    public List<Contact> saveAsync(@RequestBody Contact contact){
        contactService.save(contact);
        System.out.println(contact);
        System.out.println("react");
        return contactService.findAll();
    }



    @Autowired
    private EmailService emailService;

    @PostMapping("/upload")
    public @ResponseBody
    String uploadAjax(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam MultipartFile image
    ) throws MessagingException {
        Contact contact = new Contact(name, email);
        contact.setAvatar(image.getOriginalFilename());
        contactService.transferFile(image);
        contactService.save(contact);

        emailService.sendEmail(email, image);

        return "ok!";
    }





}
