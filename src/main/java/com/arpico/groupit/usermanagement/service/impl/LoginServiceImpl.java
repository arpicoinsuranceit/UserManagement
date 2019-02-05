package com.arpico.groupit.usermanagement.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arpico.groupit.usermanagement.dao.LoginDao;
import com.arpico.groupit.usermanagement.dao.SubSbuDao;
import com.arpico.groupit.usermanagement.dao.SysUserDao;
import com.arpico.groupit.usermanagement.dto.LoginResponseDto;
import com.arpico.groupit.usermanagement.dto.MenuDto;
import com.arpico.groupit.usermanagement.dto.UserTokenDto;
import com.arpico.groupit.usermanagement.model.LoginModel;
import com.arpico.groupit.usermanagement.model.SubSbuModel;
import com.arpico.groupit.usermanagement.model.SysUserBranchModel;
import com.arpico.groupit.usermanagement.model.SysUserModel;
import com.arpico.groupit.usermanagement.security.EncryptData;
import com.arpico.groupit.usermanagement.security.JwtGenerator;
import com.arpico.groupit.usermanagement.service.LoginService;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	@Autowired
	private SysUserDao sysUserDao;

	@Autowired
	private LoginDao loginDao;
	
	@Autowired
	private SubSbuDao subSbuDao;

	@Override
	public LoginResponseDto isUser(String userName, String password, String subSbu) throws Exception {

		

		LoginResponseDto responseDto = new LoginResponseDto();


		SysUserModel sysUser = sysUserDao.findOneByUserNameAndIsEnabeled(userName, 1);
		

		if (sysUser != null) {
			if (sysUser.getLoginModel().getPassword().equals(EncryptData.encrypt(password))) {
				if (sysUser.getLoginModel().getLocked() == 0) {
					responseDto.setLock(false);
				} else {
					responseDto.setLock(true);
				}

				
				LocalDateTime date = LocalDateTime.now();
				LocalDateTime updateTime = LocalDateTime
						.ofInstant(sysUser.getLoginModel().getLastModified().toInstant(), ZoneId.systemDefault());

				long difference = ChronoUnit.DAYS.between(updateTime, date);

				if (difference > 45) {
					responseDto.setExpired(true);
				}

				if (difference > 42) {
					responseDto.setNeedChange(true);
				}

				if (sysUser.getLoginModel().getFailCount() > 0) {
					responseDto.setFail(true);
					responseDto.setFailCount(sysUser.getLoginModel().getFailCount());
				}

				UserTokenDto userTokenDto = new UserTokenDto();

				userTokenDto.setUserCode(sysUser.getUserCode());
				userTokenDto.setUserFullName(sysUser.getUserFirstName() + " " + sysUser.getUserLastName());
				userTokenDto.setUserId(sysUser.getUserId());
				
				for (SysUserBranchModel e : sysUser.getSysUserBranchModels()) {
				
					if(e.getBranch().getPhysical().equalsIgnoreCase("1")) {
						userTokenDto.setLocCode((e.getBranch().getCode()));
					}
				}
				

				LoginModel loginModel = sysUser.getLoginModel();
				loginModel.setFailCount(0);

				loginDao.save(loginModel);

				
				JwtGenerator generator = new JwtGenerator();
				responseDto.setJwtToken(generator.generate(userTokenDto));
				responseDto.setLogin(true);
				responseDto.setUserName(sysUser.getUserFirstName());

				List<MenuDto> menuDtos = new ArrayList<>();

				SubSbuModel subSbuModel = subSbuDao.findOne(subSbu);
				
				sysUser.getSbuSysUsers().forEach(e -> {

					
					if (e.getSubSbu().equals(subSbuModel)) {
						
						e.getSubSbuSysUserMenus().forEach(f -> {
							
							
							
							if (f.getIsEnabled().equals(1) && f.getMenu().getIsEnabled().equals(1)) {
								MenuDto dto = new MenuDto();
								dto.setMenuId(f.getMenu().getMenuId());
								dto.setHref(f.getMenu().getHref());
								dto.setMenuDescription(f.getMenu().getMenuDescription());
								dto.setMenuName(f.getMenu().getMenuName());
								dto.setLevel(f.getMenu().getLevel());
								dto.setParent(f.getMenu().getParent());
								dto.setIcon(f.getMenu().getIcon());
								dto.setSubMenuBr(f.getMenu().getSubMenuBr());
								menuDtos.add(dto);
							}

						});
					}
				});

				responseDto.setMenuDtos(menuDtos);

			} else {
				responseDto.setLogin(false);

				LoginModel loginModel = sysUser.getLoginModel();
				loginModel.setFailCount(loginModel.getFailCount() + 1);
				
				if(loginModel.getFailCount() >= 3) {
					loginModel.setLocked(1);
				}

				loginDao.save(loginModel);
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
	

		SysUserModel sysUser = sysUserDao.findOneByUserNameAndIsEnabeled(userName, 1);// sysUserDao.findOneByUserNameAndUserPassword(userName,
		// EncryptData.encrypt(password));
		if (sysUser != null && sysUser.getLoginModel().getPassword().equals(EncryptData.encrypt(password))) {
			if (newPassword.equals(confirmNewPassword)) {
				// login.setLastLog(new Date());
				// login.setModifydate(new Date());
				// login.setFailCount(0);
				// sysUser.setUserPassword(newPassword);

				LoginModel loginModel = sysUser.getLoginModel();
				loginModel.setPassword(EncryptData.encrypt(newPassword));
				loginModel.setLastModified(new Date());
				loginModel.setFailCount(0);

				loginDao.save(loginModel);

				responseDto.setExpired(false);
				responseDto.setLogin(true);
			}

		} else {
			responseDto.setExpired(true);
			responseDto.setLogin(false);
		}

		return responseDto;

	}

	@Override
	public List<SysUserModel> getAll() throws Exception {
		return (List<SysUserModel>) sysUserDao.findAll();

	}

}
