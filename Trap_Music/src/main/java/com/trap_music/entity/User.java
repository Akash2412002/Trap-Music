package com.trap_music.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;    
    public String name;
    public String email;
    public String password;
    public String gender;
    public String role;
    public boolean premiumAccount;
    
    @ManyToMany
    public List<Song> favorites;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int id, String name, String email, String password, String gender, String role, boolean premiumAccount,
			List<Song> favorites) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.role = role;
		this.premiumAccount = premiumAccount;
		this.favorites = favorites;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isPremiumAccount() {
		return premiumAccount;
	}
	public void setPremiumAccount(boolean premiumAccount) {
		this.premiumAccount = premiumAccount;
	}
	public List<Song> getFavorites() {
		return favorites;
	}
	public void setFavorites(List<Song> favorites) {
		this.favorites = favorites;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", gender="
				+ gender + ", role=" + role + ", premiumAccount=" + premiumAccount + ", favorites=" + favorites + "]";
	}

}
