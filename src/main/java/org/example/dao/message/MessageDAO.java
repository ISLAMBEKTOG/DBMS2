package org.example.dao.message;

import org.example.model.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageDAO extends CrudRepository<Message, String> {
}
