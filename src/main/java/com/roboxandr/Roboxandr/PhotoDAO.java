package com.roboxandr.Roboxandr;

import java.util.List;

import com.roboxandr.Roboxandr.models.Album;
import com.roboxandr.Roboxandr.models.Photo;

public interface PhotoDAO {
	void add¿lbum(Album album);

	void remove¿lbum(int id);

	void update¿lbum(Album album);

	List<Album> getAlbums();

	Album getAlbum(int id);

	void addPhoto(String googleDriveAlbumId, String googleDriveFileId);

	void removePhotos(List<String> googleDriveIds);
	
	List<Photo> getPhotosByGoogleDriveAlbumId(String googleDriveAlbumId);
}
