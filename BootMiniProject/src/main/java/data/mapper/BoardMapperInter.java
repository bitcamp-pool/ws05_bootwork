package data.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import data.dto.BoardDto;

@Mapper
public interface BoardMapperInter {
	
	public int getMaxNum();
	public void updateReStep(Map<String, Integer> map);
	public int getTotalCount();
	public List<BoardDto> getList(Map<String, Integer> map);
	public void insertBoard(BoardDto dto);
	public void updateReadCount(int num);
	public BoardDto getData(int num);
	public void updateChu(Map<String, Integer> map);
	public void updateBoard(BoardDto dto);
	public void deleteBoard(int num);
	
	
}
