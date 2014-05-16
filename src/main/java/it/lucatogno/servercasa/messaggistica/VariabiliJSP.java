package it.lucatogno.servercasa.messaggistica;

public enum VariabiliJSP {
	MESSAGGIO_SUCCESSO("messaggioSuccesso"), MESSAGGIO_INFO("messaggioInfo"), MESSAGGIO_WARNING("messaggioWarning"), MESSAGGIO_DANGER("messaggioDanger");

	
	private String code;
	
	VariabiliJSP(String code) {
		this.code = code;
	}
	
	
}
