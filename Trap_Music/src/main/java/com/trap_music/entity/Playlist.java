package com.trap_music.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Playlist {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String name;
	public Playlist() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Playlist(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Playlist [id=" + id + ", name=" + name + "]";
	}
    
    
}
