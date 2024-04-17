package com.kosta.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.board.dao.BoardDao;
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
	public void boardWrite(HttpServletRequest request) throws Exception {
		Board board = new Board();
	      
	    // 1. 파일 업로드 
	    // 1-1. 업로드할 경로 설정 및 파일 최대 크기 설정
//	    String path = request.getServletContext().getRealPath("upload");
//	    int size = 10*1024*1024;
//	    // 1-2. 설정할 경로에 파일 업로드 
//	    MultipartRequest multi = new MultipartRequest(request, path, size, "utf-8", new DefaultFileRenamePolicy());
//	      
//	    // 2. 업로드 파일이 있을 경우
//	    File file = multi.getFile("file");  //"file"은 jsp input태그의 name
//	    if(file!=null) {
//	       // 2-1. 파일 정보를 BFile 객체에 담아 File 테이블에 삽입
//	       BFile bFile = new BFile();
//	       bFile.setDirectory(path);
//	       bFile.setContenttype(multi.getContentType("file"));
////	       getFilesystemName : 동일한 이름이 있으면 파일명에 숫자를 붙여주는데 그것을 가져옴(즉, 최종 파일명)
//	       bFile.setName(multi.getFilesystemName("file"));
//	       bFile.setSize(file.length());
//	       boardDao.insertFile(bFile);
//	         
//	       // 2-2. 파일 테이블에서 자동 생성된 파일번호로 업로드한 파일이름 변경
//	       File uploadFile = new File(path,file.getName());
//	       uploadFile.renameTo(new File(path,bFile.getNum()+""));  //+""를 통해 숫자를 문자열로 변환
//	       
//	       // 2-3. 파일 테이블에서 자동 생성된 파일번호로 Board의 파일 번호 셋팅
//	       board.setFilenum(bFile.getNum());
//	    }
//	    // 3. 파라미터에서 파일 이외의 정보 가져와 Board 객체에 담아 Board 테이블에 삽입  
//	    board.setSubject(multi.getParameter("subject"));
//	    board.setContent(multi.getParameter("content"));
//	    board.setWriter(multi.getParameter("writer"));
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
