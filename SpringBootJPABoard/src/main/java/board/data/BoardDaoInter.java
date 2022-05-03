package board.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardDaoInter extends JpaRepository<BoardDto, Long>{

}
