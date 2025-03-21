package com.ecommerce.backendone.service;

import java.util.List;

import com.ecommerce.backendone.entity.Section;

public interface SectionService {
	
    List<Section> getAllSections();

    Section getSectionById(Integer sectionId);

}
