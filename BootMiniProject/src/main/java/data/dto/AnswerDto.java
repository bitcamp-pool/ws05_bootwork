package data.dto;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;


@Alias("answer")
@Data
public class AnswerDto {
	
	private int idx;
	private int num;
	private String name;
	private String id;
	private String message;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
	private Timestamp writeday;
}
