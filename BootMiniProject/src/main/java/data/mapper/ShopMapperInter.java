package data.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import data.dto.ShopDto;

@Mapper
public interface ShopMapperInter {

	public void insertShop(ShopDto dto);
	public List<ShopDto> getShopList(Map<String, Integer> map);
	public ShopDto getSangpum(int num);
	public void deleteShop(int num);
	public int getTotalCount();
 }
