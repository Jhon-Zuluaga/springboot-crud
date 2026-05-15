package com.hibernate.ferreteria.DTOs;

import java.util.List;

public class SaleDTO {
    private int id;
    private String client;
    private Double total;
    private String date;
    private List<Integer> articleIds;

    public SaleDTO(int id, String client, Double total, String date, List<Integer> articleIds) {
        this.id = id;
        this.client = client;
        this.total = total;
        this.date = date;
        this.articleIds = articleIds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Integer> getArticleIds() {
        return articleIds;
    }

    public void setArticleIds(List<Integer> articleIds) {
        this.articleIds = articleIds;
    }
}
