package ktoda.dev.swiftly.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import ktoda.dev.swiftly.model.Tag;
import ktoda.dev.swiftly.model.Task;
import ktoda.dev.swiftly.repository.TaskCustomRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TaskCustomRepositoryImpl implements TaskCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Task> findAllByTags(Set<Tag> tags) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Task> query = cb.createQuery(Task.class);
        Root<Task> task = query.from(Task.class);

        Path<Tag> tagPath = task.get("tags");

        List<Predicate> predicates = new ArrayList<>();
        for (Tag tag : tags) {
            predicates.add(cb.equal(tagPath.get("name"), tag.getName()));
        }
        query.select(task)
                .where(cb.or(predicates.toArray(new Predicate[predicates.size()])));

        return entityManager.createQuery(query).getResultList();
    }
}
