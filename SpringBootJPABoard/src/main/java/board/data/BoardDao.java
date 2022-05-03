package board.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDao {

	@Autowired
	BoardDaoInter daoInter;
	
	// insert
	public void insertBoard(BoardDto dto) {
		daoInter.save(dto);
	}
}
