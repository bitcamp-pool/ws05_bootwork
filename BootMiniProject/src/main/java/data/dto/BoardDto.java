package data.dto;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("board")
@Data
public class BoardDto {
	
	private int num;
	private String id;
	private String name;
	private String subject;
	private String content;
	private String photos;
	private Timestamp writeday;
	private int readcount;
	private int chu;
	private int totalchu;
	private int reg;
	private int restep;
	private int relevel;
	
	// 댓글개수
	private int acount;
}
