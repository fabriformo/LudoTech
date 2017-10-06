import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;

public class LudoTech 
{
	private Persona user;
	private static LudoTech instance;
	private LinkedList<Tesserato> lista_tesserati; 
	private LinkedList<Staff> lista_staff;
	private LinkedList<Gioco> lista_giochi;
	private LinkedList<Sessione> lista_sessioni;
	private boolean loggato = false;
	
	public LudoTech()
	{
		lista_staff = new LinkedList<Staff>();
		lista_tesserati = new LinkedList<Tesserato>();
		lista_giochi = new LinkedList<Gioco>();
		lista_sessioni = new LinkedList<Sessione>();
	}
	
	//*****SINGLETON*****
	public static LudoTech getInstance()
	{
        if(instance == null)
            instance = new LudoTech();
        return instance;
	}
	
	public void creaListaStaff() throws IOException
	{
		FileReader f = new FileReader("Staff.txt");
		BufferedReader b = new BufferedReader(f);
		
		String s;
		while(true)
		{
			s = b.readLine();
			if(s==null)
				break;
			
			//creo un array di sottostringhe separate dal carattere ;
			String [] splits = s.split(";");	
			
			//creo un nuovo oggetto staff e lo inserisco nella lista
			Staff x = new Staff(splits[0], splits[1], splits[2], splits[3]);
			lista_staff.add(x);
		}
		b.close();
	}
	
	public void creaListaGiochi() throws IOException
	{		
		FileReader f1 = new FileReader("Giochi.txt");
		BufferedReader b1 = new BufferedReader(f1);
		
		String s1;
		while(true)
		{
			s1 = b1.readLine();
			if(s1==null || s1.equals(""))
				break;
			
			//creo un array di sottostringhe separate dal carattere ;
			String [] splits1 = s1.split(";");
			
			//creo un nuovo oggetto gioco e lo inserisco nella lista
			Gioco x = new Gioco(splits1[0], splits1[1], splits1[2], Integer.parseInt(splits1[3]), Integer.parseInt(splits1[4]), Integer.parseInt(splits1[5]), Integer.parseInt(splits1[6]), Integer.parseInt(splits1[7]));
			lista_giochi.add(x);
		}
		b1.close();
	}
	
	public void creaListaTesserati() throws IOException
	{
		FileReader f = new FileReader("Tesserati.txt");
		BufferedReader b = new BufferedReader(f);
		
		String s;
		while(true)
		{
			s = b.readLine();
			if(s==null || s.equals(""))
				break;
			
			//creo un array di sottostringhe separate dal carattere ;
			String [] splits = s.split(";");	
			//creo un nuovo oggetto tesserat e lo inserisco nella lista
			Tesserato x = new Tesserato(splits[0], splits[1], splits[2], splits[3], Integer.parseInt(splits[4]), Long.parseLong(splits[5]));
			lista_tesserati.add(x);
		}
		b.close();
	}
	
	public Persona getUser()
	{
		return user;
	}
	
	public void setUser(Persona x)
	{
		user = x;
	}
	
	public LinkedList<Staff> getListaStaff()
	{
		return lista_staff;
	}
	
	public LinkedList<Tesserato> getListaTesserati()
	{
		return lista_tesserati;
	}
	
	public LinkedList<Gioco> getListaGiochi()
	{
		return lista_giochi;
	}
	
	public LinkedList<Sessione> getListaSessioni()
	{
		return lista_sessioni;
	}
	
	public void aggiungiStaff(String nom, String cog, String cell, String id) throws IOException
	{
		Staff a = new Staff(nom, cog, cell, id);
		
		lista_staff.add(a);
		
		//inserisco la entry nel database dei membri
		FileWriter w;
		w = new FileWriter("Staff.txt", true);

		BufferedWriter b;
		b = new BufferedWriter(w);

		b.write(nom+";"+cog+";"+cell+";"+id+"\n");
		b.close();
	}
	
	public void eliminaStaff(String id)
	{
		boolean trovato = false;
		int id_el = 0;
		for(int i = 0; i < lista_staff.size(); i++)
		{
			if(lista_staff.get(i).getId().equals(id))
			{
				//lista_staff.remove(i);
				id_el = i;
				trovato = true;
				break;
			}
		}
		if(!trovato)
			System.out.println("Errore. Nessuno staff ha questo ID.");
		else if(confermaEliminazione())
		{
			lista_staff.remove(id_el);
			FileWriter w;
			try {
				w = new FileWriter("Staff_temp.txt", true);
				BufferedWriter b;
				b = new BufferedWriter(w);
				
				for(int i = 0; i < lista_staff.size(); i++)
				{
					b.write(lista_staff.get(i).getNome()+";"+lista_staff.get(i).getCognome()+";"+lista_staff.get(i).getCellulare()+";"+lista_staff.get(i).getId()+"\n");
					/*if(i < (Main.lista_staff.size()-1))
						b.write("\n");*/
				}
				b.close();
				File newname = new File("Staff.txt");
				File oldname = new File("Staff_temp.txt");
				oldname.renameTo(newname);
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("*** LISTA STAFF ***");
			for(int i = 0; i < lista_staff.size(); i++)
			{
				System.out.println("| "+lista_staff.get(i).getNome()+" | "+lista_staff.get(i).getCognome()+" | "+lista_staff.get(i).getCellulare()+" | "+lista_staff.get(i).getId()+" |");
			}
		}
		else
			System.out.println("Errore. Non hai confermato.");
	}
	
	public boolean confermaEliminazione()
	{
		int result = JOptionPane.showConfirmDialog(null, 
				   "Sei sicuro di voler eliminare questo Staff?",null, JOptionPane.YES_NO_OPTION);
		if(result == JOptionPane.YES_OPTION)
			return true;
		else
			return false;
	}
	
	public void creaSessione(int idtessera)
	{
		boolean cerca = false;
		boolean impegnato = false;
		int index = -1;
		for(int i = 0; i < lista_tesserati.size(); i++)
		{
			if(idtessera == lista_tesserati.get(i).getIdTessera())
			{
				index = i;
				cerca = true;
				break;
			}
		}
		for(int i = 0; i < lista_sessioni.size(); i++)
		{
			if( (lista_tesserati.get(index).getIdTessera() == lista_sessioni.get(i).getGiocatore().getIdTessera()) && lista_sessioni.get(i).isAperta())
			{
				impegnato = true;
				break;
			}
		}

		if(!cerca)
			System.out.println("Errore: tessera non presente nel database.");
		else if(impegnato)
			System.out.println("Errore: il tesserato e' gia' impegnato in un'altra sessione di gioco.");
		else if(!(lista_tesserati.get(index).getValiditaTessera()))
			System.out.println("Errore: tessera scaduta.");
		else if(cerca)
		{
			System.out.println("*** LISTA GIOCHI DISPONIBILI***");
			for(int i = 0; i < lista_giochi.size(); i++)
			{
				if(lista_giochi.get(i).isDisponibile())
					System.out.println("| "+lista_giochi.get(i).getNome()+" | "+lista_giochi.get(i).getGiocatori()+" | "+lista_giochi.get(i).getId()+" |");
			}
			
			String a = JOptionPane.showInputDialog("Inserisci l'ID del gioco scelto dal tesserato: ");
			int result = selezionaGioco(a);
			if(result == -1)
				System.out.println("Gioco gia' in uso.");
			else if(result == -2)
				System.out.println("ID gioco non presente in lista.");
			else if(result >= 0)
			{
				int id_temp = 1;
				if(!lista_sessioni.isEmpty())
					id_temp = lista_sessioni.getLast().getId() + 1;
				Sessione sessionecorrente = new Sessione(id_temp, lista_giochi.get(result), lista_tesserati.get(index), user, new Date().getTime());
				lista_sessioni.add(sessionecorrente);
				lista_giochi.get(result).setNonDisponibile();
				sessionecorrente.addObserver(lista_giochi.get(result));
				System.out.println("Creata la sessione di gioco con ID "+this.lista_sessioni.getLast().getId());
			}
			
		}
	}
	
	public int selezionaGioco(String id_gioco)
	{
		if(id_gioco == null)
			return -2;
		for(int i = 0; i < lista_giochi.size(); i++)
		{
			if(id_gioco.equals(lista_giochi.get(i).getId()))
			{
				if(lista_giochi.get(i).isDisponibile())
					return i;
				else
					return -1;
			}
		}
		return -2;
	}
	
	public void eliminaSessione(int id_sessione)
	{
		boolean eliminata = false;
		for(int i = 0; i < lista_sessioni.size(); i++)
		{
			if( (lista_sessioni.get(i).getId()) == id_sessione && (lista_sessioni.get(i).getSupervisore().getId() == getUser().getId()) )
			{
				eliminata = true;
				lista_sessioni.get(i).setChanged();
				lista_sessioni.get(i).notifyObservers();
				break;
			}
		}
		
		if(eliminata)
			System.out.println("Sessione eliminata.");
		else
			System.out.println("Sessione non presente.");
	}
}
