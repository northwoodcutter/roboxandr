package com.roboxandr.Roboxandr.models;

import javax.persistence.*;

import org.hibernate.annotations.Proxy;

@Proxy(lazy=false)
@Entity
@Table(name = "photos")
public class Photo {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name = "definition")
	private String definition;
	@Column(name = "link_to_photo")
	private String linkToPhoto;
	@Column(name = "rating")
	private int rating;
	@Column(name = "google_drive_album_id")
	private String googleDriveAlbumId;

	
	public String getGoogleDriveAlbumId() {
		return googleDriveAlbumId;
	}

	public void setGoogleDriveAlbumId(String googleDriveAlbumId) {
		this.googleDriveAlbumId = googleDriveAlbumId;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public String getLinkToPhoto() {
		return linkToPhoto;
	}

	public void setLinkToPhoto(String linkToPhoto) {
		this.linkToPhoto = linkToPhoto;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
}
