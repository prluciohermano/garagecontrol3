package br.com.garagecontrol.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.garagecontrol.model.Post;
import br.com.garagecontrol.repository.PostRepository;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

@Service
public class LoadTestDataService {

	@Autowired
	private PostRepository postRepository;

	@Transactional
	public void loadPosts() {
		List<Post> posts = new ArrayList<>();
		for (int i = 0; i < 1000; i++) {
			Lorem lorem = LoremIpsum.getInstance();
			Post post = new Post(lorem.getTitle(2, 4), lorem.getWords(10, 40));
			posts.add(post);
		}
		this.postRepository.saveAll(posts);
	}

	public void deletePosts() {
		this.postRepository.deleteAll();
	}
}
