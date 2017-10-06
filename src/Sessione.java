import java.util.Date;
import java.util.Observable;

public class Sessione extends Observable
{
	private int id;
	private Gioco gioco;
	private Tesserato giocatore;
	private Persona supervisore;
	private long data_inizio;
	private long data_chiusura;
	private boolean aperta;
	
	public Sessione(int i, Gioco g, Tesserato m, Persona s, long d)
	{
		id = i;
		gioco = g;
		giocatore = m;
		supervisore = s;
		aperta = true;
		data_inizio = d;
		data_chiusura = -1;
	}
	
	public int getId()
	{
		return id;
	}
	
	public Gioco getGioco()
	{
		return gioco;
	}
	
	public Tesserato getGiocatore()
	{
		return giocatore;
	}
	
	public Persona getSupervisore()
	{
		return supervisore;
	}
	
	public long getInizio()
	{
		return data_inizio;
	}
	
	public long getChiusura()
	{
		return data_chiusura;
	}
	
	public void setChiusura()
	{
		data_chiusura = new Date().getTime();
	}
	
	public boolean isAperta()
	{
		return aperta;
	}
	
	@Override
    protected synchronized void setChanged() {
        aperta = false;
        this.setChiusura();
        super.setChanged(); //To change body of generated methods, choose Tools | Templates.
    }
}

