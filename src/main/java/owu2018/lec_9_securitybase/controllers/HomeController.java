package owu2018.lec_9_securitybase.controllers;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import owu2018.lec_9_securitybase.dao.UserDAO;
import owu2018.lec_9_securitybase.models.Contact;
import owu2018.lec_9_securitybase.models.CustomUser;
import owu2018.lec_9_securitybase.models.Phone;
import owu2018.lec_9_securitybase.services.ContactService;
import owu2018.lec_9_securitybase.services.PhoneService;
import owu2018.lec_9_securitybase.services.editors.PhoneEditor;


import javax.validation.Valid;
import java.security.Principal;


@Controller
@AllArgsConstructor
public class HomeController {

    private ContactService contactService;
    private PhoneService phoneService;

    @GetMapping({"/", "/home"})
    public String home(Model model,
                       @AuthenticationPrincipal Authentication authentication,
                       @AuthenticationPrincipal Principal principal,
                       @AuthenticationPrincipal UserDetails userDetails) {

//        System.out.println("1" + " " + authentication.getName());
//        System.out.println("1" + " " + principal.getName());
//        System.out.println("1" + " " + userDetails.getUsername());
        model.addAttribute("contacts", contactService.findAll());
        model.addAttribute("contact", new Contact("test", "test@test.com"));
        model.addAttribute("xxx", "hello page");
        return "home";
    }

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/saveUser")
    public String saveUser(CustomUser customUser) {
        System.out.println(customUser);

        String password = customUser.getPassword();
        String encode = passwordEncoder.encode(password);
        customUser.setPassword(encode);

        userDAO.save(customUser);
        return "redirect:/home";
    }


    @PostMapping("/saveContact")
    public String saveContact(@Valid Contact contact, BindingResult bindingResult,
                              @RequestParam("picture") MultipartFile file) {
        if (bindingResult.hasErrors()) {
            return "home";
        }
        contactService.transferFile(file);
        contact.setAvatar(file.getOriginalFilename());
        System.out.println(contact.getPhoneList());
        contactService.save(contact);
        return "redirect:/";
    }

    @GetMapping("/details-{xxx}")
    public String contactDetails(@PathVariable("xxx") int id, Model model) {
        Contact one = contactService.getOne(id);
        model.addAttribute("contact", one);
        return "contactDetails";
    }

    @PostMapping("/update")
    public String updateContact(Contact contact) {
        contactService.save(contact);
        return "redirect:/";
    }

    @Autowired
    private PhoneEditor phoneDoctor;

    @InitBinder("contact")
    public void initBinder(WebDataBinder binder) {
        System.out.println("!!!!!!!!!!!!");
        binder.registerCustomEditor(Phone.class, phoneDoctor);
//        binder.registerCustomEditor(Phone.class,"phoneList", phoneDoctor);

    }


}
