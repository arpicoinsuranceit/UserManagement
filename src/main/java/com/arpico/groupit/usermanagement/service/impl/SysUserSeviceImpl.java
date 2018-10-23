package com.arpico.groupit.usermanagement.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arpico.groupit.usermanagement.dao.RoleDao;
import com.arpico.groupit.usermanagement.dao.SubSbuSysUserDao;
import com.arpico.groupit.usermanagement.dao.SubSbuSysUserMenuDao;
import com.arpico.groupit.usermanagement.dao.SysUserDao;
import com.arpico.groupit.usermanagement.dto.MenuDto;
import com.arpico.groupit.usermanagement.dto.UserAssignDto;
import com.arpico.groupit.usermanagement.dto.UserTokenDto;
import com.arpico.groupit.usermanagement.model.MenuModel;
import com.arpico.groupit.usermanagement.model.RoleModel;
import com.arpico.groupit.usermanagement.model.SubSbuModel;
import com.arpico.groupit.usermanagement.model.SubSbuSysUserModel;
import com.arpico.groupit.usermanagement.model.SubSbuSysUserMenuModel;
import com.arpico.groupit.usermanagement.model.SysUserModel;
import com.arpico.groupit.usermanagement.security.JwtDecoder;
import com.arpico.groupit.usermanagement.service.SysUserService;
import com.arpico.groupit.usermanagement.util.AppConstant;

@Service
@Transactional
public class SysUserSeviceImpl implements SysUserService {

	@Autowired
	private SysUserDao sysUserDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private SubSbuSysUserDao subSbuSysUserDao;
	
	@Autowired
	private SubSbuSysUserMenuDao subSbuSysUserMenuDao;
	
	@Override
	public List<MenuDto> getAllByUser(String referance) throws Exception {
		String userId = new JwtDecoder().generate(referance);
		SysUserModel sysUser = sysUserDao.findOne(userId);

		List<HashMap<String, Object>> menuMap = new ArrayList<>();

		List<MenuModel> menus = new ArrayList<>();

		if (sysUser != null) {
			for (SubSbuSysUserModel sbuSysUser : sysUser.getSbuSysUsers()) {
				if (sbuSysUser.getSubSbu().getSubSbuId().equals("")) {
					for (SubSbuSysUserMenuModel sbuSysUserMenu : sbuSysUser.getSubSbuSysUserMenus()) {
						menus.add(sbuSysUserMenu.getMenu());
					}
				}
			}
		}

		for (MenuModel menuModel1 : menus) {
			if (menuModel1.getParent().equals("0")) {
				HashMap<String, Object> menuLvl1 = new HashMap<>();
				menuLvl1.put("name", menuModel1.getMenuName());
				menuLvl1.put("href", menuModel1.getHref());
				menuLvl1.put("icon", menuModel1.getIcon());

				boolean isChildAvailableLvl1 = false;

				List<HashMap<String, Object>> childMapLvl1 = new ArrayList<>();

				for (MenuModel menuModel2 : menus) {
					if (menuModel2.getParent().equals(menuModel1.getMenuId())) {
						isChildAvailableLvl1 = true;
						HashMap<String, Object> menuLvl2 = new HashMap<>();

						menuLvl2.put("name", menuModel2.getMenuName());
						menuLvl2.put("href", menuModel2.getHref());
						menuLvl2.put("icon", menuModel2.getIcon());

						boolean isChildAvailableLvl2 = false;
						List<HashMap<String, Object>> childMapLvl2 = new ArrayList<>();

						for (MenuModel menuModel3 : menus) {
							if (menuModel3.getParent().equals(menuModel2.getMenuId())) {
								isChildAvailableLvl2 = true;
							}
						}

						if (isChildAvailableLvl2) {
							menuLvl1.put("child", childMapLvl2);
						} else {
							menuLvl1.put("child", null);
						}

						childMapLvl1.add(menuLvl2);
					}
					menus.remove(menuModel2);
				}
				if (isChildAvailableLvl1) {
					menuLvl1.put("child", childMapLvl1);
				} else {
					menuLvl1.put("child", null);
				}
				menuMap.add(menuLvl1);
			}
			menus.remove(menuModel1);
		}

		return null;
	}

	@Override
	public List<UserTokenDto> getAll(String val) throws Exception {

		List<SysUserModel> models = null;

		if (val.equals("No_Val")) {
			models = sysUserDao.findAllByIsEnabeled(1);
		} else {
			models = sysUserDao.findAllByIsEnabeledAndUserFirstNameContaining(1, val);
		}

		System.out.println(models.size());

		List<UserTokenDto> dtos = new ArrayList<>();

		models.forEach(e -> {
			dtos.add(getUserTokenDto(e));
		});

		return dtos;
	}

	private UserTokenDto getUserTokenDto(SysUserModel e) {
		UserTokenDto dto = new UserTokenDto();

		dto.setUserCode(e.getUserCode());
		dto.setUserId(e.getUserId());
		dto.setUserFullName(e.getUserFirstName() + " " + e.getUserLastName());

		return dto;
	}

	@Override
	public String assignUser(UserAssignDto userAssignDto) throws Exception {

		List<RoleModel> roleModels = new ArrayList<>();
		List<SysUserModel> sysUserModels = new ArrayList<>();
		List<SubSbuModel> subSbuModels = new ArrayList<>();
		List<MenuModel> menus = new ArrayList<>();
		
		List<SubSbuSysUserModel> subSbuSysUserModels = new ArrayList<>();
		List<SubSbuSysUserMenuModel> subSbuSysUserMenuModels = new ArrayList<>();

		userAssignDto.getRoles().forEach(e -> {
			roleModels.add(roleDao.findOne(e));
		});

		
		userAssignDto.getUsers().forEach(e -> {
			sysUserModels.add(sysUserDao.findOne(e));
		});

		roleModels.forEach(role -> {
			role.getRoleMenuModels().forEach(roleMenu -> {

				boolean isAvailableMenu = false;

				for (MenuModel menu : menus) {
					if (roleMenu.getMenuModel().getMenuId().equals(menu.getMenuId())) {
						isAvailableMenu = true;
					}
				}
				
				if(!isAvailableMenu) {
					menus.add(roleMenu.getMenuModel());
				}
				
				boolean isAvailableSubSbu = false;

				for (SubSbuModel subSbu : subSbuModels) {
					if (roleMenu.getMenuModel().getSubSbuModel().getSubSbuId().equals(subSbu.getSubSbuId())) {
						isAvailableSubSbu = true;
					}
				}
				
				if(!isAvailableSubSbu) {
					subSbuModels.add(roleMenu.getMenuModel().getSubSbuModel());
				}

			});
		});
		
		subSbuModels.forEach(subSbuModel -> {
			sysUserModels.forEach(sysUser -> {
				subSbuSysUserModels.add(getSubSbuSysUser(subSbuModel,sysUser));
			});
		});
		
		
		subSbuSysUserModels.forEach(subSbuSysUserModel-> {
			menus.forEach(menu-> {
				subSbuSysUserMenuModels.add(getSunSbuSysUserMenuModel(subSbuSysUserModel,menu ));
			});
		});

		System.out.println(subSbuSysUserModels.size() + " : subSbuSysUserModels.size()");
		System.out.println(subSbuSysUserMenuModels.size() + " : subSbuSysUserMenuModels.size()");
		
		subSbuSysUserDao.save(subSbuSysUserModels);
		subSbuSysUserMenuDao.save(subSbuSysUserMenuModels);
		
		return "200";
	}

	private SubSbuSysUserMenuModel getSunSbuSysUserMenuModel(SubSbuSysUserModel subSbuSysUserModel, MenuModel menu) {
		SubSbuSysUserMenuModel model = new SubSbuSysUserMenuModel();
		model.setCreatedTime(new Date());
		model.setIsEnabled(AppConstant.ENABLE);
		model.setMenu(menu);
		model.setSubSbuSysUser(subSbuSysUserModel);
		model.setSubSbuSysUserMenuId(UUID.randomUUID().toString());
		return model;
	}

	private SubSbuSysUserModel getSubSbuSysUser(SubSbuModel subSbuModel, SysUserModel sysUser) {
		SubSbuSysUserModel model = new SubSbuSysUserModel();
		
		model.setIsEnabled(AppConstant.ENABLE);
		model.setSubSbu(subSbuModel);
		model.setSubSbuSysUserId(UUID.randomUUID().toString());
		model.setSysUser(sysUser);
		model.setCreatedTime(new Date());
		
		return model;
	}

}
