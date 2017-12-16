package com.zuxp.springboot.hello.contorller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zuxp.springboot.hello.view.User;

@RestController
@RequestMapping("/user")
public class UserResetfullController {
	

	@RequestMapping(path = {"/{id}"}, method = RequestMethod.GET)
	public User getUser(@PathVariable(name = "id") String id){
		
		User user = new User();
		
		user.setId(id);
		user.setName("haha");
		user.setSex("famale");
		
		return user;
		
	}
	
	@RequestMapping(path = {"{id}"}, method = RequestMethod.DELETE)
	public User delUser(@PathVariable(name = "id") String id){
		
		User user = new User();
		
		user.setId(id);
		user.setName("已经删除了哈哈哈");
		user.setSex("famale");
		
		return user;
		
	}

}
