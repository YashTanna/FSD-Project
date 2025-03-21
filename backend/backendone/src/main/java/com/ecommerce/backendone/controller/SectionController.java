package com.ecommerce.backendone.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.backendone.dto.SectionDto;

import jakarta.validation.constraints.NotNull;

@RequestMapping("/sections")
@Validated
public interface SectionController {
	
	@GetMapping("/")
	ResponseEntity<List<SectionDto>> getAllSections();

    @GetMapping("/{sectionId}")
    ResponseEntity<SectionDto> getSection(@PathVariable @NotNull Integer sectionId);

}
