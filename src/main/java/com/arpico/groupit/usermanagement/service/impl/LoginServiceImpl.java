package com.arpico.groupit.usermanagement.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.arpico.groupit.usermanagement.dao.LoginDao;
import com.arpico.groupit.usermanagement.dto.LoginResponseDto;
import com.arpico.groupit.usermanagement.dto.UserTokenDto;
import com.arpico.groupit.usermanagement.model.Login;
import com.arpico.groupit.usermanagement.security.EncryptData;
import com.arpico.groupit.usermanagement.security.JwtGenerator;
import com.arpico.groupit.usermanagement.service.LoginService;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao loginDao;

	@Override
	public LoginResponseDto isUser(String userName, String password) throws Exception {

		LoginResponseDto responseDto = new LoginResponseDto();

		//Login login = loginDao.findOneByUserNameAndPassword(userName, EncryptData.encrypt(password));
		
		Login login = loginDao.findOneByUserName(userName);

		if (login != null && login.getUsers() != null) {
			if(login.getPassword().equals(EncryptData.encrypt(password))) {
				if (login.getLocks().equals(0)) {
					responseDto.setLock(false);
				} else {
					responseDto.setLock(true);
				}

				LocalDateTime date = LocalDateTime.now();
				LocalDateTime updateTime = LocalDateTime.ofInstant(login.getModifydate().toInstant(),
						ZoneId.systemDefault());

				long difference = ChronoUnit.DAYS.between(updateTime, date);

				if (difference > 45) {
					responseDto.setExpired(true);
				}

				if (difference > 42) {
					responseDto.setNeedChange(true);
				}

				if (login.getFailCount() > 0) {
					responseDto.setFail(true);
					responseDto.setFailCount(login.getFailCount());
				}

				UserTokenDto userTokenDto = new UserTokenDto();

				userTokenDto.setUserCode(login.getUsers().getUserCode());
				userTokenDto.setUserFullName(login.getUsers().getUser_Name());
				userTokenDto.setUserId(login.getUsers().getUserId());

				login.setLastLog(new Date());
				login.setFailCount(0);

				loginDao.save(login);

				JwtGenerator generator = new JwtGenerator();
				responseDto.setJwtToken(generator.generate(userTokenDto));
				responseDto.setLogin(true);

			}else {
				login.setLastLog(new Date());
				responseDto.setLogin(false);
				login.setFailCount(login.getFailCount()+1);
				loginDao.save(login);
			}
			
		} else {
			responseDto.setLogin(false);
		}

		return responseDto;
	}

	@Override
	public LoginResponseDto changePassword(String userName, String password, String newPassword,
			String confirmNewPassword) throws Exception {
		
		LoginResponseDto responseDto = new LoginResponseDto();
		System.out.println(userName);
		System.out.println(password);
		System.out.println(newPassword);
		
		Login login = loginDao.findOneByUserNameAndPassword(userName, EncryptData.encrypt(password));
		if (login != null && login.getUsers() != null) {
			if(newPassword.equals(confirmNewPassword)) {
				login.setLastLog(new Date());
				login.setModifydate(new Date());
				login.setFailCount(0);
				login.setPassword(EncryptData.encrypt(newPassword));
				loginDao.save(login);
				responseDto.setExpired(false);	
				responseDto.setLogin(true);
			}
			
		}else {
			responseDto.setExpired(true);	
			responseDto.setLogin(false);
		}

		return responseDto;
		
	}

}
