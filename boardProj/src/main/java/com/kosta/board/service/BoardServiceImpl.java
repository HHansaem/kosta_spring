package com.kosta.board.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.swing.border.Border;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.board.dao.BoardDao;
import com.kosta.board.dao.BoardLikeDao;
import com.kosta.board.dto.BFile;
import com.kosta.board.dto.Board;
import com.kosta.board.util.PageInfo;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDao boardDao;
	
	@Autowired
	private BoardLikeDao boardLikeDao;
	
	@Override
	public List<Board> boardListByPage(PageInfo pageInfo, String type, String word) throws Exception {
		//1. 페이지를 가져오고 없으면 페이지번호를 1로 한다.
		Integer page = pageInfo.getCurPage();
		int row = (page-1)*10;  //시작 offset 계산
		List<Board> boardList = null;
		
		//2. PageInfo 계산하여 실행하기
		int maxPage = 1;
		if(type != null && word != null) {  //검색 목록 조회
			maxPage = (int)Math.ceil((double)boardDao.selectBoardSearchCount(type, word)/10);
			boardList = boardDao.selectBoardSearchList(row, type, word);
		} else {  //전체 목록 조회
			maxPage = (int)Math.ceil((double)boardDao.selectBoardCount()/10);
			boardList = boardDao.selectBoardList(row);
		}
		int startPage = (page-1)/10*10+1; //1,11,21,31...
		int endPage = startPage+10-1;  //10,20,30...
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		pageInfo.setAllPage(maxPage);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
		
		return boardList;
	}

	@Override
	public Board boardDetail(Integer num) throws Exception {
		boardDao.updateBrdViewCnt(num);
	    return boardDao.selectBoard(num);
	}
	
	@Override
	public void boardWrite(Board board, MultipartFile file) throws Exception {
		// MultipartFile: 브라우저에서 가져온 파일의 모든 정보 담고있음
		// 1. 파일업로드
		if(file != null && !file.isEmpty()) {
			String path = "C:/hhs/spring_upload";
			BFile bFile = new BFile();
			// DB에 저장
			bFile.setDirectory(path);
			bFile.setName(file.getOriginalFilename());;
			bFile.setSize(file.getSize());
			bFile.setContenttype(file.getContentType());
			boardDao.insertFile(bFile);  // 파일정보 테이블에 삽입
			
			// file upload
			File upFile = new File(path, bFile.getNum()+"");
			file.transferTo(upFile);
			board.setFilenum(bFile.getNum());
		}
		// 2. 게시글 테이블에 삽입
		boardDao.insertBoard(board);
	}

	@Override
	public void boardModify(Board board, MultipartFile file) throws Exception {
		// MultipartFile: 브라우저에서 가져온 파일의 모든 정보 담고있음
		Integer beforeFileNum = null;
		String path = "C:/hhs/spring_upload";
		// 1. 파일업로드
		if(file != null && !file.isEmpty()) {
			//기존 파일정보와 파일 삭제
			beforeFileNum = boardDetail(board.getNum()).getFilenum();
			
			//새 파일 업로드 & 새 파일정보 삽입
			BFile bFile = new BFile();
			bFile.setDirectory(path);
			bFile.setName(file.getOriginalFilename());;
			bFile.setSize(file.getSize());
			bFile.setContenttype(file.getContentType());
			boardDao.insertFile(bFile);  //파일정보 테이블에 삽입
			
			//file upload
			File upFile = new File(path, bFile.getNum()+"");
			file.transferTo(upFile);
			board.setFilenum(bFile.getNum());
		}
		// 2. 수정된 Board 정보를 파라미터에서 가져다가 Board 객체 생성하여 Board 테이블 갱신
		boardDao.updateBoard(board);
		if(beforeFileNum != null) {
			boardDao.deleteFile(beforeFileNum);
			File beforeFile = new File(path, beforeFileNum+"");
			beforeFile.delete();
		}
	}
	
	@Override
	public boolean isSelectBoardLike(String memberId, Integer boardNum) throws Exception {
		Integer num = boardLikeDao.selectBoardLike(memberId, boardNum);
		//하트가 눌려져 있지 않으면 false 리턴
		return num != null;
	}

	@Override
	public boolean checkBoardLike(String memberId, Integer boardNum) throws Exception {
		boolean isBoardLike = isSelectBoardLike(memberId, boardNum);
		if(isBoardLike) {
			boardLikeDao.deleteBoardLike(memberId, boardNum);
		} else {
			boardLikeDao.insertBoardLike(memberId, boardNum);
		}
		return !isBoardLike;
	}

}
