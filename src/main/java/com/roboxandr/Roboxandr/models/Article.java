package com.roboxandr.Roboxandr.models;

import java.sql.Date;

import javax.persistence.*;

import org.hibernate.annotations.Proxy;

@Proxy(lazy = false)
@Entity
@Table(name = "articles")
public class Article {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "caption")
	private String caption;
	@Column(name = "hashtags")
	private String hashtags;
	@Column(name = "preview")
	private String preview;
	@Column(name = "content")
	private String content;
	@Column(name = "publication_date")
	private Date publicationDate;
	@Column(name = "album_id")
	private int albumId;
	@Column(name = "published")
	private boolean published;
	@Column(name = "author")
	private String author;
	@Column(name = "link_to_photo")
	private String linkToPhoto;

	public String getLinkToPhoto() {
		return linkToPhoto;
	}

	public void setLinkToPhoto(String linkToPhoto) {
		this.linkToPhoto = linkToPhoto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getHashtags() {
		return hashtags;
	}

	public void setHashtags(String hashtags) {
		this.hashtags = hashtags;
	}

	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public int getAlbumId() {
		return albumId;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
}
