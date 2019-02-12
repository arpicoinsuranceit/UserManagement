package com.arpico.groupit.usermanagement.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.sound.midi.Soundbank;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.apache.poi.util.RLEDecompressingInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arpico.groupit.usermanagement.dao.MenuDao;
import com.arpico.groupit.usermanagement.dao.RoleDao;
import com.arpico.groupit.usermanagement.dao.RoleMenuDao;
import com.arpico.groupit.usermanagement.dao.SubSbuSysUserDao;
import com.arpico.groupit.usermanagement.dao.SubSbuSysUserMenuDao;
import com.arpico.groupit.usermanagement.dao.SysUserDao;
import com.arpico.groupit.usermanagement.dao.SysUserRoleDao;
import com.arpico.groupit.usermanagement.dto.MenuDto;
import com.arpico.groupit.usermanagement.dto.RoleDto;
import com.arpico.groupit.usermanagement.dto.UserAssignDto;
import com.arpico.groupit.usermanagement.model.MenuModel;
import com.arpico.groupit.usermanagement.model.RoleMenuModel;
import com.arpico.groupit.usermanagement.model.RoleModel;
import com.arpico.groupit.usermanagement.model.SubSbuSysUserMenuModel;
import com.arpico.groupit.usermanagement.model.SubSbuSysUserModel;
import com.arpico.groupit.usermanagement.model.SysUserModel;
import com.arpico.groupit.usermanagement.model.SysUserRoleModel;
import com.arpico.groupit.usermanagement.service.RoleService;
import com.arpico.groupit.usermanagement.util.AppConstant;
import com.mysql.fabric.xmlrpc.base.Array;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMenuDao roleMenuDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private MenuDao menuDao;
	
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	
	@Autowired
	private SysUserDao sysUserDao;
	
	@Autowired
	private SubSbuSysUserDao subsubSysUserDao;
	
	@Autowired
	private SubSbuSysUserMenuDao subSbuSysUserMenuDao;
	
	private String menuId="";

	private List<SysUserRoleModel> allusersers;

	private List<SubSbuSysUserModel> getAllSubSysUserModel;

	private List<SubSbuSysUserMenuModel> getsubsysUserMenuModel;
	
	private List<String> allmenus;
	
	private RoleMenuModel rolems=null;
	
	private MenuModel menumodels;
	
	private String set="";
	@Override
	public String save(RoleDto roleDto) throws Exception {
		RoleModel model = getRoleModel(roleDto);

		List<RoleMenuModel> roleMenuModels = new ArrayList<>();

		roleDto.getMenus().forEach(e -> {
			roleMenuModels.add(getRoleMenuModel(e, model));
		});

		
		roleDao.save(model);
		roleMenuDao.save(roleMenuModels);

		return "200";
	}

	private RoleMenuModel getRoleMenuModel(String e, RoleModel roleModel) {
		MenuModel menuModel = menuDao.findOne(e);

		RoleMenuModel model = new RoleMenuModel();
		model.setEnabled(AppConstant.ENABLE);
		model.setId(UUID.randomUUID().toString());
		model.setRoleModel(roleModel);
		model.setMenuModel(menuModel);

		return model;
	}
	
	private RoleMenuModel getEditRoleMenuModel(String e, RoleModel roleModel) {
		
		
		MenuModel menuModel = menuDao.findOne(e);
		
		RoleMenuModel model = new RoleMenuModel();
		
		List<RoleMenuModel> menuList=(List<RoleMenuModel>) roleMenuDao.findAll();
		
		for (RoleMenuModel roleMenuModel : menuList) {
			if (roleMenuModel.getMenuModel().getMenuId()==e) {
				model.setEnabled(AppConstant.ENABLE);
				model.setId(roleMenuModel.getId());
				model.setRoleModel(roleModel);
				model.setMenuModel(menuModel);
				
				
			}else {
			
				model.setEnabled(AppConstant.ZERO);
				model.setId(roleMenuModel.getId());
				model.setRoleModel(roleModel);
				model.setMenuModel(menuModel);
				
			}
		}
		

		return model;
	}

	private RoleModel getRoleModel(RoleDto roleDto) {
		RoleModel model = new RoleModel();
		model.setCreateDate(new Date());
		model.setDescription(roleDto.getDescription());
		model.setEnable(AppConstant.ENABLE);
		model.setId(UUID.randomUUID().toString());
		model.setName(roleDto.getName());
		return model;
	}
	
	private RoleModel getEditRoleModel(RoleDto roleDto) {
		RoleModel model = new RoleModel();
		model.setCreateDate(new Date());
		model.setDescription(roleDto.getDescription());
		model.setEnable(AppConstant.ENABLE);
		model.setId(roleDto.getId());
		model.setName(roleDto.getName());
		return model;
	}

	@Override
	public List<RoleDto> getAll() throws Exception {
		List<RoleModel> roleModels = (List<RoleModel>) roleDao.findAll();

		List<RoleDto> dtos = new ArrayList<>();
		roleModels.forEach(e -> {
			dtos.add(getRoleDto(e));
		});

		return dtos;

	}

	private RoleDto getRoleDto(RoleModel e) {
		RoleDto dto = new RoleDto();
		dto.setDescription(e.getDescription());
		dto.setId(e.getId());
		dto.setName(e.getName());
		dto.setSbu("450");
		return dto;
	}

	@Override
	public List<RoleDto> getAll(String val) throws Exception {
		List<RoleModel> roleModels = null;
		if(val.equals("No_Val")) {
			roleModels = (List<RoleModel>) roleDao.findAll();
		}else {
			roleModels = (List<RoleModel>) roleDao.findAllByNameContaining(val);			
		}
		
		 

		List<RoleDto> dtos = new ArrayList<>();
		roleModels.forEach(e -> {
			dtos.add(getRoleDto(e));
		});

		return dtos;
	}

	@Override
	public RoleDto getSelectedRole(String id) throws Exception {
		System.out.println(id);
		RoleModel roleModel = roleDao.findOne(id);

		RoleDto roleDto=new RoleDto();
		
		roleDto.setId(roleModel.getId());
		roleDto.setDescription(roleModel.getDescription());
		roleDto.setName(roleModel.getName());
		roleDto.setSbu(AppConstant.SUUCODE);
		
		
		List<MenuDto> menudtos=new ArrayList<MenuDto>();
		
		List<RoleMenuModel> getRollmodel=roleMenuDao.findByRoleModelAndEnabled(roleModel, 1);
		
		System.out.println(getRollmodel);
		
		
		boolean valid=false;
		
		for (RoleMenuModel roleMenuModel : getRollmodel) {
			if(roleMenuModel.getEnabled()==1) {
				MenuModel menuModel=roleMenuModel.getMenuModel();
				MenuDto menuDto=new MenuDto();
				menuDto.setMenuId(menuModel.getMenuId());
				menuDto.setMenuName(menuModel.getMenuName());
				menuDto.setMenuDescription(menuModel.getMenuDescription());
				menuDto.setHref(menuModel.getHref());
				menuDto.setIcon(menuModel.getIcon());
				menuDto.setLevel(menuModel.getLevel());
				menuDto.setParent(menuModel.getParent());
				menuDto.setSubMenuBr(menuModel.getSubMenuBr());
				menuDto.setSystem(menuModel.getSubSbuModel().getSubSbuName());
				menuDto.setIcon(menuModel.getIcon());
				
				menudtos.add(menuDto);
			}
			
			}
		
		roleDto.setMenuDto(menudtos);
		
		return roleDto;
		
	
	}

	private MenuDto getMenuDto(MenuModel menuModel) {
		MenuDto menuDto = new MenuDto();
		
		menuDto.setMenuId(menuModel.getMenuId());
		menuDto.setMenuName(menuModel.getMenuName());
		menuDto.setMenuDescription(menuModel.getMenuDescription());
		menuDto.setHref(menuModel.getHref());
		menuDto.setIcon(menuModel.getIcon());
		menuDto.setLevel(menuModel.getLevel());
		menuDto.setParent(menuModel.getParent());
		menuDto.setSubMenuBr(menuModel.getSubMenuBr());
		menuDto.setSystem(menuModel.getSubSbuModel().getSbu().getSbuName());
		return menuDto;
	}

	@Override
	public String edit(RoleDto roleDto) {
		
		
			
			String size="";
			RoleModel model = getEditRoleModel(roleDto);

			List<RoleMenuModel> rolemenuModel = new ArrayList<RoleMenuModel>();
			List<SysUserRoleModel> getAlRoleModel=new ArrayList<SysUserRoleModel>();
			List<String> setSubId=new ArrayList<String>();
			
			List<SubSbuSysUserModel> getAllSubSysyUser=new ArrayList<SubSbuSysUserModel>();
			List<RoleMenuModel> roleMenuModel = roleMenuDao.findByRoleModelAndEnabled(model, 1);
			List<RoleMenuModel> roleMenus = (List<RoleMenuModel>) roleMenuDao.findAll();
			List<String> allmenus=roleDto.getMenus();
			
			List<String> allids=new ArrayList<String>();
			
			List<RoleMenuModel> roleMenuModelsNew = new ArrayList<RoleMenuModel>(); 
			
			List<SubSbuSysUserMenuModel> allSubSubSysUserMenus=new ArrayList<SubSbuSysUserMenuModel>();
			
			
			
		
		
			allmenus.forEach(e->{
					RoleModel roles=roleDao.findOne(roleDto.getId());
					MenuModel menumods=menuDao.findOne(e);
					RoleMenuModel rolem=roleMenuDao.findOneByRoleModelAndMenuModel(roles, menumods);
					if(rolem==null && rolem.getEnabled()==1) {
						System.out.println("workkkkkkkkkk");
						MenuModel menumodels=menuDao.findOne(menumods.getMenuId());
						RoleMenuModel roleMenumodels = new RoleMenuModel();
						roleMenumodels.setEnabled(AppConstant.ENABLE);
						roleMenumodels.setId(UUID.randomUUID().toString());
						roleMenumodels.setRoleModel(roles);
						roleMenumodels.setMenuModel(menumodels);
						
						rolemenuModel.add(roleMenumodels);
						

						
						
						roles.getSysUserRoleModels().forEach(r->{
							getAlRoleModel.add(r);
						});
						
						getAlRoleModel.forEach(sysUserrole->{
							sysUserrole.getSysUserModel().getSbuSysUsers().forEach(sub->{
								getAllSubSysyUser.add(sub);
							});
						});
						
						try {
							
						
						getAllSubSysyUser.forEach(subs->{
							if(setSubId.isEmpty()) {
								setSubId.add(subs.getSubSbuSysUserId());
							
							}
						
							if(!setSubId.isEmpty()) {
								setSubId.forEach(s1->{
									String id=subs.getSubSbuSysUserId();
									String ids=s1;
									if(!ids.equals(id)) {
										setSubId.add(ids);
										System.out.println(id);
									}
								});
							}
							setSubId.forEach(subid->{
								SubSbuSysUserModel subSysUser=subsubSysUserDao.findOne(subid);
								SubSbuSysUserMenuModel sub=new SubSbuSysUserMenuModel();
								sub.setSubSbuSysUserMenuId(UUID.randomUUID().toString());
								sub.setSubSbuSysUser(subSysUser);
								sub.setMenu(menumodels);
								sub.setIsEnabled(AppConstant.ENABLE);
								sub.setCreatedTime(new Date());
								allSubSubSysUserMenus.add(sub);
							});
							System.out.println("Working");
							//e18e8b8a-1541-4b2b-b3c2-e5bf3457a13b
							
						});
						} catch (Exception e2) {
							// TODO: handle exception
						}
						
					}
			});
			
		
			
	
			roleDao.save(model);
			roleMenuDao.save(rolemenuModel);
			subSbuSysUserMenuDao.save(allSubSubSysUserMenus);
		
		
		return "200";
		
		}

	@Override
	public List<RoleDto> getUserRoles(String id) throws Exception {

		SysUserModel sysuser=sysUserDao.findOne(id);
		
		List<SysUserRoleModel> getSysyusersRoles=sysUserRoleDao.findAllBySysUserModel(sysuser);
		
		List<RoleDto> getRoles=new ArrayList<RoleDto>();
		
		getSysyusersRoles.forEach(e->{
			if(e.getEnabled()==1) {
				
			
			RoleDto r=new RoleDto();
			r.setId(e.getRoleModel().getId());
			r.setName(e.getRoleModel().getName());
			r.setDescription(e.getRoleModel().getDescription());
			getRoles.add(r);
			
			}
		});
		
		return getRoles;
	}

	@Override
	public String removerole(UserAssignDto userAssignDto) throws Exception {
		System.out.println("worksss");
		SysUserModel sysuser=sysUserDao.findOne(userAssignDto.getUserid());
		
		userAssignDto.getRoles().forEach(e->{
			RoleModel role=roleDao.findOne(e);
			try {
				SysUserRoleModel sysUserRole=sysUserRoleDao.findOneBySysUserModelAndRoleModel(sysuser, role);
				sysUserRole.setEnabled(0);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		return "Work";
	}

	@Override
	public String removeroleMenus(RoleDto roleDto) throws Exception {
		System.out.println("working");
		RoleModel role=roleDao.findOne(roleDto.getId());
		List<RoleMenuModel> getAllRoleMenus=roleMenuDao.findByRoleModelAndEnabled(role, 1);
		roleDto.getMenus().forEach(e->{
			MenuModel m=menuDao.findOne(e);
			System.out.println(e);
			System.out.println(role.getId());
			for (RoleMenuModel roleMenuModel : getAllRoleMenus) {
				if(roleMenuModel.getMenuModel().equals(m) && roleMenuModel.getMenuModel().equals(m)) {
					roleMenuModel.setEnabled(0);
				}
			}
			
			
			
//			roleMenu.getRoleModel().getSysUserRoleModels().forEach(sysRole->{
//				sysRole.getSysUserModel().getSbuSysUsers().forEach(subsysRole->{
//					subsysRole.getSubSbuSysUserMenus().forEach(submenus->{
//						SubSbuSysUserMenuModel subMenuModel=subSbuSysUserMenuDao.findOneBySubSbuSysUserAndMenuAndIsEnabled(submenus.getSubSbuSysUser(), m,1);
//						System.out.println("Work");
//						System.out.println(subMenuModel);
//						if(subMenuModel!=null && subMenuModel.getIsEnabled()==1) {
//							subMenuModel.setIsEnabled(0);
//						}
//						
//					});
//				});
//			});
		});
		
		
		return "Work";
	}
	

}

