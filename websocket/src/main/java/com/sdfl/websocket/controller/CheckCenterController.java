package com.sdfl.websocket.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sdfl.websocket.service.WebSocketServer;

@Controller
@RequestMapping("/checkCenter")
public class CheckCenterController {
	// 页面请求
	@GetMapping("/socket/{cid}")
	public ModelAndView socket(@PathVariable String cid) {
		ModelAndView mav = new ModelAndView("/socket");
		mav.addObject("cid", cid);
		return mav;
	}

	/**
	 * 消息推送接口
	 */
	@ResponseBody
	@RequestMapping("/socket/push/{cid}")
	public String pushToWeb(@PathVariable String cid, String message) {
		try {
			WebSocketServer.sendInfo(message, cid);
		} catch (IOException e) {
			e.printStackTrace();
			return cid + "#" + e.getMessage();
		}
		return cid;
	}
	
	

}
