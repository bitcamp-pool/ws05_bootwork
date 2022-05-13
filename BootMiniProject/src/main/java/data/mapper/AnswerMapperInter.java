package data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import data.dto.AnswerDto;

@Mapper
public interface AnswerMapperInter {

	public void insertAnswer(AnswerDto dto);
	public List<AnswerDto> getAnswerList(int num);
	public void deleteAnswer(int dex);
}
