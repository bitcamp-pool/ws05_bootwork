package board.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDao {

	@Autowired
	BoardDaoInter daoInter;
	
	// insert
	public void insertBoard(BoardDto dto) {
		
		// num 컬럼이 있으므로 insert가 아니라 update(수정)
		daoInter.save(dto);
	}
	
	// list 조회
	public List<BoardDto> getAllDatas(){
		
		// 최신글이 위로 올라오도록 정렬(desc)
		return daoInter.findAll(Sort.by(Sort.Direction.DESC, "num"));
	}
	
	// detail
	public BoardDto getData(Long num) {
		
		return daoInter.getById(num);
	}
	
	// update
	public void updateBoard(BoardDto dto) {
		
		// num 컬럼이 있으므로 insert가 아니라 update(수정)
		daoInter.save(dto);
	}
	
	// delete
	public void deleteBoard(Long num) {
		
		daoInter.deleteById(num);
	}
}













