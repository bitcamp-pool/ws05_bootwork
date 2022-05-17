package data.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("shop")
@Data
public class ShopDto {

	private int num;
	private int price;
	private String sangpum;
	private String color;
	private String subject;
	private String content;
	private String ipgoday;
	private String photo;
}

/* src/main/resources/static 에 라이브러리 폴더 저장
 * pom.xml 라이브러리 추가
 * file_uploader
 * String path = getServletContext().getRealPath("/save"); 수정
 */
