package javaEE.springboot.springbootdemo.repositories;

import javaEE.springboot.springbootdemo.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
@Transactional
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByItemId(Long id);
    List<Comment> findAllByAuthorId(Long id);
    List<Comment> findAllByItemIdAndAuthorId(Long item_id, Long author_id);
    List<Comment> findAllByItemIdOrderByAddedDateDesc(Long id);
}
