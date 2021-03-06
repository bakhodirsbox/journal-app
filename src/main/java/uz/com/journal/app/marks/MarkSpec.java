package uz.com.journal.app.marks;


import org.springframework.data.jpa.domain.Specification;
import uz.com.journal.app.attendance.Attendance;
import uz.com.journal.core.NonDeletableSpec;
import uz.com.platform.app.users.User;
import uz.com.platform.app.users.User_;
import uz.com.platform.app.utils.SpecUtils;

public abstract class MarkSpec {

    public static Specification<Mark> findByCriteria(MarkFetch fetch, MarkCriteria criteria, User currentUser) {
        return SpecUtils.and(
                NonDeletableSpec.byDeleted(false),

                criteria.getUuid() != null ? byUuid(criteria.getUuid()) : null
        );
    }
    private static Specification<Mark> byUuid(String uuid) {
        return uuid == null || uuid.isEmpty() ? null :
                (root, query, cb) -> cb.equal(root.get(Mark_.uuid), uuid);
    }
}
