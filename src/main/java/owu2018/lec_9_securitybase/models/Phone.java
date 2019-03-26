package owu2018.lec_9_securitybase.models;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString(exclude = {"contact"})

public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    String number;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Contact contact;

//    public Phone(String number) {
//        this.number = number;
//    }
}
