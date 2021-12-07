package pers.brian.kaigiantest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pers.brian.kaigiantest.service.OssService;

import java.util.Map;

/**
 * @Description:
 * @Author: BrianHu
 * @Create: 2021-11-11 11:11
 * @Version: 0.0.1
 **/
@RestController
@RequestMapping(value = "/oss")
public class OssController {

	@Autowired
	private OssService ossService;

	@CrossOrigin
	@RequestMapping(value = "/policy", method = RequestMethod.GET)
	public Map<String, String> policy() {
		return ossService.policy();
	}
}
