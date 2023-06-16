package com.ecommerce.backendone.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.backendone.controller.SectionController;
import com.ecommerce.backendone.dto.SectionDto;
import com.ecommerce.backendone.dto.mapper.SectionMapper;
import com.ecommerce.backendone.entity.Section;
import com.ecommerce.backendone.service.SectionService;

@RestController
public class SectionControllerImpl implements SectionController {
	@Autowired
	private SectionService sectionService;

	@Override
	public ResponseEntity<List<SectionDto>> getAllSections() {
		List<Section> sections = sectionService.getAllSections();

		List<SectionDto> sectionDtos = sections.stream()
				.map(SectionMapper::mapToDto)
				.toList();
		
		return new ResponseEntity<>(sectionDtos, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SectionDto> getSection(@PathVariable Integer sectionId) {
		Section section = sectionService.getSectionById(sectionId);
		return new ResponseEntity<>(SectionMapper.mapToDto(section), HttpStatus.OK);
	}
}