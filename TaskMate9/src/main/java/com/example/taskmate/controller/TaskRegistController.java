package com.example.taskmate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.taskmate.form.TaskRegistForm;

@Controller
public class TaskRegistController {

	/* タスク登録画面表示リクエスト*/
	@PostMapping("/task-show-regist")
	public String showRegist(@ModelAttribute TaskRegistForm form) {
		
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
	public String regist(@Validated @ModelAttribute TaskRegistForm form,BindingResult result) {
		
		//入力エラーがある場合はタスク登録画面に戻す
		if(result.hasErrors()) {
			return "task-regist";
		}
		
		//正常な場合にタスク登録画面に遷移する
		return "task-confirm-regist";
	}
	/* タスク登録リクエスト（登録画面より）*/
	@PostMapping("/task-confirm-regist")
	public String confirmRegist(@Validated @ModelAttribute TaskRegistForm form,BindingResult result,RedirectAttributes redirectAttributes) {
		
		//RedirectAttributes はPRGパターン（POST後にフォーム２重送信防止）
		
		//入力エラーはタスク画面に戻す
		if(result.hasErrors()) {
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
