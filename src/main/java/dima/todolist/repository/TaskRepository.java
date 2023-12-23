package dima.todolist.repository;

import dima.todolist.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(value = "select * from task where user_id = :userId", nativeQuery = true)
    public Page<Task> findAll(@Param("userId") Long userId, Pageable pageable);

}
