package com.example.taskmate.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.taskmate.entity.Status;
import com.example.taskmate.form.TaskRegistForm;
import com.example.taskmate.service.StatusService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TaskRegistController {

	//ログを出す書き方
	private static final Logger logger = LoggerFactory.getLogger(TaskRegistController.class);
	
	//①　まずは利用するサービス層のオブジェクトをDIする
	private final StatusService statusService;
	
	
	/* タスク登録画面表示リクエスト*/
	@PostMapping("/task-show-regist")
	public String showRegist(@ModelAttribute TaskRegistForm form,
			Model mv) {
		
		// ②　そして、ステータス名をモデルに格納してテンプレートに渡すので、modelをつくる
		
		// ③　ステータスリストを取得してModelに設定
		List<Status> status = statusService.findAll();
		mv.addAttribute("statusList",status);
		
		//登録画面へ
		return "task-regist";
		
	/*	 なぜ @ModelAttribute で渡すと使えるようになるのか？
		 Spring MVCの動作：
		 メソッドの引数に @ModelAttribute をつけておくと、Springがそのクラスのオブジェクト（ここでは TaskRegistForm）を作成して、自動でModelに追加してくれます。

		 つまり、明示的に model.addAttribute("taskRegistForm", form); と書かなくても、Springが自動的にやってくれる。
	}
	*/
		
	}
	/* タスク登録リクエスト（登録画面より） */
	@PostMapping("/task-regist")
	
	public String regist(@Validated @ModelAttribute TaskRegistForm form,BindingResult result,
			Model mv) {
		// ④　ここでもModelをつかって渡す

		
		 // フォームの中身をログ出力
        logger.debug("TaskRegistForm 内容: {}", form);
		
		//入力エラーがある場合はタスク登録画面に戻す
		if(result.hasErrors()) {
					
			// ⑤　ステータスリストを取得してModelに設定
			List<Status> status = statusService.findAll();
			mv.addAttribute("statusList",status);
			
			return "task-regist";
		}
		
		//　★ミスした箇所　formのstatusCodeには値が設定されて渡されるが、statusNameには設定されていない
		//この処理を書かないとstatusNameがnullになってしまう
		// ステータ名を form に設定 (Model内)
		//　form のname属性にstatusNameはありません。valueで数値が入るだけなので、statusNameを渡す仕組み
		Status status = statusService.findByCode(form.getStatusCode());
		form.setStatusName(status.getStatusName()); //Lombokの@Dataによって自動生成メソッド
		
		//正常な場合にタスク登録画面に遷移する
		return "task-confirm-regist";
	}
	/* タスク登録リクエスト（登録画面より）*/
	@PostMapping("/task-confirm-regist")
	public String confirmRegist(@Validated @ModelAttribute TaskRegistForm form,BindingResult result,
			RedirectAttributes redirectAttributes,
			Model mv) {
		
		//RedirectAttributes はPRGパターン（POST後にフォーム２重送信防止）
		
		//入力エラーはタスク画面に戻す
		if(result.hasErrors()) {
			
			// ⑤　ステータスリストを取得してModelに設定
			List<Status> status = statusService.findAll();
			mv.addAttribute("statusList",status);
			
			return "task-regist";
		}
		
		//とりあえず書き出し
		System.out.println("--登録form");
		System.out.println(form);
		
		//ここで実際の登録
		
		
		
		//フラッシュスコープに完了メッセージを表示してリダイレクト
		//次のリクエストまで保存されてさらにその内容は次のリクエスト時にModelにコピーされる
		redirectAttributes.addFlashAttribute("msg","(タスク登録)");
		
		return "redirect:/task-complete";
		
	}
}
