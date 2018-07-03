package com.arpico.groupit.usermanagement.service.impl;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.arpico.groupit.usermanagement.dao.SysUserDao;
import com.arpico.groupit.usermanagement.dto.LoginResponseDto;
import com.arpico.groupit.usermanagement.dto.UserTokenDto;
import com.arpico.groupit.usermanagement.model.SysUser;
import com.arpico.groupit.usermanagement.security.EncryptData;
import com.arpico.groupit.usermanagement.security.JwtGenerator;
import com.arpico.groupit.usermanagement.service.LoginService;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	@Autowired
	private SysUserDao sysUserDao;

	@Override
	public LoginResponseDto isUser(String userName, String password) throws Exception {

		LoginResponseDto responseDto = new LoginResponseDto();

		//Login login = loginDao.findOneByUserNameAndPassword(userName, EncryptData.encrypt(password));
		
		SysUser sysUser = sysUserDao.findOneByUserName(userName);

		if (sysUser != null ) {
			if(sysUser.getUserPassword().equals(EncryptData.encrypt(password))) {
				/*if (SysUser.equals(0)) {
					responseDto.setLock(false);
				} else {
					responseDto.setLock(true);
				}*/

				responseDto.setLock(false);
				
				/*LocalDateTime date = LocalDateTime.now();
				LocalDateTime updateTime = LocalDateTime.ofInstant(sysUser.getMo.toInstant(),
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
				}*/

				UserTokenDto userTokenDto = new UserTokenDto();

				userTokenDto.setUserCode(sysUser.getUserCode());
				userTokenDto.setUserFullName(sysUser.getUserFirstName() + " " + sysUser.getUserLastName() );
				userTokenDto.setUserId(sysUser.getUserId());

				/*login.setLastLog(new Date());
				login.setFailCount(0);

				loginDao.save(login);*/

				JwtGenerator generator = new JwtGenerator();
				responseDto.setJwtToken(generator.generate(userTokenDto));
				responseDto.setLogin(true);

			}else {/*
				login.setLastLog(new Date());
				responseDto.setLogin(false);
				login.setFailCount(login.getFailCount()+1);
				loginDao.save(login);*/
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
		
		SysUser sysUser = sysUserDao.findOneByUserNameAndUserPassword(userName, EncryptData.encrypt(password));
		if (sysUser != null) {
			if(newPassword.equals(confirmNewPassword)) {
				//login.setLastLog(new Date());
				//login.setModifydate(new Date());
				//login.setFailCount(0);
				sysUser.setUserPassword(newPassword);
				sysUserDao.save(sysUser);
				responseDto.setExpired(false);	
				responseDto.setLogin(true);
			}
			
		}else {
			responseDto.setExpired(true);	
			responseDto.setLogin(false);
		}

		return responseDto;
		
	}

	@Override
	public List<SysUser> getAll() throws Exception {
		return (List<SysUser>) sysUserDao.findAll();
		
	}

}
