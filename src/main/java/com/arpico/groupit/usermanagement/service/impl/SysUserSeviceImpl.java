package com.arpico.groupit.usermanagement.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arpico.groupit.usermanagement.dao.SysUserDao;
import com.arpico.groupit.usermanagement.dto.MenuDto;
import com.arpico.groupit.usermanagement.model.MenuModel;
import com.arpico.groupit.usermanagement.model.SubSbuSysUserModel;
import com.arpico.groupit.usermanagement.model.SubSbuSysUserMenuModel;
import com.arpico.groupit.usermanagement.model.SysUserModel;
import com.arpico.groupit.usermanagement.security.JwtDecoder;
import com.arpico.groupit.usermanagement.service.SysUserService;

@Service
@Transactional
public class SysUserSeviceImpl implements SysUserService {

	@Autowired
	private SysUserDao sysUserDao;
	
	@Override
	public List<MenuDto> getAllByUser(String referance) throws Exception {
		String userId = new JwtDecoder().generate(referance);
		SysUserModel sysUser = sysUserDao.findOne(userId);
		
		List<HashMap<String, Object>> menuMap = new ArrayList<>();
		
		List<MenuModel> menus = new ArrayList<>();
		
		if(sysUser != null) {
			for (SubSbuSysUserModel sbuSysUser : sysUser.getSbuSysUsers()) {
				if(sbuSysUser.getSubSbu().getSubSbuId().equals("")) {
					for (SubSbuSysUserMenuModel sbuSysUserMenu : sbuSysUser.getSubSbuSysUserMenus()) {
						menus.add(sbuSysUserMenu.getMenu());
					}
				}
			}
		}
		
		
		
		
		for (MenuModel menuModel1 : menus) {
			if(menuModel1.getParent().equals("0")) {
				HashMap<String, Object> menuLvl1 = new HashMap<>();
				menuLvl1.put("name", menuModel1.getMenuName());
				menuLvl1.put("href", menuModel1.getHref());
				menuLvl1.put("icon", menuModel1.getIcon());
				
				boolean isChildAvailableLvl1 = false;
				
				List<HashMap<String, Object>> childMapLvl1 = new ArrayList<>();
				
				
				
				for (MenuModel menuModel2 : menus) {
					if(menuModel2.getParent().equals(menuModel1.getMenuId())) {
						isChildAvailableLvl1 = true;
						HashMap<String, Object> menuLvl2 = new HashMap<>();
						
						menuLvl2.put("name", menuModel2.getMenuName());
						menuLvl2.put("href", menuModel2.getHref());
						menuLvl2.put("icon", menuModel2.getIcon());
						
						boolean isChildAvailableLvl2 = false;
						List<HashMap<String, Object>> childMapLvl2 = new ArrayList<>();
						
						for (MenuModel menuModel3 : menus) {
							if(menuModel3.getParent().equals(menuModel2.getMenuId())) {
								isChildAvailableLvl2 = true;
							}
						}
						
						if(isChildAvailableLvl2) {
							menuLvl1.put("child", childMapLvl2);	
						}else {
							menuLvl1.put("child", null);	
						}
						
						childMapLvl1.add(menuLvl2);
					}
					menus.remove(menuModel2);
				}
				if(isChildAvailableLvl1) {
					menuLvl1.put("child", childMapLvl1);	
				}else {
					menuLvl1.put("child", null);	
				}
				menuMap.add(menuLvl1);
			}
			menus.remove(menuModel1);
		}
		
		return null;
	}

}
