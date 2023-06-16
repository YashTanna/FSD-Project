package com.ecommerce.backendone.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.backendone.entity.Section;
import com.ecommerce.backendone.exception.ResourceNotFoundException;
import com.ecommerce.backendone.repository.SectionRepository;
import com.ecommerce.backendone.service.SectionService;

@Service
public class SectionServiceImpl implements SectionService {
	@Autowired
    private SectionRepository sectionRepository;

    @Override
    public List<Section> getAllSections() {
        return sectionRepository.findAll();
    }

	@Override
	public Section getSectionById(Integer sectionId) {
		return sectionRepository.findById(sectionId).orElseThrow(() -> new ResourceNotFoundException("Section with id: " + sectionId + " not found"));
	}
    
}
