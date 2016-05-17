package org.healthcare.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity(name = "image_meta")
public class ImageMeta {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", length = Integer.MAX_VALUE, nullable = false)
	private Long id;

	@OneToOne(cascade = {CascadeType.REMOVE,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH})
	@JsonBackReference
	@JoinColumn(name = "doctor_id",referencedColumnName = "id")
	private Doctor doctor;

	@Column(name = "web_path", length = 255, nullable = false)
	private String webPath;

	@Column(name = "original_path", length = 255, nullable = true)
	private String originalPath;

	@Column(name = "height", length = 10, nullable = false)
	private Integer height;

	@Column(name = "width", length = 10, nullable = false)
	private Integer width;

	@Column(name = "format", length = 50, nullable = true)
	private String format;

	@Column(name = "size", length = 10, nullable = false)
	private Long size;

	@Column(name = "name", length = 126, nullable = false)
	private String name;

	public ImageMeta() {

	}

	public ImageMeta(Doctor doctor, String webPath, String originalPath, Integer height, Integer width, String format, Long size, String name) {
		this.doctor = doctor;
		this.webPath = webPath;
		this.originalPath = originalPath;
		this.height = height;
		this.width = width;
		this.format = format;
		this.size = size;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWebPath() {
		return webPath;
	}

	public void setWebPath(String webPath) {
		this.webPath = webPath;
	}

	public String getOriginalPath() {
		return originalPath;
	}

	public void setOriginalPath(String originalPath) {
		this.originalPath = originalPath;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
}
