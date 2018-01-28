package com.roboxandr.Roboxandr;

import java.io.IOException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.roboxandr.Roboxandr.googledrive.GoogleDrive;
import com.roboxandr.Roboxandr.googledrive.GoogleDriveImpl;
import com.roboxandr.Roboxandr.models.Album;
import com.roboxandr.Roboxandr.models.Photo;

@Repository
public class PhotoDAOImpl implements PhotoDAO {

	private static final Logger LOG = LoggerFactory.getLogger(PhotoDAOImpl.class);
	private SessionFactory mFactory;

	public void setFactory(SessionFactory factory) {
		this.mFactory = factory;
	}

	@Override
	public void add¿lbum(Album album) {
		Session session = this.mFactory.getCurrentSession();
		GoogleDrive googleDrive = new GoogleDriveImpl();
		String id;
		try {
			id = googleDrive.generateGoogleDriveId();
			album.setAlbumFolderId(id);
			googleDrive.createAlbumFolder(album.getName(), album.getAlbumFolderId());
		} catch (IOException e) {
			e.printStackTrace();
		}
		session.persist(album);

	}

	@Override
	public void remove¿lbum(int id) {
		Session session = this.mFactory.getCurrentSession();
		Album album = getAlbum(id);
		if (album != null) {
			GoogleDrive googleDrive = new GoogleDriveImpl();
			try {
				List<Photo> photos = getPhotosByGoogleDriveAlbumId(album.getAlbumFolderId());
				for (Photo photo : photos) {
					session.delete(photo);
				}
				googleDrive.removeAlbumFolder(album.getAlbumFolderId());
			} catch (IOException e) {
				e.printStackTrace();
			}
			session.delete(album);
		}
	}

	@Override
	public void update¿lbum(Album album) {
		Session session = this.mFactory.getCurrentSession();
		session.update(album);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Album> getAlbums() {
		Session session = this.mFactory.getCurrentSession();
		List<Album> albums = session.createQuery("from Album").list();
		return albums;
	}

	@Override
	public Album getAlbum(int id) {
		Session session = this.mFactory.getCurrentSession();
		Album album = (Album) session.load(Album.class, new Integer(id));
		return album;
	}

	@Override
	public void addPhoto(String googleDriveAlbumId, String googleDriveFileId) {
		Session session = this.mFactory.getCurrentSession();
		Photo photo = new Photo();
		photo.setGoogleDriveAlbumId(googleDriveAlbumId);
		photo.setLinkToPhoto(googleDriveFileId);
		session.persist(photo);
	}

	@Override
	public void removePhotos(List<String> googleDriveIds) {
		Session session = this.mFactory.getCurrentSession();
		GoogleDrive googleDrive = new GoogleDriveImpl();
		for (String id : googleDriveIds) {
			Photo photo = (Photo) session.createQuery("from Photo where linkToPhoto='" + id + "'").uniqueResult();
			session.delete(photo);
			try {
				googleDrive.removePhoto(id);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Photo> getPhotosByGoogleDriveAlbumId(String googleDriveAlbumId) {
		Session session = this.mFactory.getCurrentSession();
		List<Photo> photos = session.createQuery("from Photo where googleDriveAlbumId='" + googleDriveAlbumId + "'")
				.list();
		return photos;
	}
}
