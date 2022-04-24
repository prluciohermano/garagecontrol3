package br.com.garagecontrol.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.garagecontrol.dto.filter.PostFilter;
import br.com.garagecontrol.dto.req.PostReqDTO;
import br.com.garagecontrol.dto.res.PostResDTO;
import br.com.garagecontrol.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	@Autowired
	private PostService postService;

	@GetMapping
	public Page<PostResDTO> index(Optional<PostFilter> filter, @PageableDefault Pageable pageable) {
		return this.postService.index(filter, pageable);
	}

	@GetMapping("/{id}")
	public Optional<PostResDTO> show(@PathVariable Long id) {
		return this.postService.show(id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Long store(@Valid @RequestBody PostReqDTO dto) {
		return this.postService.store(dto);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{id}")
	public void update(@PathVariable Long id, @Valid @RequestBody PostReqDTO dto) {
		this.postService.update(id, dto);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void destroy(@PathVariable Long id) {
		this.postService.destroy(id);
	}
}
