package board.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/* 자동으로 Bean 등록
 * Dao : Repository
 * 
 */
@Repository
public class MyCarDao {

	@Autowired
	MyCarDaoInter daoInter;
	
	// Insert 생성
	public void insertCar(MyCarDto dto) {
		
		/* ID 유무에 따라 자동으로
		 * insert/update
		 */
		daoInter.save(dto);
	}
	
	// Select 조회
	public List<MyCarDto> getlistAll() {
		
		return daoInter.findAll();
	}
}
