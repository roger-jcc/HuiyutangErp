package com.HuiyutangErp.controller.admin;


import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.HuiyutangErp.bean.LoginReq;
import com.HuiyutangErp.bean.UserReq;
import com.HuiyutangErp.pojo.User;
import com.HuiyutangErp.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * 會員登入登出
 * @author hank
 *
 */
@Controller
@RequestMapping("/admin")
public class LoginController {
	
	
	@Autowired
	UserService userService;
	
	@GetMapping("/login")
	public String loginPage() {
		return "admin/login";
	}
	
	@GetMapping("/register")
	public String vendorlist(Model mod) {
		return "admin/register";
	}
	
	
	
	/**
	 * 登入
	 * @param username
	 * @param password
	 * @param session
	 * @param attribute
	 * @return
	 */
	@PostMapping("/account")
	public String account( @ModelAttribute("loginreq") LoginReq loginreq,
			HttpSession session ,RedirectAttributes attribute,HttpServletRequest request,HttpServletResponse response,Model mod) {
        response.setContentType("text/html;charset=UTF-8");
        User user = (User) session.getAttribute("user");
        //已經登入
        if(user !=null) {
        	session.setAttribute("user", user);
			mod.addAttribute("user", user);
        	 return "admin/account";
        }
		
		if(StringUtils.isEmpty(loginreq.getUsername()) || StringUtils.isEmpty(loginreq.getPassword())) {
			attribute.addFlashAttribute("message", "請輸入帳號或密碼");
			return "redirect:/admin/login";
		}
		
		 //取得 是否《記住密碼》 選項
        String remember=loginreq.getRemember();
		
		try {
			 user = userService.checkuser(loginreq.getUsername(), loginreq.getPassword());
			if(user!=null) {
				user.setPassword(null);
				
				session.setAttribute("user", user);
				mod.addAttribute("user", user);
				session.setAttribute("session", session);
				 
				//記住我
				if("true".equals(remember)){
	                 Cookie cookie =new Cookie(loginreq.getUsername(),loginreq.getPassword());
	                //cookie有效期
	                //一个月
	                cookie.setMaxAge(60*60*24*30);
	                //cookie发送到浏览器
	                response.addCookie(cookie);

	            }
			}else {
				attribute.addFlashAttribute("message", "登入失敗，帳號或密碼錯誤");
				return "redirect:/admin/login";
			}
			
		} catch (Exception e) {
			attribute.addFlashAttribute("message", e.getMessage());
			mod.addAttribute("message", e.getMessage());
			return "redirect:/admin/login";
		}
		
		 return "admin/account";
	}
	
	/**
	 * 註冊
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@PostMapping("/register")
	public void register(@RequestBody UserReq req ,Model mod) throws Exception {
		String emailRegex = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
        boolean emailSuccess = Pattern.matches(emailRegex, req.getEmail());
        if (!emailSuccess) {
        	mod.addAttribute("error","信箱格式不正確！" );
        	throw new Exception("信箱格式不正確！");
        }
//        String passwordRegex = "^(?![A-Za-z]+$)(?![A-Z\\d]+$)(?![A-Z\\W]+$)(?![a-z\\d]+$)(?![a-z\\W]+$)(?![\\d\\W]+$)\\S{8,16}$/;\n";
//        boolean pwdSuccess = Pattern.matches(passwordRegex, req.getPassword());
//        if (!pwdSuccess) {
//        	throw new Exception("包含大小写字母、数字、特殊字符至少3个组合大于8小于16个字符");
//        }
		
		if(userService.findUserByUserName(req.getUsername())) {
			mod.addAttribute("errorMsg", "帳戶已存在");
			throw new Exception("帳戶已存在");
		}
		if(!StringUtils.equals(req.getPassword(), req.getCheckPassword())) {
			mod.addAttribute("errorMsg", "密碼不一樣，請重新輸入");
			throw new Exception("密碼不一樣，請重新輸入");
		}
		mod.addAttribute("success","註冊成功" );
		userService.save(req);
	}
	
	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect : /admin/login";
		
	}

}
