package ssb.service.imp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ssb.model.BarUser;
import ssb.model.BarUserResponse;
import ssb.repository.BarUserRepository;
import ssb.service.BarUserService;
import ssb.service.exception.APIException;
import ssb.service.status.ResponseCode;
import ssb.util.GeneralUtil;

@Service("barUserService")
public class BarUserServiceImp implements BarUserService {

	@Value("${db.clear}")
	private String clearInDays;
	
	@Autowired
	BarUserRepository barUserRepository;

	@Override
	public BarUserResponse save(List<BarUser> barUsers, HttpServletResponse response) throws APIException {

		BarUserResponse barUserResponse = new BarUserResponse();
		List<BarUser> successBarUser = new ArrayList<BarUser>();
		List<BarUser> failBarUser = new ArrayList<BarUser>();
		for (BarUser barUser : barUsers) {

			BarUser result = barUserRepository.findByMsisdn(barUser);
			barUser.setModifyDate(Calendar.getInstance().getTime());

			try {
				if (result == null) {
					barUser.setId(GeneralUtil.generateUUID());
					barUserRepository.insert(barUser);
				} else {
					barUserRepository.update(barUser);
				}
				successBarUser.add(barUser);
			} catch (Exception ex) {
				failBarUser.add(barUser);
			}
		}
		if (successBarUser.size() == 0) {
			barUserResponse.setResultResponse(ResponseCode.STATUS_INTERNAL_SERVER_ERROR);
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		} else if (failBarUser.size() == 0) {
			barUserResponse.setResultResponse(ResponseCode.STATUS_OK);
		} else {
			barUserResponse.setResultResponse(ResponseCode.STATUS_OK_WITH_CONDITION);
		}
		return barUserResponse;
	}

	@Override
	public BarUserResponse findByList(List<BarUser> barUsers, HttpServletResponse response) {
		BarUserResponse barUserResponse = new BarUserResponse();
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < barUsers.size(); i++) {
			if (i != 0) {
				stringBuilder.append(" ,");
			}
			stringBuilder.append("'" + barUsers.get(i).getMsisdn() + "'");
		}
		String msisdnList = stringBuilder.toString();

		try {

			List<BarUser> resultObject = barUserRepository.findByList(msisdnList);
			if (resultObject.size() == 0) {
				barUserResponse.setResultResponse(ResponseCode.STATUS_OK_WITH_DATA_NOT_FOUND);
			} else {
				barUserResponse.setResultResponse(ResponseCode.STATUS_OK);
			}
		} catch (Exception ex) {
			barUserResponse.setResultResponse(ResponseCode.STATUS_INTERNAL_SERVER_ERROR);
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return barUserResponse;
	}

	@Override
	public BarUserResponse deleteBarUser(List<BarUser> msisdn, HttpServletResponse response) {
		BarUserResponse barUserResponse = new BarUserResponse();
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < msisdn.size(); i++) {
			if (i != 0) {
				stringBuilder.append(" ,");
			}
			stringBuilder.append("'" + msisdn.get(i).getMsisdn() + "'");
		}
		String msisdnList = stringBuilder.toString();

		try {
			barUserRepository.deleteByList(msisdnList);
			barUserResponse.setResultResponse(ResponseCode.STATUS_OK);
		} catch (Exception e) {
			barUserResponse.setResultResponse(ResponseCode.STATUS_INTERNAL_SERVER_ERROR);
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		
		return barUserResponse;

	}

	@Override
	public BarUserResponse clear(HttpServletResponse response) {
		BarUserResponse barUserResponse = new BarUserResponse();
		try{
			barUserRepository.clear(clearInDays);
			barUserResponse.setResultResponse(ResponseCode.STATUS_OK);
		}
		catch(Exception e){
			barUserResponse.setResultResponse(ResponseCode.STATUS_INTERNAL_SERVER_ERROR);
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return barUserResponse;
	}

}
