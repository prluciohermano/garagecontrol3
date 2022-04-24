package br.com.garagecontrol.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.garagecontrol.dto.filter.PostFilter;
import br.com.garagecontrol.dto.req.PostReqDTO;
import br.com.garagecontrol.dto.res.PostResDTO;
import br.com.garagecontrol.model.Post;
import br.com.garagecontrol.repository.PostRepository;
import br.com.garagecontrol.specs.PostSpecs;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	private Post save(Post post) {
		return this.postRepository.save(post);
	}

	public Page<PostResDTO> index(Optional<PostFilter> filter, Pageable pageable) {
		Specification<Post> spec = PostSpecs.specByFilter(filter);
		return this.postRepository.findAll(spec, pageable).map(PostResDTO::new);
	}

	public Optional<PostResDTO> show(Long id) {
		return this.postRepository.findById(id).map(PostResDTO::new);
	}

	public Long store(PostReqDTO dto) {
		Post post = dto.toModel(new Post());
		post = this.save(post);
		return post.getId();
	}

	public void update(Long id, PostReqDTO dto) {
		Post post = dto.toModel(this.postRepository.findById(id).get());
		this.save(post);
	}

	public void destroy(Long id) {
		this.postRepository.deleteById(id);
	}
}
