package com.roboxandr.Roboxandr.googledrive;

import com.google.api.services.drive.Drive;
import com.google.api.client.auth.oauth2.Credential;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Ivan Grishko
 */
public interface GoogleDrive {
	/**
	 * Creates an authorized Credential object.
	 * 
	 * @return an authorized Credential object.
	 * @throws IOException
	 */
	public Credential authorize() throws IOException;

	/**
	 * Build and return an authorized Drive client service.
	 * 
	 * @return an authorized Drive client service
	 * @throws IOException
	 */
	public Drive getDriveService() throws IOException;

	public String uploadFile(MultipartFile file, String googleDriveAlbumId) throws IOException;

	public void removeAlbumFolder(String folderId) throws IOException;

	public void createAlbumFolder(String name, String albumFolderId);
	
	public String generateGoogleDriveId() throws IOException; 
	
	List<String> getIdsOfFilesInFolder(String folderId);
	
	public void removePhoto(String googleDriveId) throws IOException;
}
