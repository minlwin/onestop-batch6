package com.jdc.shop.model.service;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.shop.api.employee.input.MemberForm;
import com.jdc.shop.api.employee.input.MemberSearch;
import com.jdc.shop.api.employee.output.MemberDto;
import com.jdc.shop.api.member.output.MemberProfile;
import com.jdc.shop.model.entity.Address;
import com.jdc.shop.model.entity.Member;
import com.jdc.shop.model.entity.Member_;
import com.jdc.shop.model.repo.AccountRepo;
import com.jdc.shop.model.repo.MemberRepo;
import com.jdc.shop.model.repo.TownshipRepo;
import com.jdc.shop.utils.exceptions.ApiBusinessException;
import com.jdc.shop.utils.io.DataModificationResult;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
@Transactional(readOnly = true)
public class MemberService {

	@Autowired
	private MemberRepo repo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private PurchaseService purchaseService;
	@Autowired
	private TownshipRepo townshipRepo;
	@Autowired
	private AccountRepo accountRepo;
	
	@Autowired
	private PhotoUploadService photoUploadService;

	public Page<MemberProfile> search(MemberSearch form, int page, int size) {
		Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc = cb -> {
			var cq = cb.createQuery(Long.class);
			var root = cq.from(Member.class);
			// select count(e.id)
			cq.select(cb.count(root.get(Member_.id)));

			cq.where(form.where(cb, root));
			return cq;
		};

		Function<CriteriaBuilder, CriteriaQuery<MemberProfile>> queryFunc = cb -> {
			var cq = cb.createQuery(MemberProfile.class);
			var root = cq.from(Member.class);
			// select count(e.id)
			MemberProfile.select(cq, root);
			cq.where(form.where(cb, root));

			cq.orderBy(cb.asc(root.get(Member_.name)));

			return cq;
		};

		return repo.search(queryFunc, countFunc, page, size);
	}

	public MemberDto findById(int id) {
		var profile = repo.findById(id).map(MemberProfile::from)
				.orElseThrow(() -> new ApiBusinessException("Invalid member id."));
		var purchases = purchaseService.findByMemberId(id);
		return new MemberDto(profile, purchases);
	}

	public MemberProfile findByUserName(String username) {
		return repo.findOneByAccountLoginId(username).map(MemberProfile::from)
				.orElseThrow(() -> new ApiBusinessException("Invalid login id."));
	}

	@Transactional
	public DataModificationResult<Integer> create(MemberForm form) {

		if (!StringUtils.hasLength(form.getLoginId())) {
			throw new ApiBusinessException("Please enter login id.");
		}

		if (accountRepo.countByLoginId(form.getLoginId()) > 0) {
			throw new ApiBusinessException("Login id has been used.");
		}

		var entity = form.entity(passwordEncoder);
		var address = new Address();
		address.setAddress(form.getAddress());
		address.setTownship(townshipRepo.getReferenceById(form.getTownshipId()));

		entity.setAddress(address);

		entity = repo.save(entity);

		return new DataModificationResult<>(entity.getId(), "Member has been created.");
	}

	@Transactional
	public DataModificationResult<Integer> update(int id, MemberForm form) {

		var entity = repo.findById(id).orElseThrow(() -> new ApiBusinessException("Invalid member id."));

		entity.setName(form.getName());
		entity.setPhone(form.getPhone());
		entity.setEmail(form.getEmail());
		entity.setDob(form.getDob());
		entity.setGender(form.getGender());

		return new DataModificationResult<>(entity.getId(), "Member has been updated.");
	}

	@Transactional
	public DataModificationResult<Integer> uploadPhoto(int id, MultipartFile file) {

		var imageName = photoUploadService.saveProfileImage(id, file);

		var entity = repo.findById(id).orElseThrow(() -> new ApiBusinessException("Invalid member id."));
		entity.setProfileImage(imageName);

		return new DataModificationResult<>(entity.getId(), "Profile image has been uploaded.");
	}

}
