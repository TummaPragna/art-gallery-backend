package com.artgallery.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String artworkTitle;
    private String buyerName;
    private String status;

    // ✅ CONSTRUCTOR
    public OrderEntity() {
        this.status = "Pending";
    }

    // ✅ GETTERS

    public Long getId() {
        return id;
    }

    public String getArtworkTitle() {
        return artworkTitle;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public String getStatus() {
        return status;
    }

    // ✅ SETTERS

    public void setId(Long id) {
        this.id = id;
    }

    public void setArtworkTitle(String artworkTitle) {
        this.artworkTitle = artworkTitle;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}