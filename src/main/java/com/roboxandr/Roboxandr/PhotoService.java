package com.roboxandr.Roboxandr;

import java.util.List;

import com.roboxandr.Roboxandr.models.Album;
import com.roboxandr.Roboxandr.models.Photo;

public interface PhotoService {
	void addAlbum(Album album);

	void removeAlbum(int id);

	void updateAlbum(Album album);

	List<Album> getAlbums();

	Album getAlbum(int id);

	void addPhoto(String googleDriveAlbumId,String googleDriveFileId);

	void removePhotos(List<String> googleDriveIds);
	
	List<Photo> getPhotosByGoogleDriveAlbumId(String googleDriveAlbumId);
}
