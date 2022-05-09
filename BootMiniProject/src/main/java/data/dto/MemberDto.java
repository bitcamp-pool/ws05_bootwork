package data.dto;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("member") //data.dto.MemberDto 를 "member" 로 줄여 쓰겠다.
public class MemberDto {
	
	private String num;
	private String name;
	private String id;
	private String pass;
	private String hp;
	private String email;
	private String addr;
	private Timestamp gaipday;
	
}
