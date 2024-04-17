package com.kosta.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.kosta.board.dto.Board;
import com.kosta.board.util.PageInfo;

public interface BoardService {
	List<Board> boardListByPage(PageInfo pageInfo) throws Exception;
	Board boardDetail(Integer num) throws Exception;
	void boardWrite(Board board, MultipartFile file) throws Exception;
	void boardModify(HttpServletRequest request) throws Exception;
}
