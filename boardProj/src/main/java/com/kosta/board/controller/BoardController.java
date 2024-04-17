package com.kosta.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.board.dto.Board;
import com.kosta.board.service.BoardService;
import com.kosta.board.util.PageInfo;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/boardlist")
//	@RequestMapping(value = "/makeAccount", method = RequestMethod.GET 랑 같음!
	public ModelAndView boardlist(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page) {
		//페이지 파라미터 안 가져왔을 때 -> (required = false)꼭 필요한 건 아니라 에러나지 않고 (defaultValue)기본값을 1페이지로
		ModelAndView mav = new ModelAndView();
		try {
			PageInfo pageInfo = new PageInfo();
			pageInfo.setCurPage(page);
			List<Board> boardList = boardService.boardListByPage(pageInfo);
			mav.addObject("pageInfo", pageInfo);
			mav.addObject("boardList", boardList);
			mav.setViewName("boardlist");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", "게시글 목록 조회 실패");
			mav.setViewName("error");
		}
		return mav;
	}
	
	@GetMapping("/boardwrite")
	public String boardWrite() {
		return "writeform";
	}
}
