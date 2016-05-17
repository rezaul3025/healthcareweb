package org.healthcare.persistence.service;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.healthcare.domain.Doctor;
import org.healthcare.domain.DoctorSpecialization;
import org.healthcare.domain.ImageMeta;
import org.healthcare.domain.Specialization;
import org.healthcare.domain.User;
import org.healthcare.persistence.repository.DoctorRepository;
import org.healthcare.persistence.repository.DoctorSpecializationRepository;
import org.healthcare.persistence.repository.ImageRepository;
import org.healthcare.persistence.repository.OpeningTimeRepository;
import org.healthcare.persistence.repository.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service
public class DoctorServiceHandler implements DoctorService {
	
	private static final Logger LOG = Logger.getLogger(DoctorServiceHandler.class);
	
	private static final String IMAGE_MIME_TYPE_INDICATOR = "image";

    @Autowired
    private DoctorRepository doctorRespository;

    @Autowired
    private SpecializationRepository specializationRepository;

    @Autowired
    private DoctorSpecializationRepository doctorSpecializationRepository;
    
    @Autowired
    private ImageRepository imageRepository;
    
    @Autowired
    private UserService userService;
    
    @Value("${doctor_image_path}")
	private String imageUploadPath;

    @Override
    public Doctor add(Doctor doctor) {

        Set<DoctorSpecialization> doctorSpecializations = new HashSet<DoctorSpecialization>();
	
        doctorRespository.save(doctor);

        if (doctor.getSpecializations() != null) {

            List<String> specializations = doctor.getSpecializations();
            for (Specialization sp : specializationRepository.getSpecializationByNames(specializations)) {
                doctorSpecializationRepository.save(new DoctorSpecialization(doctor, sp));
            }

            doctor.setDoctorSpecializations(doctorSpecializations);
        }

        if (doctor.getUser() != null && doctor.getUser().getEmail() != null && doctor.getUser().getPassword() != null) {
            doctor.getUser().setParentId(doctor.getId());
            userService.addUser(doctor.getUser());
        }
        
        
        /*if(doctor.getOpeningTime() != null){
        	doctor.getOpeningTime().setDoctor(doctor);
        	openingTimeRepository.save(doctor.getOpeningTime());
        }
*/
        return doctor;
    }

    @Override
    public Doctor getDoctorById(Long id) {
    	Doctor doctor = doctorRespository.findOne(id);
    	
    	if(doctor != null){
    		User user = userService.getUserByParent(doctor.getId());
    		doctor.setUser(user);
    	}
    	
        return doctorRespository.findOne(id);
    }

	@Override
	public void imageUpload(MultipartHttpServletRequest request, Long id) {
		Iterator<String> itr = request.getFileNames();
		//String rootPath = System.getProperty("catalina.home");
		//String imageUploadPath = env.getProperty("business_image_path");
		
		while (itr.hasNext())
		{
			String uploadedFile = itr.next();
			MultipartFile file = request.getFile(uploadedFile);
			String mimeType = file.getContentType();
			String filename = file.getOriginalFilename();
			
			try
			{
				byte[] bytes = file.getBytes();

				File dir = new File(imageUploadPath+"/"+id);
				
				if (!dir.exists())
				{
					dir.mkdirs();
				}

				if (dir.exists())
				{
					UUID randomuuId = UUID.randomUUID();
					String originalPath = dir.getAbsolutePath() + "/" + randomuuId +"_"+ filename;
					String webPath = "/static/" + id+"/"+randomuuId +"_"+ filename;
					/*
					if(!servletContextPath.isEmpty())
					{
						if(servletContextPath.startsWith("/"))
						{
							webPath=servletContextPath+webPath;
						}
						else
						{
							webPath="/"+servletContextPath+webPath;
						}
					}*/
					
					// Create the file on server
					File serverFile = new File(originalPath);

					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();

					String savePath = serverFile.getPath();
					Integer width = null;
					Integer height = null;
					
					if(mimeType.startsWith(IMAGE_MIME_TYPE_INDICATOR))
					{
						BufferedImage image = ImageIO.read(file.getInputStream());
						width = image.getWidth();
						height = image.getHeight();
					}
					
					Doctor doctor = doctorRespository.findOne(id);
				
					ImageMeta imageMeta = new ImageMeta(doctor, webPath, originalPath, height, width, mimeType, file.getSize() ,filename);
					
					ImageMeta oldImage = imageRepository.findByDoctor(doctor);
					
					if(oldImage != null){
						File oldImgFile = new File(oldImage.getOriginalPath());
						imageMeta.setId(oldImage.getId());
						if(oldImgFile != null && oldImgFile.delete()){
							LOG.info("Old image found and deleted");
						}
					}
					
					imageRepository.save(imageMeta);
				}
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<String> findCities() {
		return doctorRespository.findCities();
	}

}
