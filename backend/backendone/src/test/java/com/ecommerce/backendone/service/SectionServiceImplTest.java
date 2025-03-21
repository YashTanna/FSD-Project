package com.ecommerce.backendone.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecommerce.backendone.entity.Section;
import com.ecommerce.backendone.exception.ResourceNotFoundException;
import com.ecommerce.backendone.repository.SectionRepository;
import com.ecommerce.backendone.service.impl.SectionServiceImpl;

@ExtendWith(MockitoExtension.class)
public class SectionServiceImplTest {	
	@Mock
	private SectionRepository sectionRepository;
	@InjectMocks
	private SectionServiceImpl underTest;
	
	@Test
	void getAllSections_ShouldReturnSections() {
		Section section1 = Section.builder().sectionId(1).build();
		Section section2 = Section.builder().sectionId(2).build();
		List<Section> expectedSections = List.of(section1, section2);
		
		when(sectionRepository.findAll()).thenReturn(expectedSections);
		
		List<Section> actualSections = underTest.getAllSections();
		
		assertEquals(expectedSections, actualSections);
	}
	
	@Test
	void getSectionById_ShouldReturnSection_UponValidId() {
		Section expectedSection = Section.builder()
				.sectionId(1)
				.sectionName("Electronics")
				.build();
		
		when(sectionRepository.findById(1)).thenReturn(Optional.of(expectedSection));
		
		Section actualSection = underTest.getSectionById(1);
		
		assertEquals(expectedSection, actualSection);
	}
	
	@Test
	void getSectionById_ShouldThrowResourceNotFoundException_UponInvalidId() {
		when(sectionRepository.findById(1)).thenReturn(Optional.empty());
		
		assertThrows(ResourceNotFoundException.class, () -> underTest.getSectionById(1));
	}

}
