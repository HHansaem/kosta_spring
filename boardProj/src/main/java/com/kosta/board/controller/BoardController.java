package com.kosta.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	
	@GetMapping("/boardlist")
//	@RequestMapping(value = "/makeAccount", method = RequestMethod.GET 랑 같음!
	public String boardlist() {
		return "boardlist";
	}
}
