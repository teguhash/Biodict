package com.hift.biodict;

/**
 * Created by TeguhAS on 25-Jul-17.
 */

public class DictObject {
    private String indonesiaName;
    private String englishName;
    private String latinName;

    //for classification tree
    private String kingdom;
    private String subKingdom;
    private String superDivisio;
    private String divisio;
    private String kelas;
    private String subKelas;
    private String ordo;
    private String famili;
    private String genus;
    private String spesies;

    private String morphology;
    private String manfaat;

    private byte[] imageBlob;

    public String getIndonesiaName() {
        return indonesiaName;
    }

    public void setIndonesiaName(String indonesiaName) {
        this.indonesiaName = indonesiaName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

    public String getKingdom() {
        return kingdom;
    }

    public void setKingdom(String kingdom) {
        this.kingdom = kingdom;
    }

    public String getSubKingdom() {
        return subKingdom;
    }

    public void setSubKingdom(String subKingdom) {
        this.subKingdom = subKingdom;
    }

    public String getSuperDivisio() {
        return superDivisio;
    }

    public void setSuperDivisio(String superDivisio) {
        this.superDivisio = superDivisio;
    }

    public String getDivisio() {
        return divisio;
    }

    public void setDivisio(String divisio) {
        this.divisio = divisio;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getSubKelas() {
        return subKelas;
    }

    public void setSubKelas(String subKelas) {
        this.subKelas = subKelas;
    }

    public String getOrdo() {
        return ordo;
    }

    public void setOrdo(String ordo) {
        this.ordo = ordo;
    }

    public String getFamili() {
        return famili;
    }

    public void setFamili(String famili) {
        this.famili = famili;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public String getSpesies() {
        return spesies;
    }

    public void setSpesies(String spesies) {
        this.spesies = spesies;
    }

    public String getMorphology() {
        return morphology;
    }

    public void setMorphology(String morphology) {
        this.morphology = morphology;
    }

    public String getManfaat() {
        return manfaat;
    }

    public void setManfaat(String manfaat) {
        this.manfaat = manfaat;
    }

    public byte[] getImageBlob() {
        return imageBlob;
    }

    public void setImageBlob(byte[] imageBlob) {
        this.imageBlob = imageBlob;
    }
}
