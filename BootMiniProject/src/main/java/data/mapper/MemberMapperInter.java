package data.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import data.dto.MemberDto;

@Mapper
public interface MemberMapperInter {
	
	public int getTotalCount();
	public void insertMember(MemberDto dto);
	public int getSearchId(String id);
	public List<MemberDto> getAllMembers();
	public void deleteMember(String num);
	public String getSearchName(String id);
	public int login(Map<String, String> map);
}
