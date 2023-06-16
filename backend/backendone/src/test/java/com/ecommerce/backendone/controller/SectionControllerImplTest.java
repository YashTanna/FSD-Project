package com.ecommerce.backendone.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ecommerce.backendone.controller.impl.SectionControllerImpl;
import com.ecommerce.backendone.dto.SectionDto;
import com.ecommerce.backendone.dto.mapper.SectionMapper;
import com.ecommerce.backendone.entity.Section;
import com.ecommerce.backendone.service.SectionService;

@ExtendWith(MockitoExtension.class)
public class SectionControllerImplTest {
	@Mock
	private SectionService sectionService;
	@InjectMocks
	private SectionControllerImpl underTest;
	
	@Test
	void getAllSections_ShouldReturnSectionDtos() {
		List<Section> sections = List.of(
				Section.builder().sectionId(1).categories(Collections.emptyList()).build(),
				Section.builder().sectionId(2).categories(Collections.emptyList()).build(),
				Section.builder().sectionId(3).categories(Collections.emptyList()).build()
				);
		
		List<SectionDto> expectedSectionDtos = sections.stream().map(SectionMapper::mapToDto).toList();
		
		when(sectionService.getAllSections()).thenReturn(sections);
		
		ResponseEntity<List<SectionDto>> response = underTest.getAllSections();
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedSectionDtos, response.getBody());
	}
	
	@Test
	void getSection_ShouldReturnSectionDto_UponValidInput() {
		Integer sectionId = 1;
		Section section = Section.builder().sectionId(sectionId).categories(Collections.emptyList()).build();
		
		SectionDto expectedSectionDto = SectionMapper.mapToDto(section);
		
		when(sectionService.getSectionById(sectionId)).thenReturn(section);
		
		ResponseEntity<SectionDto> response = underTest.getSection(sectionId);
		
		assertEquals(HttpStatus.OK, response.getStatusCode())
;		assertEquals(expectedSectionDto, response.getBody());
	}
}
