package owu2018.lec_9_securitybase.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString(exclude = {"phoneList"})

public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    @Size(min = 3, max = 6, message = "hello {foo.message}")
    String name;
    String email;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "contact")
    List<Phone> phoneList = new ArrayList<>();
    private String avatar;

    public Contact(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
