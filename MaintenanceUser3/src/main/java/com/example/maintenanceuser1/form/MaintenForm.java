package com.example.maintenanceuser1.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class MaintenForm {
	
	@Size(min=1,max=32,message="1-32文字で入力")
	private String userName;
	
	@Email(message = "メールアドレスの形式が正しくありません")
	private String email;
	
	@Size(min=1,max=2,message="指定に誤り")
	private String areaCode;
	
	private String areaName;
}
