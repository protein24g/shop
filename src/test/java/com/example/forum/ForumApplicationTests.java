package com.example.forum;

import com.example.forum.board.comment.entity.Comment;
import com.example.forum.board.comment.repository.CommentRepository;
import com.example.forum.board.freeboard.entity.Board;
import com.example.forum.board.freeboard.repository.BoardRepository;
import com.example.forum.board.freeboard.service.BoardService;
import com.example.forum.user.entity.User;
import com.example.forum.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class ForumApplicationTests {
	@Autowired
	UserRepository userRepository;

	@Autowired
	BoardRepository boardRepository;

	@Autowired
	CommentRepository commentRepository;

	@Autowired
	BoardService boardService;

	@Test
	public void test0() { // 고유 id n번 유저의 글 작성
		Long n = 1L;
		User user = userRepository.findById(n).orElse(null);

		for(int i = 0; i < 100; i++){
			boardRepository.save(Board.builder()
					.title("작성글 테스트" + Integer.toString(i + 1))
					.content("bbbbbbbbdbbb")
					.createDate(LocalDateTime.now())
					.user(user)
					.view(0)
					.build());
		}
	}

	@Test
	public void test1(){ // 고유 id n번 유저의 댓글 작성
		User user = userRepository.findById(1L).orElse(null);
		Board board = boardRepository.findById(Long.valueOf(256)).orElse(null);

		commentRepository.save(Comment.builder()
			.user(user)
			.board(board)
			.content("테스트 댓글")
			.createDate(LocalDateTime.now())
			.build()
		);
	}

}