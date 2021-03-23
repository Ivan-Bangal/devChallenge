package com.night.countryapi.countryapi.model;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.lang.NonNull;






/**
 * Country
 */
@Entity
public class Country {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;

    @Column(unique = true)
    @NonNull
    private String nome;

    @Column
    @NonNull
    private Continent continente;

    @Column
    @NonNull
    private String capital;

    @Column
    private String regiao;

    public Country() {
        
    }


    public Country (String nome,Continent continente, String regiao,String capital) {
        this.nome = nome;
        this.continente =continente;
        this.regiao = regiao;
        this.capital = capital;

    }


    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

   
    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the regiao
     */
    public String getRegiao() {
        return regiao;
    }

    /**
     * @return the continente
     */
    public Continent getContinente() {
        return continente;
    }

    /**
     * @return the capital
     */
    public String getCapital() {
        return capital;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public void setContinente(Continent continente) {
        this.continente = continente;
    }

    /**
     * @param capital the capital to set
     */
    public void setCapital(String capital) {
        this.capital = capital;
    }

    public void updateAtributes(Country country){
       
        this.continente = country.getContinente() != null ? country.getContinente() : this.continente;
        this.nome = country.getNome() != null ? country.getNome() : this.nome;
        this.regiao = country.getRegiao() != null ? country.getRegiao() : this.regiao;
        this.capital = country.getCapital() != null ? country.getCapital() : this.capital;   
    }

    public boolean hasNullValues(){
        return continente != null && !nome.isEmpty() && !capital.isEmpty() && !nome.equals("") && !capital.equals("");
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Country)) {
            return false;
        }
        Country country = (Country) o;
        return id == country.id && Objects.equals(nome, country.nome) && Objects.equals(regiao, country.regiao) && Objects.equals(capital, country.capital) && Objects.equals(continente, country.continente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, regiao,capital);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nome='" + getNome() + "'" +
            ", regiao='" + getRegiao() + "'" +
            "}";
    }


    

}