package org.healthcare.persistence.service;

import java.util.Date;

import org.healthcare.domain.Comment;
import org.healthcare.domain.Doctor;
import org.healthcare.domain.User;
import org.healthcare.persistence.repository.CommentsRepository;
import org.healthcare.persistence.repository.DoctorRepository;
import org.healthcare.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceHandler implements CommentService {

	@Autowired
	private CommentsRepository commentRepository;
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Comment add(Comment comment) {
		
		Doctor doctor = doctorRepository.findOne(comment.getDoctorId());
		
		User user = userRepository.findOne(comment.getUserId());
		
		comment.setDoctor(doctor);
		
		comment.setUser(user);
		
		comment.setDate(new Date());
		
		return commentRepository.save(comment);
	}

}
