package com.vunlph30245.assignment.Model;

public class Products {
    private int masp;
    private String tensp;
    private int gia;
    private int soluong;
    private String img;


    public Products(int masp, String tensp, int gia, int soluong, String string) {
        this.masp = masp;
        this.tensp = tensp;
        this.gia = gia;
        this.soluong = soluong;
        this.img = img;
    }


    public Products(String tensp, int gia, int soluong, String img) {
        this.tensp = tensp;
        this.gia = gia;
        this.soluong = soluong;
        this.img = img;
    }


    public Products(String tensp, int gia, int soluong) {
        this.tensp = tensp;
        this.gia = gia;
        this.soluong = soluong;
    }


    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
