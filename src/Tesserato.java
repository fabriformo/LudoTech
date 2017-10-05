
import java.io.IOException;
import java.util.Date;

public class Tesserato extends Persona
{
	private int id_tessera;
	private long ultimo_rinnovo;
	
	public Tesserato(String i, String n, String co, String ce, int it, long ur)
	{
		super(i, n, co, ce);
		id_tessera = it;
		ultimo_rinnovo = ur;
		
		//TODO inserisci tessera sia nella lista che nel db
	}
	
	public int getIdTessera()
	{
		return id_tessera;
	}
	
	public long getUltimoRinnovo()
	{
		return ultimo_rinnovo;
	}
	
	public boolean getValiditaTessera()
	{
		Date d = new Date();
		
		long oggi = d.getTime();
		
		double giorni = (oggi-ultimo_rinnovo)/(24*3600*1000);
		System.out.println("------------------------------------");
		System.out.println("Il tesserato e' iscritto da "+giorni+" giorni");
		if(giorni <= 30.0)
		{	
			System.out.println("TESSERA VALIDA");
			System.out.println("------------------------------------");
			return true;
		}
		else
		{
			System.out.println("TESSERA NON VALIDA");
			System.out.println("------------------------------------");
			return false;
		}
	}
	
	public void prenotaGioco(Gioco x)
	{	
		x.setPrenotato();
		x.setUltimaPrenotazione(new Date().getTime());
	}
}

/*//inserisco la entry nel database dei membri
FileWriter w;
w = new FileWriter("prova.txt", true);

BufferedWriter b;
b = new BufferedWriter(w);

b.write("\n"+n+";"+co+";"+ce+";"+(ultima_tessera+1));
b.close();*/


/*
//inserisco la entry nella lista delle tessere
Main.lista_tessere.add(tessera);


//inserisco la entry nel database delle tessere
w = new FileWriter("prova2.txt", true);
b = new BufferedWriter(w);

b.write(tessera.getId()+";"+tessera.getUltimoRinnovo()+";"+tessera.getCosto());
b.close();*/
