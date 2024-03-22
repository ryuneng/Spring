package com.example.reply; // 20240322 Day23

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.user.User;

@Repository
public interface ReplyVoterRepository extends JpaRepository<ReplyVoter, Long> {

	Optional<ReplyVoter> findByReplyAndUser(Reply reply, User user);
}
