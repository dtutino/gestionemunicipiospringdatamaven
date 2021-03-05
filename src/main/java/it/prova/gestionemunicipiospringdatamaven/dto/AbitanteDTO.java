package it.prova.gestionemunicipiospringdatamaven.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import it.prova.gestionemunicipiospringdatamaven.model.Abitante;
import it.prova.gestionemunicipiospringdatamaven.model.Municipio;

public class AbitanteDTO {
	
	private Long id;
	private String nome;
	private String cognome;
	private Integer eta;
	private String residenza;
	private Municipio municipio;
	
	public AbitanteDTO() {
		super();
	}

	public AbitanteDTO(String nome, String cognome, Integer eta, String residenza, Municipio municipio) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.eta = eta;
		this.residenza = residenza;
		this.municipio = municipio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Integer getEta() {
		return eta;
	}

	public void setEta(Integer eta) {
		this.eta = eta;
	}

	public String getResidenza() {
		return residenza;
	}

	public void setResidenza(String residenza) {
		this.residenza = residenza;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
	
	public List<String> errors(){
		List<String> result = new ArrayList<String>();
		if(StringUtils.isBlank(this.nome))
			result.add("Il campo nome non può essere vuoto");
		if(StringUtils.isBlank(this.cognome))
			result.add("Il campo cognome non può essere vuoto");
		if(this.eta < 1)
			result.add("Il campo età non può essere vuoto");
		if(StringUtils.isBlank(this.residenza))
			result.add("Il campo residenza non può essere vuoto");
		if(this.municipio == null)
			result.add("Il campo municipio non può essere vuoto");
		
		return result;
	}

	public static Abitante buildModelFromDto(AbitanteDTO abitanteDTO) {
		Abitante result = new Abitante();
		result.setId(abitanteDTO.getId());
		result.setNome(abitanteDTO.getNome());
		result.setCognome(abitanteDTO.getCognome());
		result.setEta(abitanteDTO.getEta());
		result.setResidenza(abitanteDTO.getResidenza());
		result.setMunicipio(abitanteDTO.getMunicipio());
		return result;
	}

}
