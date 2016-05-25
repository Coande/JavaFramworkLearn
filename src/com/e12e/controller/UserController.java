package com.e12e.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.e12e.entity.User;

@Controller
@RequestMapping(value = "user")
public class UserController {

	Map<String, User> userMap = new HashMap<String, User>();

	/**
	 * 装载数据
	 */
	public UserController() {
		userMap.put("Coande01", new User("Coande01", "password01", "e11e@qq.com", 18, "男"));
		userMap.put("Coande02", new User("Coande02", "password02", "e12e@qq.com", 19, "女"));
		userMap.put("Coande03", new User("Coande03", "password03", "e13e@qq.com", 20, "男"));
		userMap.put("Coande04", new User("Coande04", "password04", "e14e@qq.com", 21, "女"));
		userMap.put("张三1111", new User("张三1111", "密码123456", "e14e@qq.com", 21, "男"));
	}

	
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
		System.out.println("keyword=" + keyword);
		// 用于存储查询到的结果
		Map<String, User> temp = new HashMap<String, User>();

		Set set = userMap.keySet();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String mapKey = it.next();
			// 忽略大小写进行匹配
			if (mapKey.matches("(?i).*" + keyword + ".*")) {
				temp.put(mapKey, userMap.get(mapKey));
			}
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
	@RequestMapping(value = "/delete/{username}", method = RequestMethod.GET)
	public String delete(@PathVariable String username, Model model) {
		try {
			// 解决乱码问题
			username = new String(username.getBytes("ISO-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("delete something : " + username);
		userMap.remove(username);
		return "redirect:/user/userList";
	}

	/**
	 * 跳转到编辑用户页面
	 * 
	 * @param username
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit/{username}", method = RequestMethod.GET)
	public String edit(@PathVariable String username, Model model) {
		// 解决乱码问题
		try {
			username = new String(username.getBytes("ISO-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		model.addAttribute("user", userMap.get(username));
		return "/user/userEdit";
	}

	/**
	 * 处理编辑用户
	 * 
	 * @param username
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/edit/{username}", method = RequestMethod.POST)
	public String doEdit(@PathVariable String username, @Validated User user, BindingResult br) {
		// 解决乱码问题
		try {
			username = new String(username.getBytes("ISO-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		if (br.hasErrors()) {
			return "/user/userEdit";
		}

		userMap.remove(username);
		userMap.put(user.getUsername(), user);
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
	public String doAdd(HttpServletRequest req, @Validated User user, BindingResult br, Map<String, Object> map) {
		if (br.hasErrors()) {

			for (ObjectError oe : br.getAllErrors()) {
				System.out.println(oe.getDefaultMessage());
			}
			return "/user/userAdd";
		}
		userMap.put(user.getUsername(), user);
		return "redirect:/user/userList";
	}
}
