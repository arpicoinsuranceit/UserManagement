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
	
	

	private List<SysUserRoleModel> allusersers;

	private List<SubSbuSysUserModel> getAllSubSysUserModel;

	private List<SubSbuSysUserMenuModel> getsubsysUserMenuModel;
	
	@Override
	public String save(RoleDto roleDto) throws Exception {
		RoleModel model = getRoleModel(roleDto);
		//SysUserModel sysUserModel=sysUserDao.findOne("018781dc-1415-4fad-b6f9-f091efc894e2");

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
				System.out.println(roleMenuModel.getId());
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
		
		RoleModel roleModel = roleDao.findOne(id);

		RoleDto roleDto=new RoleDto();
		
		roleDto.setId(roleModel.getId());
		roleDto.setDescription(roleModel.getDescription());
		roleDto.setName(roleModel.getName());
		roleDto.setSbu(AppConstant.SUUCODE);
		
		
		List<MenuDto> menudtos=new ArrayList<MenuDto>();
		
		List<RoleMenuModel> getRollmodel=roleMenuDao.findByRoleModelAndEnabled(roleModel, 1);
		
		
		boolean valid=false;
		
		for (RoleMenuModel roleMenuModel : getRollmodel) {
			if(roleMenuModel.getMenuModel().getIsEnabled().equals(1)) {
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
		
		System.out.println(roleDto);
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
		
		try {
		
			RoleModel model = getEditRoleModel(roleDto);

			List<RoleMenuModel> menuModel = new ArrayList<RoleMenuModel>();
			
			 
			
			List<RoleMenuModel> roleMenuModel = roleMenuDao.findByRoleModelAndEnabled(model, 1);
			
			List<RoleMenuModel> roleMenus = (List<RoleMenuModel>) roleMenuDao.findAll();
			
			
			List<String> allmenus=roleDto.getMenus();
			
			List<RoleMenuModel> roleMenuModelsNew = new ArrayList<RoleMenuModel>(); 
			
			List<SubSbuSysUserMenuModel> allSubSubSysUserMenus=new ArrayList<SubSbuSysUserMenuModel>();
			
			
			for (RoleMenuModel m : roleMenuModel) {
				boolean b = false;
				for(String md : allmenus) {
					if(m.getMenuModel().getMenuId().equals(md)) {
						b =true;
						System.out.println(b);
					}
					
				
				if(!b) {
					m.setEnabled(0);
					
					List<RoleMenuModel> roleMenuModels1 = roleMenuDao.findByRoleModelAndEnabled(model, 1);
					allusersers = new ArrayList<SysUserRoleModel>();
					
					
					for (RoleMenuModel roleMenuModels2 : roleMenuModels1) {
						allusersers=roleMenuModels2.getRoleModel().getSysUserRoleModels();
						
						for (SysUserRoleModel sysUserRoleModel : allusersers) {
							getAllSubSysUserModel=sysUserRoleModel.getSysUserModel().getSbuSysUsers();
							
							for (SubSbuSysUserModel subSbuSysUserModel : getAllSubSysUserModel) {
								allSubSubSysUserMenus=subSbuSysUserModel.getSubSbuSysUserMenus();
								subSbuSysUserModel.getSubSbuSysUserMenus().forEach(e->{
									
									System.out.println("md 1 "+md);
									
									if(e.getMenu().getMenuId()!=md) {
										System.out.println("md 2 "+md);
										SubSbuSysUserMenuModel getSelectsubSbuSysUserMenuModel=subSbuSysUserMenuDao.findOne(e.getSubSbuSysUserMenuId());
										
										if (getSelectsubSbuSysUserMenuModel.getMenu().getMenuId().equals(md)) {
											getSelectsubSbuSysUserMenuModel.setIsEnabled(0);
											
										}
										
									}
									
									
									
									
									
								});
								
							}
							
						}
					}
					
				}
				
				}
			
			}
			
			for (String menu : allmenus) {
				
				boolean available = false;
				
			
				for (RoleMenuModel roleMenuModels : roleMenuDao.findByRoleModelAndEnabled(model, 1)) {
				if(roleMenuModels.getMenuModel().getMenuId().equals(menu)) {
						available = true;
					}
				
				if(!available ) {
					MenuModel menumodel=menuDao.findOne(menu);
					RoleMenuModel roleMenumodels = new RoleMenuModel();
					roleMenumodels.setEnabled(AppConstant.ENABLE);
					roleMenumodels.setId(UUID.randomUUID().toString());
					roleMenumodels.setRoleModel(model);
					roleMenumodels.setMenuModel(menumodel);
					menuModel.add(roleMenumodels);
					
					getSubsysUserMenu(model, menu,menumodel);

					
				}		
					
			}
		}
			
			roleDao.save(model);
			roleMenuDao.save(menuModel);
			subSbuSysUserMenuDao.save(getsubsysUserMenuModel);
			
			
			
		} catch (Exception e) {
			
		}
		
		return "200";
		
		}
	
	
	public void getSubsysUserMenu(RoleModel model,String menu,MenuModel menuModel) {
		
		getsubsysUserMenuModel=new ArrayList<SubSbuSysUserMenuModel>();
		List<RoleMenuModel> getAllEnabled=roleMenuDao.findByRoleModelAndEnabled(model, 1);
		
		for (RoleMenuModel roleMenuModel : getAllEnabled) {
			for (SysUserRoleModel sysUserRoleModel : roleMenuModel.getRoleModel().getSysUserRoleModels()) {
				for (SubSbuSysUserModel subSbuSysUserModel : sysUserRoleModel.getSysUserModel().getSbuSysUsers()) {
					for (SubSbuSysUserMenuModel subSbuSysUserMenuModel : subSbuSysUserModel.getSubSbuSysUserMenus()) {
						if (subSbuSysUserMenuModel.getMenu().getMenuId().equals(menu)) {
							SubSbuSysUserModel subSbuSysUser=subsubSysUserDao.findOne(subSbuSysUserModel.getSubSbuSysUserId());
							SubSbuSysUserMenuModel subSbuSysUserMenu=new SubSbuSysUserMenuModel();
							subSbuSysUserMenu.setSubSbuSysUser(subSbuSysUser);
							subSbuSysUserMenu.setSubSbuSysUserMenuId(UUID.randomUUID().toString());
							subSbuSysUserMenu.setMenu(menuModel);
							subSbuSysUserMenu.setIsEnabled(AppConstant.ENABLE);
							
							getsubsysUserMenuModel.add(subSbuSysUserMenu);
							
						}
						
					}
					
				}
			}
		}
	}
	

}

