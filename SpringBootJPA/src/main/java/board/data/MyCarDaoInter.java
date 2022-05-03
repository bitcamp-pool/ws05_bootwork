package board.data;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

/* 2개의 인터페이스가 있다.
 * CrudRepository 
 * JpaRepository
 * CRUD 기능구현이 되어 있음.
 */

public interface MyCarDaoInter extends JpaRepository<MyCarDto, Long>{
	
	
}
