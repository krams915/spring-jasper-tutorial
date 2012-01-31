package org.krams.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.krams.domain.User;
import org.krams.repository.UserRepository;
import org.krams.response.JqgridResponse;
import org.krams.response.StatusResponse;
import org.krams.response.UserDto;
import org.krams.service.DownloadService;
import org.krams.service.TokenService;
import org.krams.util.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private DownloadService downloadService;
	
	@Autowired
	private TokenService tokenService;
	
	@RequestMapping
	public String getUsersPage() {
		return "users";
	}
	
	@RequestMapping(value="/records", produces="application/json")
	public @ResponseBody JqgridResponse<UserDto> records(
    		@RequestParam(value="page", required=false) Integer page,
    		@RequestParam(value="rows", required=false) Integer rows) {

		Pageable pageRequest = new PageRequest(page-1, rows);
			
		Page<User> users = repository.findAll(pageRequest);
		List<UserDto> userDtos = UserMapper.map(users);
		
		JqgridResponse<UserDto> response = new JqgridResponse<UserDto>();
		response.setRows(userDtos);
		response.setRecords(Long.valueOf(users.getTotalElements()).toString());
		response.setTotal(Integer.valueOf(users.getTotalPages()).toString());
		response.setPage(Integer.valueOf(users.getNumber()+1).toString());
		
		return response;
	}
	
	@RequestMapping(value="/download/progress")
	public @ResponseBody StatusResponse checkDownloadProgress(@RequestParam String token) {
		return new StatusResponse(true, tokenService.check(token));
	}
	
	@RequestMapping(value="/download/token")
	public @ResponseBody StatusResponse getDownloadToken() {
		return new StatusResponse(true, tokenService.generate());
	}
	
	@RequestMapping(value="/download")
	public void download(@RequestParam String type,
			@RequestParam String token, 
			HttpServletResponse response) {
		downloadService.download(type, token, response);
	}
}
