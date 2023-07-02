package hongvengers.architecturestudy.hexagonal.account.adapter.out.persistence;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Account")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountJpaEntity {
    @Id
    @GeneratedValue    private Long id;
}
