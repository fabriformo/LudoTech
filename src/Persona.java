
public abstract class Persona 
{
	private String nome;
	private String cognome;
	private String cellulare;
	private String id;
	
	public Persona(String n, String co, String ce, String i)
	{
		nome = n;
		cognome = co;
		cellulare = ce;
		id = i;
	}
	
	public String getId()
	{
		return id;
	}
	
	public String getNome()
	{
		return nome;
	}
	
	public String getCognome()
	{
		return cognome;
	}
	
	public String getCellulare()
	{
		return cellulare;
	}
}
