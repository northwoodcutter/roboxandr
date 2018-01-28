package com.roboxandr.Roboxandr.models;

import javax.persistence.*;

import org.hibernate.annotations.Proxy;

@Proxy(lazy=false)
@Entity
@Table(name="albums")
public class Album {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="name")
	private String name;
    @Column(name="definition")
	private String definition;
    @Column(name="preview_photo_id")
	private String previewPhotoId;
    @Column(name="published")
    private boolean published;
    @Column(name="album_folder_id")
    private String albumFolderId;
    
	public String getPreviewPhotoId() {
		return previewPhotoId;
	}

	public void setPreviewPhotoId(String previewPhotoId) {
		this.previewPhotoId = previewPhotoId;
	}

	public String getAlbumFolderId() {
		return albumFolderId;
	}

	public void setAlbumFolderId(String albumFolderId) {
		this.albumFolderId = albumFolderId;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public String getLinkToPreviewPhoto() {
		return previewPhotoId;
	}

	public void setLinkToPreviewPhoto(String linkToPreviewPhoto) {
		this.previewPhotoId = linkToPreviewPhoto;
	}
}
