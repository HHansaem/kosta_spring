package com.kosta.board.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.swing.border.Border;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.board.dao.BoardDao;
import com.kosta.board.dto.BFile;
import com.kosta.board.dto.Board;
import com.kosta.board.util.PageInfo;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDao boardDao;
	
	@Override
	public List<Board> boardListByPage(PageInfo pageInfo) throws Exception {
		//1. 페이지를 가져오고 없으면 페이지번호를 1로 한다.
		Integer page = pageInfo.getCurPage();
		
		//2. PageInfo 계산하여 실행하기
		int maxPage = (int)Math.ceil((double)boardDao.selectBoardCount()/10);
		int startPage = (page-1)/10*10+1; //1,11,21,31...
		int endPage = startPage+10-1;  //10,20,30...
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		pageInfo.setAllPage(maxPage);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
		
		
		//3. 해당 페이지에 해당하는 게시판 글 목록 조회
		int row = (page-1)*10;
		List<Board> boardList = boardDao.selectBoardList(row);
		
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
	public void boardModify(HttpServletRequest request) throws Exception {
		Board board = new Board();
		
		//1. 파일 업로드
//		String path = request.getServletContext().getRealPath("upload");
//	    int size = 10*1024*1024;
//	    MultipartRequest multi = new MultipartRequest(request, path, size, "utf-8", new DefaultFileRenamePolicy());
//
//	    //2. 업로드 파일이 있으면
//	    File file = multi.getFile("file");
//	    if(file != null) {
//	    	//2-1. 파일정보 모아서 BFile 객체 생성해 파일 테이블에 삽입
//	    	BFile bFile = new BFile();
//	    	bFile.setDirectory(path);
//	    	bFile.setName(file.getName());
//	    	bFile.setContenttype(multi.getContentType("file"));
//	        bFile.setSize(file.length());
//	        boardDao.insertFile(bFile);
//	        
//	        //2-2. 저장된 파일번호로 업로드한 파일명 변경
//	        File uploadFile = new File(path, file.getName());
//	        uploadFile.renameTo(new File(path, bFile.getNum()+""));  //+""를 통해 숫자를 문자열로 변환
//
//	        //2-3. 저장된 파일번호로 Board의 파일번호 세팅
//	        board.setFilenum(bFile.getNum());
//	    }
//	    
//		//3. 수정된 Board 정보를 파라미터에서 가져다가 Board객체 생성하여 Board 테이블 갱신
//	    board.setNum(Integer.parseInt(multi.getParameter("num")));
//	    board.setSubject(multi.getParameter("subject"));
//	    board.setContent(multi.getParameter("content"));
	    boardDao.updateBoard(board);
	}

}
