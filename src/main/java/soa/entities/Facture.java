package soa.entities;



import jakarta.persistence.*;

import java.util.List;

@Entity
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFacture")
    private Long idFacture;
    private int num;
    private Boolean Status;
    private double mpFacture;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idFacture", referencedColumnName = "idFacture")
    private List<Reglement> ReglementList;


    public Facture( ) {

    }

    public Facture(int num, Boolean status, double mpFacture) {
        this.num = num;
        Status = status;
        this.mpFacture = mpFacture;
    }

    public Long getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(Long idFacture) {
        this.idFacture = idFacture;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Boolean getStatus() {
        return Status;
    }

    public boolean setStatus(Boolean status) {
        return Status = status;

    }

    public double getMpFacture() {

        return mpFacture;
    }

    public void setMpFacture(double mpFacture) {
        this.mpFacture = mpFacture;
    }

    public List<Reglement> getReglementList() {
        return ReglementList;
    }

    public void setReglementList(List<Reglement> reglementList) {
        ReglementList = reglementList;
    }
}
