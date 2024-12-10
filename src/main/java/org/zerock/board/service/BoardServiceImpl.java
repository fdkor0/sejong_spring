package org.zerock.board.service;



import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


import org.springframework.stereotype.Service;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.entity.Board;
import org.zerock.board.repository.BoardRepository;

import java.util.List;
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
                .map(entity -> entityToDto(entity))
                .collect(Collectors.toList());
    }

    private BoardDTO entityToDto(Board board) {
        return BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .writerEmail(board.getWriter().getEmail())
                .writerName(board.getWriter().getName())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .build();
    }

	
};
