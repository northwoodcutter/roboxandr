package com.roboxandr.Roboxandr.googledrive;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.*;
import com.google.api.services.drive.Drive;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;


public class GoogleDriveImpl implements GoogleDrive {
	private static final Logger logger = LoggerFactory.getLogger(GoogleDriveImpl.class);
	/** Application name. */
	private static final String APPLICATION_NAME = "Roboxandr";

	/** Directory to store user credentials for this application. */
	private static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"),
			".credentials/drive-java-quickstart");

	/** Global instance of the {@link FileDataStoreFactory}. */
	private static FileDataStoreFactory DATA_STORE_FACTORY;

	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	/** Global instance of the HTTP transport. */
	private static HttpTransport HTTP_TRANSPORT;

	/**
	 * Global instance of the scopes required by this quickstart.
	 *
	 * If modifying these scopes, delete your previously saved credentials at
	 * ~/.credentials/drive-java-quickstart
	 */
	private static final List<String> SCOPES = Arrays.asList(DriveScopes.DRIVE_FILE);

	static {
		try {
			HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
		} catch (Throwable t) {
			t.printStackTrace();
			System.exit(1);
		}
	}
	
	public Credential authorize() throws IOException {
		// Load client secrets.
		InputStream in = GoogleDriveImpl.class.getResourceAsStream("/client_secret.json");
		if (in != null) {
			logger.info("InputStream in  not null");
		}
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				clientSecrets, SCOPES).setDataStoreFactory(DATA_STORE_FACTORY).setAccessType("offline").build();
		Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver.Builder().build())
				.authorize("user8");
		System.out.println("Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
		return credential;
	}

	public Drive getDriveService() throws IOException {
		Credential credential = authorize();
		return new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME).build();
	}

	@Override
	public String uploadFile(MultipartFile file, String googleDriveAlbumId) throws IOException {
		File fileMetadata = new File();
		fileMetadata.setName(file.getOriginalFilename());
		fileMetadata.setParents(Collections.singletonList(googleDriveAlbumId));
		java.io.File filePath = new java.io.File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(filePath);
		fos.write(file.getBytes());
		fos.close();
		FileContent mediaContent = new FileContent("image/jpeg", filePath);
		File file1 = getDriveService().files().create(fileMetadata, mediaContent).setFields("id").execute();
		System.out.println("File ID: " + file1.getId());
		return file1.getId();
	}

	@Override
	public void createAlbumFolder(String name, String albumFolderId) {
		String parentFolderId = "1mvrvn7EVuBsYNUjCiPEajniA243333ed";
		File fileMetadata = new File();
		fileMetadata.setName(name);
		fileMetadata.setParents(Collections.singletonList(parentFolderId));
		fileMetadata.setMimeType("application/vnd.google-apps.folder");
		fileMetadata.setId(albumFolderId);
		try {
			File file = getDriveService().files().create(fileMetadata).setFields("id").execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeAlbumFolder(String folderId) throws IOException {
		if(folderId!=null)
		getDriveService().files().delete(folderId).execute();
	}

	@Override
	public String generateGoogleDriveId() throws IOException {
		GeneratedIds allIds = getDriveService().files().generateIds().setSpace("drive").setCount(1).execute();
		return allIds.getIds().get(0).toString();
	}

	@Override
	public List<String> getIdsOfFilesInFolder(String folderId) {
		String query ="'"+folderId+"' in parents";
		List<String> result = new ArrayList<String>();
		FileList request;
		try {
			request = getDriveService().files().list().setQ(query).execute();
			for (File file : request.getFiles()) {
				result.add(file.getId());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return result;
	}

	@Override
	public void removePhoto(String googleDriveId) throws IOException {
		getDriveService().files().delete(googleDriveId).execute();
	}
}
