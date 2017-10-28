package ssb.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ssb.api.SSBRestURI;
import ssb.model.BarUser;
import ssb.model.BarUserResponse;
import ssb.service.BarUserService;
import ssb.service.status.ResponseCode;

@RestController
@RequestMapping(SSBRestURI.REST_BAR_USER)
public class SSBRestController extends AbstractRestController {
	
	@Autowired
	BarUserService barUserService;
	// /barUser/misidn/1231234124
	@RequestMapping(value="/check",method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object findByMsisdn(@RequestBody List<BarUser> barUsers,HttpServletResponse response) throws Exception {
		
		BarUserResponse resData = new BarUserResponse();
		resData = barUserService.findByList(barUsers, response);
		return resData;

	}
	
	@RequestMapping(value="/replace",method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object save(@RequestBody List<BarUser> barUsers ,HttpServletResponse response) throws Exception {
		BarUserResponse resData = new BarUserResponse();
		resData = barUserService.save(barUsers, response);
		return resData;
		
	}
	
	
	@RequestMapping(value="/delete",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object delete(@RequestBody List<BarUser> barUsers,HttpServletResponse response) {
			BarUserResponse resData = barUserService.deleteBarUser(barUsers, response);
		return resData;
		
	}
	
	@RequestMapping(value="/clear",method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object clear(HttpServletResponse response){
		BarUserResponse resData = new BarUserResponse();
		resData = barUserService.clear(response);
		return resData;
		
	}
	
}
