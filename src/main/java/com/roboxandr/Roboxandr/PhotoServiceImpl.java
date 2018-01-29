package com.roboxandr.Roboxandr;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.roboxandr.Roboxandr.models.Album;
import com.roboxandr.Roboxandr.models.Photo;

public class PhotoServiceImpl implements PhotoService {

	private PhotoDAO photoDAO;

	public void setPhotoDAO(PhotoDAO photoDAO) {
		this.photoDAO = photoDAO;
	}

	@Override
	@Transactional
	public void addAlbum(Album album) {
		this.photoDAO.addAlbum(album);
	}

	@Override
	@Transactional
	public void removeAlbum(int id) {
		this.photoDAO.removeAlbum(id);
	}

	@Override
	@Transactional
	public void updateAlbum(Album album) {
		this.photoDAO.updateAlbum(album);
	}

	@Override
	@Transactional
	public Album getAlbum(int id) {
		return this.photoDAO.getAlbum(id);
		
	}

	@Override
	@Transactional
	public void addPhoto(String googleDriveAlbumId, String googleDriveFileId) {
		this.photoDAO.addPhoto(googleDriveAlbumId, googleDriveFileId);
	}

	@Override
	@Transactional
	public void removePhotos(List<String> googleDriveIds) {
		this.photoDAO.removePhotos(googleDriveIds);
	}

	@Override
	@Transactional
	public List<Album> getAlbums() {
		return this.photoDAO.getAlbums();
	}

	@Override
	@Transactional
	public List<Photo> getPhotosByGoogleDriveAlbumId(String googleDriveAlbumId) {
		return this.photoDAO.getPhotosByGoogleDriveAlbumId(googleDriveAlbumId);
	}
}
