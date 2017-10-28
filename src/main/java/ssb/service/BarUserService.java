package ssb.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import ssb.model.BarUser;
import ssb.model.BarUserResponse;
import ssb.service.exception.APIException;

public interface BarUserService {
	
	public BarUserResponse save(List<BarUser> barUsers, HttpServletResponse response) throws APIException;

	public BarUserResponse deleteBarUser(List<BarUser> barUsers ,HttpServletResponse response);
	
	public BarUserResponse findByList(List<BarUser> barUsers,HttpServletResponse response);

	public BarUserResponse clear(HttpServletResponse response);
}
