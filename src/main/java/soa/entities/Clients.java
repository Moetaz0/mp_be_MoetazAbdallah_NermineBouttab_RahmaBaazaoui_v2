package soa.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity

public class Clients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ClientId")
    private Long ClientId;
    private String Adress;
    private String Name;
    private String Surname;
    private int tel;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ClientId", referencedColumnName = "ClientId")
    private List<Facture> factureList;
    public Clients ()
    {

    }

    public Clients(String adress, String name, String surname, int tel, List<Facture> factureList) {
        Adress = adress;
        Name = name;
        Surname = surname;
        this.tel = tel;
        this.factureList = factureList;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public List<Facture> getFactureList() {
        return factureList;
    }

    public void setFactureList(List<Facture> factureList) {
        this.factureList = factureList;
    }



}
