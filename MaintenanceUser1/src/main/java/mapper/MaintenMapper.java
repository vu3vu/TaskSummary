package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.maintenanceuser1.entity.user;

@Mapper
public interface MaintenMapper {
	// 一覧全件検索
		List<user> selectListAll();
}
