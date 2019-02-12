package com.arpico.groupit.usermanagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arpico.groupit.usermanagement.dao.BranchDao;
import com.arpico.groupit.usermanagement.dao.SysUserBranchDao;
import com.arpico.groupit.usermanagement.dao.SysUserDao;
import com.arpico.groupit.usermanagement.dto.BranchAssignDto;
import com.arpico.groupit.usermanagement.dto.BranchDto;
import com.arpico.groupit.usermanagement.model.BranchModel;
import com.arpico.groupit.usermanagement.model.SysUserBranchModel;
import com.arpico.groupit.usermanagement.model.SysUserModel;
import com.arpico.groupit.usermanagement.service.BranchService;

@Service
@Transactional
public class BranchServiceImpl implements BranchService{

	
	@Autowired
	private BranchDao branchDao;
	
	
	@Autowired
	private SysUserDao sysUserDao;
	
	@Autowired
	private SysUserBranchDao sysUserBranchDao;
	

	
	@Override
	public String getBranch(String userName) throws Exception {

		return null;
	}

	@Override
	public List<String> getAllPhysicalBranches() throws Exception {
		
		List<BranchModel> branchModels=branchDao.findByPhysical("1");
		List<String> branchcodes=new ArrayList<>();
		branchModels.forEach(branch -> {
			
			branchcodes.add(branch.getCode());
		});
		
		return branchcodes;
	}

	@Override
	public List<BranchDto> getAllBranch(String code) throws Exception {
		
		List<BranchModel> getAllBranch=new ArrayList<BranchModel>();
		
		List<BranchDto> getalllbranchDto=new ArrayList<BranchDto>();
		if(code.equals("No_Val")) {
			getAllBranch=(List<BranchModel>) branchDao.findAll();
		}else {
			getAllBranch=branchDao.findAllByCode(code);
		}
		
		BranchDto branchDto=null;
		for (BranchModel branchModel : getAllBranch) {
			
			branchDto=new BranchDto();
			branchDto.setId(branchModel.getId());
			branchDto.setCode(branchModel.getCode());
			branchDto.setName(branchModel.getName());
			getalllbranchDto.add(branchDto);
		}
		
		return getalllbranchDto;
	}

	@Override
	public String assignBranch(BranchAssignDto branchAssignDto) throws Exception {
		
		List<SysUserBranchModel> getAllBranch=new ArrayList<SysUserBranchModel>(); 
		
		List<SysUserBranchModel> getAll=(List<SysUserBranchModel>) sysUserBranchDao.findAll();
		
		SysUserBranchModel sysUserBranchModel=new SysUserBranchModel();
		
		boolean exsist=false;
		
		
		
		
		for (String id  : branchAssignDto.getBranch()) {
			for (String userid : branchAssignDto.getUsers()) {
				BranchModel branchModel=branchDao.findOne(id);
				SysUserModel sysUserModel=sysUserDao.findOne(userid);
				if (sysUserBranchDao.findOneByBranchAndSysUser(branchModel, sysUserModel)==null) {
					getAllBranch.add(setSysUserBranch(sysUserModel, branchModel));
					}
			
			}
			
		}
		
		for (SysUserBranchModel sysUserBranchModels : getAllBranch) {
			sysUserBranchDao.save(sysUserBranchModels);
			
			
		}
		
		return "Work";
	}
	
	private SysUserBranchModel setSysUserBranch(SysUserModel sysUserModel,BranchModel branchModel) {
		SysUserBranchModel sysUserBranchModel=new SysUserBranchModel();
		sysUserBranchModel.setId(UUID.randomUUID().toString());
		sysUserBranchModel.setBranch(branchModel);
		sysUserBranchModel.setSysUser(sysUserModel);
		return sysUserBranchModel;
	}

	@Override
	public List<BranchDto> getAllAssignBranch(String id) throws Exception {
		
		SysUserModel sysUserModel=sysUserDao.findOne(id);
		List<SysUserBranchModel> allsysUserBranchModel=sysUserBranchDao.findAllBySysUser(sysUserModel);
		
		List<BranchDto> getallAssignBranch=new ArrayList<BranchDto>();
		
		allsysUserBranchModel.forEach(e->{
			
			if(e.getIsEnabled()==1) {
			
			BranchDto branchdto=new BranchDto();
			branchdto.setId(e.getBranch().getId());
			branchdto.setCode(e.getBranch().getCode());
			branchdto.setName(e.getBranch().getName());
			
			getallAssignBranch.add(branchdto);
			}
			
		});
		
		
		return getallAssignBranch;
	}

	@Override
	public String removeBranch(BranchAssignDto branchAssignDto) throws Exception {
		
		SysUserModel sysuser=sysUserDao.findOne(branchAssignDto.getUserid());
		
		branchAssignDto.getBranch().forEach(e->{
			BranchModel b=branchDao.findOne(e);
			try {
				SysUserBranchModel sys=sysUserBranchDao.findOneByBranchAndSysUser(b, sysuser);
				System.out.println("working");
				sys.setIsEnabled(0);
				
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		});
		
		return "Work";
	}

	
}
