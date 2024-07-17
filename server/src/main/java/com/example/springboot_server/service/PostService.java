package com.example.springboot_server.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import com.example.springboot_server.api.models.Post;

@Service
public class PostService {
	public List<Post> posts;

	public PostService() {
		posts = new ArrayList<>(
				Arrays.asList(
						new Post(1, 2, "Desert Day",
								"https://img-s-msn-com.akamaized.net/tenant/amp/entityid/BB1msIoO.img",
								new ArrayList<>(Arrays.asList(1, 3, 2))),
						new Post(2, 2, "Lady gratisography",
								"https://gratisography.com/wp-content/uploads/2024/03/gratisography-holographic-suit-1170x780.jpg",
								new ArrayList<>(Arrays.asList(1, 3, 2))),
						new Post(3, 2, "Lowlife to Afterlife",
								"https://gratisography.com/wp-content/uploads/2024/01/gratisography-cyber-kitty-800x525.jpg",
								new ArrayList<>(Arrays.asList(1, 3, 2))) //
			)//
		);
	}

	public List<Post> find() {
		return posts;
	}
}
