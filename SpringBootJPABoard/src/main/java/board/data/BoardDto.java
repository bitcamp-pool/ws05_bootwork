package board.data;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Table(name = "board")
@Data
public class BoardDto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long num;
	
	@Column(name = "writer", updatable = false)  // 수정 안되게...
	private String writer;
	
	@Column(name = "subject")
	private String subject;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "photo")
	private String photo;
	
	@CreationTimestamp
	@Column(name = "writeday", updatable = false) // 수정 안되게...
	private Timestamp writeday;
	
}
