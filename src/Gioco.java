import java.util.Observable;
import java.util.Observer;

public class Gioco implements Observer
{
	private String id;
	private String nome;
	private String categoria;
	private int costo;
	private int giocatori;
	private int dadi;
	private int carte;
	private int pedine;
	private long ultimaPrenotazione;
	private boolean prenotato;
	private boolean disponibile;
	
	public Gioco(String i, String n, String ca, int co, int g, int d, int car, int ped)
	{
		id = i;
		nome = n;
		categoria = ca;
		costo = co;
		giocatori = g;
		dadi = d;
		carte = car;
		pedine = ped;
		ultimaPrenotazione = 0;
		prenotato = false;
		disponibile = true;
	}

	public String getId()
	{
		return id;
	}

	public String getNome()
	{
		return nome;
	}
	
	public String getCategoria()
	{
		return categoria;
	}
	
	public int getCosto()
	{
		return costo;
	}
	
	public int getGiocatori()
	{
		return giocatori;
	}
	
	public int getDadi()
	{
		return dadi;
	}
	
	public int getCarte()
	{
		return carte;
	}
	
	public int getPedine()
	{
		return pedine;
	}
	
	public long getUltimaPrenotazione()
	{
		return ultimaPrenotazione;
	}
	
	public boolean isPrenotato()
	{
		return prenotato;
	}
	
	public boolean isDisponibile()
	{
		return disponibile;
	}
	
	public void setPrenotato()
	{
		prenotato = true;
	}
	
	public void setDisponibile()
	{
		disponibile = true;
	}
	
	public void setNonDisponibile()
	{
		disponibile = false;
	}
	
	public void setUltimaPrenotazione(long u)
	{
		ultimaPrenotazione = u;
	}

	@Override
	public void update(Observable o, Object arg) 
	{
		this.setDisponibile();
	}
}