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
	
	// list 조회
	public List<MyCarDto> getAllCars() {
		
		List<MyCarDto> list = daoInter.findAll();
		
		return list;
	}
	
	// 수정폼에 객체 전달
	public MyCarDto getMyCar(Long num) {
		
		return daoInter.getById(num);
	}
	
	// 수정
	public void updateCar(MyCarDto dto) {
		
		/* num 이 있을 경우 update
		 * 없으면 insert
		 */
		daoInter.save(dto);
	}
	
	// 삭제
	public void deleteCar(Long num) {
		
		daoInter.deleteById(num);
	}
}





















