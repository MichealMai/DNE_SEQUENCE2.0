package bean;

import annotation.Table;
import annotation.column;

@Table(tableName="DS_Experiments")
public class Experiments {
	
	//Define data column
	@column(type="VARCHAR(100)",field="expid",primaryKey=true,defaultNull=false)
	private String expid;       //Experiment primary key
	@column(type="VARCHAR(100)",field="algid")
	private String algid;       //Algorithm id
	@column(type="VARCHAR(100)",field="userid")
	private String userid;       //user id
	@column(type="VARCHAR(1000)",field="inputfileid")
	private String inputfileid;       //input file id
	@column(type="VARCHAR(1000)",field="phaseonefileid")
	private String phaseonefileid;       //file id in phase one
	@column(type="VARCHAR(1000)",field="phasetwofileid")
	private String phasetwofileid;       // file id in phase two
	@column(type="VARCHAR(1000)",field="phasethreefileid")
	private String phasethreefileid;       //file id in phase three
	@column(type="VARCHAR(1000)",field="phasefourfileid")
	private String phasefourfileid;       //file id in phase four
	@column(type="VARCHAR(50)",field="expname")
	private String expname;      //experiment name
	@column(type="VARCHAR(50)",field="fn")
	private String fn;      //FN
	@column(type="VARCHAR(50)",field="fp")
	private String fp;      //FP
	@column(type="VARCHAR(50)",field="phase")
	private String phase;      //Phase
	@column(type="VARCHAR(50)",field="motifsnumber")
	private String motifsnumber;      //motifs number
	@column(type="VARCHAR(100)",field="maximummotifs")
	private String maximummotifs;      //maximum motifs number
	@column(type="VARCHAR(50)",field="featureselection")
	private String featureselection;      //featrue selection
	@column(type="VARCHAR(50)",field="extrafeature")
	private String extrafeature;      //Add extra feature
	@column(type="VARCHAR(100)",field="kfold")
	private String kfold;      //K Fold value
	@column(type="VARCHAR(100)",field="cvalue")
	private String cvalue;      //The value of c
	@column(type="VARCHAR(100)",field="numberofsequences")
	private String numberofsequences;      //The number of sequences
	@column(type="VARCHAR(100)",field="desiredclass")
	private String desiredclass;      //Desired class
	public String getKfold() {
		return kfold;
	}
	public void setKfold(String kfold) {
		this.kfold = kfold;
	}
	public String getCvalue() {
		return cvalue;
	}
	public void setCvalue(String cvalue) {
		this.cvalue = cvalue;
	}
	public String getNumberofsequences() {
		return numberofsequences;
	}
	public void setNumberofsequences(String numberofsequences) {
		this.numberofsequences = numberofsequences;
	}
	public String getDesiredclass() {
		return desiredclass;
	}
	public void setDesiredclass(String desiredclass) {
		this.desiredclass = desiredclass;
	}
	public String getMaximummotifs() {
		return maximummotifs;
	}
	public void setMaximummotifs(String maximummotifs) {
		this.maximummotifs = maximummotifs;
	}
	public String getExpid() {
		return expid;
	}
	public void setExpid(String expid) {
		this.expid = expid;
	}
	public String getAlgid() {
		return algid;
	}
	public void setAlgid(String algid) {
		this.algid = algid;
	}
	public String getPhase() {
		return phase;
	}
	public void setPhase(String phase) {
		this.phase = phase;
	}
	public String getMotifsnumber() {
		return motifsnumber;
	}
	public void setMotifsnumber(String motifsnumber) {
		this.motifsnumber = motifsnumber;
	}
	public String getFeatureselection() {
		return featureselection;
	}
	public void setFeatureselection(String featrueselection) {
		this.featureselection = featrueselection;
	}
	public String getExtrafeature() {
		return extrafeature;
	}
	public void setExtrafeature(String extrafeature) {
		this.extrafeature = extrafeature;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getInputfileid() {
		return inputfileid;
	}
	public void setInputfileid(String inputfileid) {
		this.inputfileid = inputfileid;
	}
	public String getPhaseonefileid() {
		return phaseonefileid;
	}
	public void setPhaseonefileid(String phaseonefileid) {
		this.phaseonefileid = phaseonefileid;
	}
	public String getPhasetwofileid() {
		return phasetwofileid;
	}
	public void setPhasetwofileid(String phasetwofileid) {
		this.phasetwofileid = phasetwofileid;
	}
	public String getPhasethreefileid() {
		return phasethreefileid;
	}
	public void setPhasethreefileid(String phasethreefileid) {
		this.phasethreefileid = phasethreefileid;
	}
	public String getPhasefourfileid() {
		return phasefourfileid;
	}
	public void setPhasefourfileid(String phasefourfileid) {
		this.phasefourfileid = phasefourfileid;
	}
	public String getExpname() {
		return expname;
	}
	public void setExpname(String expname) {
		this.expname = expname;
	}
	public String getFn() {
		return fn;
	}
	public void setFn(String fn) {
		this.fn = fn;
	}
	public String getFp() {
		return fp;
	}
	public void setFp(String fp) {
		this.fp = fp;
	}


}
