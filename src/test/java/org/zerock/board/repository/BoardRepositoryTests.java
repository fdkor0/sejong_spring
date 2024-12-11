package org.zerock.board.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Member;
import org.zerock.board.service.BoardService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@EnableJpaAuditing
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

	private BoardService boardService;

	/*
	 * @Test public void insertBoard() {
	 * 
	 * IntStream.rangeClosed(1,100).forEach(i -> {
	 * 
	 * Member member = Member.builder().email("user"+i +"@bbb.com").build();
	 * 
	 * Board board = Board.builder() .title("Title..."+i) .content("Content...." +
	 * i) .writer(member) .build();
	 * 
	 * boardRepository.save(board);
	 * 
	 * });
	 * 
	 * }
	 */

	// 게시글 목록 조회
	@Test
	void testGetList() {
		// Given
		List<Board> mockBoardList = Arrays.asList(
				new Board(1L, "Title 1", "Content 1", "Writer 1", "email1@example.com", LocalDateTime.now()),
				new Board(2L, "Title 2", "Content 2", "Writer 2", "email2@example.com", LocalDateTime.now())
		);
		when(boardRepository.findAll()).thenReturn(mockBoardList);

		// When
		List<BoardDTO> boardDTOList = boardService.getList();

		// Then
		assertEquals(2, boardDTOList.size());
		assertEquals("Title 1", boardDTOList.get(0).getTitle());
		assertEquals("Content 2", boardDTOList.get(1).getContent());
		//verify(boardRepository, times(1)).findAll();
	}


    @Transactional
    @Test
    public void testRead1() {
    	//조인명령을 활용한다.
        Optional<Board> result = boardRepository.findById(101L); //데이터베이스에 존재하는 번호

        Board board = result.get();

        System.out.println("testRead1()..............");
        System.out.println(board);
        System.out.println(board.getWriter());

    }



    
}
