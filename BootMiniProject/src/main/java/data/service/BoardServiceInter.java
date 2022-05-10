package data.service;

import java.util.List;

import data.dto.BoardDto;


public interface BoardServiceInter {

	public int getMaxNum();
	public void updateReStep(int reg, int restep);
	public int getTotalCount();
	public List<BoardDto> getList(int start, int perpage);
	public void insertBoard(BoardDto dto);
	public void updateReadCount(int num);
	public BoardDto getData(int num);
}
