package com.kosta.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosta.board.dto.Board;
import com.kosta.board.service.BoardService;
import com.kosta.board.util.PageInfo;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private HttpSession session;

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
	
	@PostMapping("/boardwrite")
	public String boardWrite(@ModelAttribute Board board, @RequestPart(value = "file", required = false) MultipartFile file) {
		try {
			boardService.boardWrite(board, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/boardlist";  //request 주고받지 않고 redirect
	}
	
	@GetMapping("/boarddetail")
	public ModelAndView boardDetail(Integer num) {
		ModelAndView mav = new ModelAndView();
		try {
			Board board = boardService.boardDetail(num);
			mav.addObject("board", board);
			//like
			String userId = (String)session.getAttribute("user");
			if(userId != null) {
				Boolean like = boardService.isSelectBoardLike(userId, num);
				mav.addObject("like", String.valueOf(like));
			}
			mav.setViewName("boarddetail");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", "게시글 상세조회 실패");
			mav.setViewName("error");
		}
		return mav;
	}
	
	@GetMapping("/image")
	public void imageView(Integer num, HttpServletResponse response) {
		try {
			String path = "C:/hhs/spring_upload";
			FileInputStream fis = new FileInputStream(new File(path,num+""));
			FileCopyUtils.copy(fis, response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/boardmodify")
	public ModelAndView boardModify(Integer num) {
		ModelAndView mav = new ModelAndView();
		try {
			Board board = boardService.boardDetail(num);
			mav.addObject("board", board);
			mav.setViewName("modifyform");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", "게시글 수정 조회 오류");
			mav.setViewName("error");
		}
		return mav;
	}
	
	@PostMapping("/boardmodify")
	public ModelAndView boardModify(@ModelAttribute Board board, @RequestPart(value = "file", required = false) MultipartFile file) {
		ModelAndView mav = new ModelAndView();
		try {
			boardService.boardModify(board, file);
			mav.setViewName("redirect:/boarddetail?num="+board.getNum());
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", "게시글 수정 오류");
			mav.setViewName("error");
		}
		return mav;
	}
	
	@ResponseBody  //return되는 게 view가 아니라 data
	@PostMapping("/boardlike")
	public String boardLike(@RequestParam("like")String like) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			//json 형태로 문자열을 파싱하여 map에 넣어준다
			Map<String, String> param = mapper.readValue(like, Map.class);
			//map에 있는 데이터를 json형태의 문자열로 변환해준다
			//String json = mapper.writeValueAsString(map)
			Boolean checkLike = boardService.checkBoardLike((String)param.get("memberId"), 
										Integer.parseInt(param.get("boardNum")));
			return String.valueOf(checkLike);
		} catch (Exception e) {
			e.printStackTrace();
			return "none";
		}
	}
	
}
