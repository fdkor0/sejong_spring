package org.zerock.board.service;



import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


import org.springframework.stereotype.Service;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Member;
import org.zerock.board.repository.BoardRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService{

    private final BoardRepository repository;


    @Override
    public Long register(BoardDTO dto) {

        log.info(dto);

        Board board  = dtoToEntity(dto);

        repository.save(board);

        return board.getBno();
    }


    @Override
    public List<BoardDTO> getList() {
        List<Board> entities = repository.findAll();
        return entities.stream()
                .map(board -> entityToDTO(board, board.getWriter(), 0L))
                .collect(Collectors.toList());
    }

    @Override
    public BoardDTO getView(Long bno) {
        Board board = repository.findById(bno).orElseThrow(() -> new IllegalArgumentException("Invalid board ID"));

        Member writer = board.getWriter();

        return entityToDTO(board, writer, 0L);
    }


	
};
