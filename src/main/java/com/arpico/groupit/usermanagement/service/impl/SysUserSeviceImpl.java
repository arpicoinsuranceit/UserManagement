package com.arpico.groupit.usermanagement.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arpico.groupit.usermanagement.dao.RoleDao;
import com.arpico.groupit.usermanagement.dao.RoleMenuDao;
import com.arpico.groupit.usermanagement.dao.SubSbuSysUserDao;
import com.arpico.groupit.usermanagement.dao.SubSbuSysUserMenuDao;
import com.arpico.groupit.usermanagement.dao.SysUserBranchDao;
import com.arpico.groupit.usermanagement.dao.SysUserDao;
import com.arpico.groupit.usermanagement.dao.SysUserRoleDao;
import com.arpico.groupit.usermanagement.dto.MenuDto;
import com.arpico.groupit.usermanagement.dto.RoleDto;
import com.arpico.groupit.usermanagement.dto.SysUserDto;
import com.arpico.groupit.usermanagement.dto.UserAssignDto;
import com.arpico.groupit.usermanagement.dto.UserTokenDto;
import com.arpico.groupit.usermanagement.model.MenuModel;
import com.arpico.groupit.usermanagement.model.RoleMenuModel;
import com.arpico.groupit.usermanagement.model.RoleModel;
import com.arpico.groupit.usermanagement.model.SubSbuModel;
import com.arpico.groupit.usermanagement.model.SubSbuSysUserModel;
import com.arpico.groupit.usermanagement.model.SysUserBranchModel;
import com.arpico.groupit.usermanagement.model.SubSbuSysUserMenuModel;
import com.arpico.groupit.usermanagement.model.SysUserModel;
import com.arpico.groupit.usermanagement.model.SysUserRoleModel;
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
	
	@Autowired
	private RoleMenuDao RoleMenuDao;
	
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	
	@Autowired
	private SysUserBranchDao sysUserBranchDao;
	
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
		SubSbuModel subSbum=new SubSbuModel();
		List<SysUserRoleModel> setRoles=new ArrayList<SysUserRoleModel>();
		
		List<SubSbuSysUserModel> subSbuSysUserModels = new ArrayList<>();
		List<SubSbuSysUserMenuModel> subSbuSysUserMenuModels = new ArrayList<>();
		
		SysUserRoleModel sysUserRoleModel=new SysUserRoleModel();

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
				
				SubSbuSysUserModel sbuSysUserModel = subSbuSysUserDao.findOneBySubSbuAndSysUser(subSbuModel, sysUser);
				
				if(sbuSysUserModel == null) {
					subSbuSysUserModels.add(getSubSbuSysUser(subSbuModel,sysUser));
				}else {
					subSbuSysUserModels.add(sbuSysUserModel);
				}
				
			});
		});
		
		
		subSbuSysUserModels.forEach(subSbuSysUserModel-> {
			menus.forEach(menu-> {
				SubSbuSysUserMenuModel sbuSysUserMenuModel = null;
				if(sbuSysUserMenuModel == null) {
					subSbuSysUserMenuModels.add(getSunSbuSysUserMenuModel(subSbuSysUserModel,menu));
				}
				
			});
		});
		roleModels.forEach(roleModel->{
		sysUserModels.forEach(sysUser->{
			sysUserRoleModel.setRoleModel(roleModel);
			sysUserRoleModel.setSysUserModel(sysUser);
			sysUserRoleModel.setSysUserRoleId(UUID.randomUUID().toString());
			sysUserRoleModel.setEnabled(AppConstant.ENABLE);
			setRoles.add(sysUserRoleModel);
			
		});
		});
		sysUserRoleDao.save(setRoles);
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

	@Override
	public RoleDto updateUserRole(String roleId) throws Exception {
		
		
		
		return null;
		
	}

	@Override
	public String saveSysUser(SysUserDto sysUserDto)  {
		
		
		SysUserModel sysUserModel=new SysUserModel();
		sysUserModel.setUserId(UUID.randomUUID().toString());
		sysUserModel.setUserFirstName(sysUserDto.getUserFirstName());
		sysUserModel.setUserLastName(sysUserDto.getUserLastName());
		sysUserModel.setUserAddress1(sysUserDto.getUserAddress1());
		sysUserModel.setUserAddress2(sysUserDto.getUserAddress2());
		sysUserModel.setUserAddress3(sysUserDto.getUserAddress3());
		sysUserModel.setUserEmail(sysUserDto.getUserEmail());
		sysUserModel.setUserCode(sysUserDto.getUserName());
		sysUserModel.setUserMobileNumber(sysUserDto.getUserMobileNumber());
		sysUserModel.setUserTelephoneNumber(sysUserDto.getUserMobileNumber());
		sysUserModel.setUserName(sysUserDto.getUserName());
		sysUserModel.setUserNic(sysUserDto.getUserNic());
		sysUserModel.setUserPassport(sysUserDto.getUserPassport());
		sysUserModel.setIsEnabeled(AppConstant.ENABLE);
		sysUserModel.setUserSalutation(sysUserDto.getUserSalutation());
		sysUserModel.setCreatedTime(new Date());
		sysUserModel.setUserEmployeeNo(sysUserDto.getUserEmployeeNo());
		sysUserModel.setCreatedTime(new Date());
		
		sysUserDao.save(sysUserModel);
		
		return "Work";
	}

	@Override
	public String searchUserCode(String usercode) throws Exception {
		String exsist="";
		SysUserModel sysUserModel=sysUserDao.findOneByUserCode(usercode);
		
		if (sysUserModel==null) {
			exsist="Fail";
		}else {
			exsist="Sucsess";
		}


		return exsist;
	}

	@Override
	public List<SysUserDto> getAllUsers() throws Exception {
		
		List<SysUserDto> getAll=new ArrayList<SysUserDto>();

		List<SysUserModel> getAlSysUserModel=sysUserDao.findAllByIsEnabeled(AppConstant.ENABLE);
		
		
		getAlSysUserModel.forEach(e->{
			getAll.add(getUserDto(e));
		});
		
		return getAll;
	}
	
	private SysUserDto getUserDto(SysUserModel sysUserModel) {
		
		SysUserDto sysUserDto=new SysUserDto();
		sysUserDto.setId(sysUserModel.getUserId());
		sysUserDto.setUserFirstName(sysUserModel.getUserFirstName());
		sysUserDto.setUserLastName(sysUserModel.getUserLastName());
		sysUserDto.setUserAddress1(sysUserModel.getUserAddress1());
		sysUserDto.setUserAddress2(sysUserModel.getUserAddress2());
		sysUserDto.setUserAddress3(sysUserModel.getUserAddress3());
		sysUserDto.setUserEmail(sysUserModel.getUserEmail());
		sysUserDto.setUserEmployeeNo(sysUserModel.getUserEmployeeNo());
		sysUserDto.setUserName(sysUserModel.getUserName());
		
		
		return sysUserDto;
	}

	@Override
	public SysUserDto findbyUser(String id) throws Exception {
		
		SysUserDto sysUserDto=new SysUserDto();
		SysUserModel sysUserModel=sysUserDao.findOneByUserId(id);
		
		sysUserDto.setId(sysUserModel.getUserId());
		sysUserDto.setUserFirstName(sysUserModel.getUserFirstName());
		sysUserDto.setUserLastName(sysUserModel.getUserLastName());
		sysUserDto.setUserAddress1(sysUserModel.getUserAddress1());
		sysUserDto.setUserAddress2(sysUserModel.getUserAddress2());
		sysUserDto.setUserAddress3(sysUserModel.getUserAddress3());
		sysUserDto.setUserEmail(sysUserModel.getUserEmail());
		sysUserDto.setUserEmployeeNo(sysUserModel.getUserEmployeeNo());
		sysUserDto.setUserName(sysUserModel.getUserName());
		sysUserDto.setUserPassport(sysUserModel.getUserPassport());
		sysUserDto.setUserNic(sysUserModel.getUserNic());
		sysUserDto.setUserTelephoneNumber(sysUserModel.getUserTelephoneNumber());
		sysUserDto.setUserMobileNumber(sysUserModel.getUserMobileNumber());
		
		return sysUserDto;
	}

	@Override
	public String updateUser(SysUserDto sysUserDto) throws Exception {
		SysUserModel sysUserModel=new SysUserModel();
		sysUserModel.setUserId(sysUserDto.getId());
		sysUserModel.setUserFirstName(sysUserDto.getUserFirstName());
		sysUserModel.setUserLastName(sysUserDto.getUserLastName());
		sysUserModel.setUserAddress1(sysUserDto.getUserAddress1());
		sysUserModel.setUserAddress2(sysUserDto.getUserAddress2());
		sysUserModel.setUserAddress3(sysUserDto.getUserAddress3());
		sysUserModel.setUserEmail(sysUserDto.getUserEmail());
		sysUserModel.setUserCode(sysUserDto.getUserName());
		sysUserModel.setUserMobileNumber(sysUserDto.getUserMobileNumber());
		sysUserModel.setUserTelephoneNumber(sysUserDto.getUserMobileNumber());
		sysUserModel.setUserName(sysUserDto.getUserName());
		sysUserModel.setUserNic(sysUserDto.getUserNic());
		sysUserModel.setUserPassport(sysUserDto.getUserPassport());
		sysUserModel.setIsEnabeled(AppConstant.ENABLE);
		sysUserModel.setUserSalutation(sysUserDto.getUserSalutation());
		sysUserModel.setCreatedTime(new Date());
		sysUserModel.setUserEmployeeNo(sysUserDto.getUserEmployeeNo());
		sysUserModel.setUpdatedTime(new Date());
		
		sysUserDao.save(sysUserModel);
		return "work";
	}

	@Override
	public String deleteUser(String userid) throws Exception {
		
		SysUserModel sysUserModel=sysUserDao.findOne(userid);
		sysUserModel.setIsEnabeled(0);
		return "work";
	}

	private SubSbuModel getSubModelname(String id) {
		
		RoleModel rolemodel=roleDao.findOne(id);
		
		SubSbuModel subSbuModel=new SubSbuModel();
		
		List<RoleMenuModel> getRoleMenus=rolemodel.getRoleMenuModels();
		
		for (RoleMenuModel roleMenuModel : getRoleMenus) {
			subSbuModel=roleMenuModel.getMenuModel().getSubSbuModel();
		}
		return subSbuModel;
	}

	@Override
	public String removedublicateSysUser(String id) throws Exception {
		SysUserModel sysUsemodel=sysUserDao.findOne(id);
		
		List<SysUserModel> getAll=sysUserDao.findAll();
		
		List<SubSbuSysUserModel> getAllSub=subSbuSysUserDao.findAll();
		
		List<SysUserRoleModel> getAllsysrole=sysUserRoleDao.findAll();
		
		List<SysUserBranchModel> getAllUserBranch=(List<SysUserBranchModel>) sysUserBranchDao.findAll();
		
		List<SysUserModel> getAllUsers=new ArrayList<SysUserModel>();
		
		List<SubSbuSysUserMenuModel> get;
		List<SubSbuSysUserMenuModel> getmodels=(List<SubSbuSysUserMenuModel>) subSbuSysUserMenuDao.findAll();
		

		
		getmodels.forEach(e->{
			if(e.getSubSbuSysUser().getSysUser().getUserId().equals(sysUsemodel.getUserId())) {
				e.setIsEnabled(0);
			subSbuSysUserMenuDao.save(e);
			}
		});
			
		getAllSub.forEach(e->{
			if(e.getSysUser().getUserId().equals(sysUsemodel.getUserId())) {
				e.setIsEnabled(0);
			}
		});
		
		getAllsysrole.forEach(e->{
				if(e.getSysUserModel().getUserId().equals(sysUsemodel.getUserId())) {
					e.setEnabled(0);
				}
		
		});
		
		getAllUserBranch.forEach(e->{
			if(e.getSysUser().getUserId().equals(sysUsemodel.getUserId())) {
				e.setBranch(null);
				e.setSysUser(null);
				sysUserBranchDao.delete(e.getId());
			}
		});
		
		sysUsemodel.setIsEnabeled(0);
		sysUserDao.save(sysUsemodel);
		
		return "Sucsess";
	}
}
