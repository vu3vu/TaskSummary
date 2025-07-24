package com.example.maintenanceuser1.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.maintenanceuser1.entity.Area;
import com.example.maintenanceuser1.entity.User;
import com.example.maintenanceuser1.form.MaintenForm;
import com.example.maintenanceuser1.form.UserSearchFrom;
import com.example.maintenanceuser1.service.MaintenService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainteUserController {

	//ログを出す書き方
	private static final Logger logger = LoggerFactory.getLogger(MainteUserController.class);
		
	private final MaintenService maintenService;
	

	@GetMapping("/admin")
	public String userTop() {
		return "admin-top";
	}	
	

	@PostMapping("/user-search")
	public String searchUser(@ModelAttribute UserSearchFrom form,Model mv) {
		
		//検索ボタンをオスまえの画面
		//地域コードをDBから検索して渡す
		List<Area> area = maintenService.findAreaAll();	
		mv.addAttribute("statusCodeList", area);
		
		return "user-list";
	}
	
	@PostMapping("/user-search-list")
	public String userserach(@ModelAttribute UserSearchFrom form,Model mv) {
		//部分一致検索フォームを出す
		

		System.out.println("--検索form");
		System.out.println(form);
		
		User usr = new User();//USer型をパラメータにいれることが重要　Stringではない
		
		/* !formじゃなくformとタイプミスしていたのがSQL生成がおかしくなっていた */
		
		if(!form.getUserName().equals("")) {
			usr.setUserName("%" + form.getUserName() + "%");
		}
		if(!form.getAreaCode().equals("")) {
			usr.setAreaCode(form.getAreaCode());
		}	
		
		System.out.println("--検索Model");
		System.out.println(mv);
		logger.debug("MODEL 内容: {}", mv);
		
		//mybatisを通じてDBアクセスを実施する
		List<User> list = maintenService.findUser(usr);//DIしたサービスを呼び出し
		mv.addAttribute("taskSummaryList",list);
		
//		//地域コードをDBから検索して渡す。！！これがないとselectリストができない
		List<Area> area = maintenService.findAreaAll();	
		mv.addAttribute("statusCodeList", area);		

		
		return "user-list";
	}

	
	@PostMapping("/task-show-regist")
	public String checkUser(@ModelAttribute MaintenForm form,RedirectAttributes redirectAttributes) {
		
		//ここに苦労した
		//登録確認画面のformにはareaCodeしか流れないのでareaNameをSQLで調べて地域コード名を表示させる
		//　form のname属性にstatusNameはありません。valueで数値が入るだけなので、statusNameを渡す仕組み
		Area area = maintenService.findAreaCode(form.getAreaCode());	
		form.setAreaName(area.getAreaName()); //Lombokの@Dataによって自動生成メソッド
		
		//インサートするためにオブジェクトにセット
		User usr = new User();
		usr.setAreaCode(form.getAreaName());
		usr.setUserName(form.getUserName());
		usr.setAreaCode(form.getAreaCode());
		usr.setEmail(form.getEmail());
		//usr.setEmail(form.getEmail());
		
		//とりあえず書き出し
				System.out.println("--登録form");
				System.out.println(form);
		return "/task-confirm-regist";

	}
	@PostMapping("/task-confirm-regist")
	public String addUser(@ModelAttribute MaintenForm form,RedirectAttributes redirectAttributes) {
		
		//ここに苦労した
		//登録確認画面のformにはareaCodeしか流れないのでareaNameをSQLで調べて地域コード名を表示させる
		//　form のname属性にstatusNameはありません。valueで数値が入るだけなので、statusNameを渡す仕組み
		Area area = maintenService.findAreaCode(form.getAreaCode());	
		form.setAreaName(area.getAreaName()); //Lombokの@Dataによって自動生成メソッド
		
		//インサートするためにオブジェクトにセット
		User usr = new User();
		usr.setAreaCode(form.getAreaName());
		usr.setUserName(form.getUserName());
		usr.setAreaCode(form.getAreaCode());
		usr.setEmail(form.getEmail());
		
		//とりあえず書き出し
				System.out.println("--登録form");
				System.out.println(form);
		
		maintenService.insertUser(usr);
		//フラッシュスコープに完了メッセージを表示してリダイレクト
		//次のリクエストまで保存されてさらにその内容は次のリクエスト時にModelにコピーされる
		redirectAttributes.addFlashAttribute("msg","(タスク登録)");
		
		return "redirect:/task-complete";
	}
	@PostMapping("add-user")
	public String addUserData(@ModelAttribute MaintenForm form,
			Model mv) {
		
		//　form のname属性にstatusNameはありません。valueで数値が入るだけなので、statusNameを渡す仕組み
		//Area area = maintenService.findAreaCode(form.getAreaCode());	
		//form.setAreaName(area.getAreaName()); //Lombokの@Dataによって自動生成メソッド
		//mv.addAttribute("maintenForm", form);  // ★ これが必要！ これは引数で@ModelAttributeをつけることと同じ
		
		List<Area> areaList = maintenService.findAreaAll();
		mv.addAttribute("Area",areaList);
		
		 // フォームの中身をログ出力
        logger.debug("addUserData 内容: {}", areaList);
        
		//とりあえず書き出し
		System.out.println("--登録form--AreaList--");
		System.out.println(areaList);
        
		return "add-user";
	}
	/* 完了後のリダイレクト先（タスク更新系） */
	@GetMapping("task-complete")
	private String completeTask() {
		return "task-complete";
	}
}
