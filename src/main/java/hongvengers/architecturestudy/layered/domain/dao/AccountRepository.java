package hongvengers.architecturestudy.layered.domain.dao;

import hongvengers.architecturestudy.layered.domain.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
}
