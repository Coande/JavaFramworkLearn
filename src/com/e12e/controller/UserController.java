package com.e12e.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import com.e12e.dao.UserDAO;
import com.e12e.model.User;

@Controller
@RequestMapping(value = "user")
public class UserController {

	@Autowired
	UserDAO userDAO;
	
	Map<String, User> userMap = new HashMap<String, User>();

	
	/**
	 * 基于Cookie的国际化
	 * @param request
	 * @param response
	 * @param model
	 * @param langType
	 * @return
	 */
	@RequestMapping(value = "/changeLang")
	public String changeLang(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "langType", defaultValue = "zh") String langType) {
		if (!model.containsAttribute("contentModel")) {
			if (langType.equals("zh")) {
				Locale locale = new Locale("zh", "CN");
				(new CookieLocaleResolver()).setLocale(request, response, locale);
			} else if (langType.equals("en")) {
				Locale locale = new Locale("en", "US");
				(new CookieLocaleResolver()).setLocale(request, response, locale);
			} else
				(new CookieLocaleResolver()).setLocale(request, response, LocaleContextHolder.getLocale());
		}
		return "redirect:/user/userList";
	}

	/**
	 * 列出所有用户数据
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/userList", method = RequestMethod.GET)
	public String list(Model model) {
		List<User> userList=userDAO.find();
		//将DAO返回的List转换成Map
		for(User user:userList){
			userMap.put(user.getUserId()+"", user);
		}
		model.addAttribute("users", userMap);
		return "/user/userList";
	}

	/**
	 * 查找相关用户
	 * 
	 * @param keyword
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/userList", method = RequestMethod.POST)
	public String search(Model model, String keyword) {

		// 用于存储查询到的结果
		Map<String, User> temp = new HashMap<String, User>();

		List<User> userList=userDAO.findByUsername(keyword);
		//将DAO返回的List转换成Map
		for(User user:userList){
			temp.put(user.getUserId()+"", user);
		}
		
		model.addAttribute("users", temp);
		return "/user/userList";
	}

	/**
	 * 删除用户
	 * 
	 * @param username
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/delete/{userId}", method = RequestMethod.GET)
	public String delete(@PathVariable String userId, Model model) {
		userDAO.delete(Integer.parseInt(userId));
		userMap.remove(userId);
		return "redirect:/user/userList";
	}

	/**
	 * 跳转到编辑用户页面
	 * 
	 * @param userId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit/{userId}", method = RequestMethod.GET)
	public String edit(@PathVariable String userId, Model model) {

		model.addAttribute("user", userMap.get(userId));
		return "/user/userEdit";
	}

	/**
	 * 处理编辑用户
	 * 
	 * @param username
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/edit/{userId}", method = RequestMethod.POST)
	public String doEdit(@PathVariable String userId, @Validated User user, BindingResult br) {

		if (br.hasErrors()) {
			return "/user/userEdit";
		}
		user.setUserId(Integer.parseInt(userId));
		userDAO.update(user);
		
		userMap.put(userId, user);
		
		return "redirect:/user/userList";
	}

	/**
	 * 跳转到添加用户
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		return "/user/userAdd";
	}

	/**
	 * 处理添加用户
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String doAdd(HttpServletRequest req, @Validated User user, BindingResult br) {
		if (br.hasErrors()) {
			for (ObjectError oe : br.getAllErrors()) {
				System.out.println(oe.getDefaultMessage());
			}
			return "/user/userAdd";
		}
		userDAO.save(user);
		
		return "redirect:/user/userList";
	}
}
