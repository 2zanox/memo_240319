package com.memo.post.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.memo.post.domain.Post;

@Mapper
public interface PostMapper {
	
	// test용
	public List<Map<String, Object>> selectPostListTest();
	
	public List<Post> selectPostListByUserId(int userId);
	
	public void insertPost(
			@Param("userId") int userId,
			@Param("subject") String subject, 
			@Param("content") String content, 
			@Param("imagePath") String imagePath);
	
	public Post selectPostByPostIdUserId(
			@Param("userId") int userId, 
			@Param("postId") int postId);
}
