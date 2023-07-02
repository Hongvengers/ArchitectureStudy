package hongvengers.architecturestudy.layered.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
public class AccountEntity {
    @Id
    private Long id;
}
