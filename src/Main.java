import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class Main 
{
	//Variabili istanza del main
	private static Amministratore admin = new Amministratore("Fabrizio", "Formosa", "345", "admin");
	private static boolean loggato = false;
	private static LudoTech ludoteca;
	
	//GUI
	private static JFrame frmHome;
	private static JFrame frmPannelloAmministratore;
	private static JFrame frmGestisciStaff;
	private static JFrame frmPannelloStaff;
	
	
	public static void main(String[] args) throws IOException
	{
		ludoteca = LudoTech.getInstance();
		ludoteca.creaListaStaff();
		ludoteca.creaListaGiochi();
		ludoteca.creaListaTesserati();
		initializeHome();
		frmHome.setVisible(true);
	}
	
	private static void initializeHome() {
		JPasswordField passwordField;
		JLabel lblInserisciID;

		frmHome = new JFrame();
		frmHome.setTitle("Ludoteca");
		frmHome.setBounds(100, 100, 450, 300);
		frmHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHome.getContentPane().setLayout(null);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(143, 104, 140, 31);
		frmHome.getContentPane().add(passwordField);
		
		JButton btnConferma = new JButton("Conferma");
		btnConferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String psw = passwordField.getText();
				
				if(admin.getId().equals(psw))
				{
					ludoteca.setUser(admin);
					loggato = true;
					System.out.println("Benvenuto, amministratore!");
					frmHome.dispose();
					try {
						initializePannelloAdmin();
						frmPannelloAmministratore.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				
				else
				{
					for(int i = 0; i < ludoteca.getListaStaff().size(); i++)
					{
						if(ludoteca.getListaStaff().get(i).getId().equals(psw))
						{
							ludoteca.setUser(ludoteca.getListaStaff().get(i));
							loggato = true;
							System.out.println("Benvenuto, "+ludoteca.getUser().getNome()+" "+ludoteca.getUser().getCognome()+"!");
							frmHome.dispose();
							initializePannelloStaff();
							frmPannelloStaff.setVisible(true);
						}
					}
					/*if(loggato == false)
					{
						for(int i = 0; i < Main.lista_membri.size(); i++)
						{
							if(Main.lista_membri.get(i).getId().equals(psw))
							{
								Main.user = Main.lista_membri.get(i);
								loggato = true;
								System.out.println("Benvenuto, "+Main.user.getNome()+" "+Main.user.getCognome()+"!");
								closeonLog();
							}
						}
					}*/
				}
			if(loggato == false)
				System.out.println("Errore: nessun utente con questo id.");
			}
		});
		btnConferma.setBounds(143, 147, 140, 25);
		frmHome.getContentPane().add(btnConferma);
		
		lblInserisciID = new JLabel("Inserisci il tuo ID");
		lblInserisciID.setBounds(153, 67, 130, 15);
		frmHome.getContentPane().add(lblInserisciID);
	}
	
	public static void initializePannelloAdmin()
	{
		frmPannelloAmministratore = new JFrame();
		frmPannelloAmministratore.setTitle("Pannello Amministratore");
		frmPannelloAmministratore.setBounds(100, 100, 450, 300);
		frmPannelloAmministratore.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPannelloAmministratore.getContentPane().setLayout(null);
		
		JButton btn1 = new JButton("Gestisci Staff");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("*** LISTA STAFF ***");
				for(int i = 0; i < ludoteca.getListaStaff().size(); i++)
				{
					System.out.println("| "+ludoteca.getListaStaff().get(i).getNome()+" | "+ludoteca.getListaStaff().get(i).getCognome()+" | "+ludoteca.getListaStaff().get(i).getCellulare()+" | "+ludoteca.getListaStaff().get(i).getId()+" |");
				}
				try {
					initializeGestisciStaff();
					frmGestisciStaff.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				frmPannelloAmministratore.setVisible(false);
			}
		});
		btn1.setBounds(134, 39, 169, 25);
		frmPannelloAmministratore.getContentPane().add(btn1);
		
		JButton btn3 = new JButton("Nuova Sessione");
		btn3.setBounds(134, 115, 169, 25);
		frmPannelloAmministratore.getContentPane().add(btn3);
		
		JButton btn2 = new JButton("Gestisci Tesserati");
		btn2.setBounds(134, 78, 169, 25);
		frmPannelloAmministratore.getContentPane().add(btn2);
		
		JLabel lblPannelloAdmin = new JLabel("Benvenuto, "+ludoteca.getUser().getNome()+" "+ludoteca.getUser().getCognome()+"!");
		lblPannelloAdmin.setBounds(12, 12, 426, 15);
		frmPannelloAmministratore.getContentPane().add(lblPannelloAdmin);
	}
	
	public static void initializePannelloStaff()
	{
		frmPannelloStaff = new JFrame();
		frmPannelloStaff.setTitle("Pannello Amministratore");
		frmPannelloStaff.setBounds(100, 100, 450, 300);
		frmPannelloStaff.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPannelloStaff.getContentPane().setLayout(null);
		
		JButton btn3 = new JButton("Nuova Sessione");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("*** LISTA TESSERATI ***");
				for(int i = 0; i < ludoteca.getListaTesserati().size(); i++)
				{
					System.out.println("| "+ludoteca.getListaTesserati().get(i).getNome()+" | "+ludoteca.getListaTesserati().get(i).getCognome()+" | "+ludoteca.getListaTesserati().get(i).getCellulare()+" | "+ludoteca.getListaTesserati().get(i).getIdTessera()+" |");
				}
				String x = JOptionPane.showInputDialog("Inserisci l'ID del tesserato: ");
				if(x != null)
					ludoteca.creaSessione(Integer.parseInt(x));
				else
					System.out.println("Errore: tessera non presente nel database.");
			}
		});
		btn3.setBounds(134, 115, 169, 25);
		frmPannelloStaff.getContentPane().add(btn3);
		
		JButton btn2 = new JButton("Gestisci Tesserati");
		btn2.setBounds(134, 78, 169, 25);
		frmPannelloStaff.getContentPane().add(btn2);
		
		JButton btn4 = new JButton("Elimina Sessione");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("*** LISTA DELLE TUE SESSIONI ATTIVE ***");
				for(int i = 0; i < ludoteca.getListaSessioni().size(); i++)
				{
					if(ludoteca.getListaSessioni().get(i).getSupervisore().getId() == ludoteca.getUser().getId() && ludoteca.getListaSessioni().get(i).isAperta())
					{
						Date in = new Date(ludoteca.getListaSessioni().get(i).getInizio());
						System.out.println("| "+ludoteca.getListaSessioni().get(i).getGioco().getNome()+" | "+ludoteca.getListaSessioni().get(i).getGioco().getId()+" | "+ludoteca.getListaSessioni().get(i).getGiocatore().getNome()+" "+ludoteca.getListaSessioni().get(i).getGiocatore().getCognome()+" | "+in.toString()+" | "+ludoteca.getListaSessioni().get(i).getId()+" |");
					}
				}
				String y = JOptionPane.showInputDialog("Inserisci l'ID della sessione da eliminare: ");
				if(y != null)
					ludoteca.eliminaSessione(Integer.parseInt(y));
				else
					System.out.println("Errore: sessione non esistente.");
			}
		});
		btn4.setBounds(134, 152, 169, 25);
		frmPannelloStaff.getContentPane().add(btn4);
		
		JLabel lblPannelloAdmin = new JLabel("Benvenuto, "+ludoteca.getUser().getNome()+" "+ludoteca.getUser().getCognome()+"!");
		lblPannelloAdmin.setBounds(12, 12, 426, 15);
		frmPannelloStaff.getContentPane().add(lblPannelloAdmin);
	}
	
	public static void initializeGestisciStaff()
	{
		frmGestisciStaff = new JFrame();
		frmGestisciStaff.setTitle("Gestisci Staff");
		frmGestisciStaff.setBounds(100, 100, 450, 300);
		frmGestisciStaff.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmGestisciStaff.getContentPane().setLayout(null);
		frmGestisciStaff.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
            	frmPannelloAmministratore.setVisible(true);
            }
        });
		
		JButton btn1 = new JButton("Inserisci Staff");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean errore_add = true;
				String add_nome = JOptionPane.showInputDialog("Inserisci nome: ");
				String add_cognome = JOptionPane.showInputDialog("Inserisci cognome: ");
				String add_cell = JOptionPane.showInputDialog("Inserisci cellulare: ");
				String add_id = JOptionPane.showInputDialog("Inserisci id: ");
				
				if(add_nome != null && add_cognome != null && add_cell != null && add_id != null)
				{
					errore_add = false;
					for(int i = 0; i < ludoteca.getListaStaff().size(); i++)
					{
						if(ludoteca.getListaStaff().get(i).getId().equals(add_id))
						{
							errore_add = true;
							break;
						}
					}
					if(add_nome.equals("") || add_cognome.equals("") || add_id.equals(""))
						errore_add = true;
				}		
				
				if(!errore_add)
				{
					try {
						ludoteca.aggiungiStaff(add_nome, add_cognome, add_cell, add_id);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println("*** LISTA STAFF ***");
					for(int i = 0; i < ludoteca.getListaStaff().size(); i++)
					{
						System.out.println("| "+ludoteca.getListaStaff().get(i).getNome()+" | "+ludoteca.getListaStaff().get(i).getCognome()+" | "+ludoteca.getListaStaff().get(i).getCellulare()+" | "+ludoteca.getListaStaff().get(i).getId()+" |");
					}
				}
				else
					System.out.println("Errore. ID gia' presente o non hai inserito tutti i campi.");
			}
		});
		btn1.setBounds(140, 77, 150, 25);
		frmGestisciStaff.getContentPane().add(btn1);
		
		JButton btnEliminaStaff = new JButton("Elimina Staff");
		btnEliminaStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String del_id = JOptionPane.showInputDialog("Inserisci ID da eliminare: ");
				if(del_id == null || del_id.equals(""))
				{
					System.out.println("Errore. Inserisci un ID.");
				}
				else
					ludoteca.eliminaStaff(del_id);
			}
		});
		btnEliminaStaff.setBounds(140, 114, 150, 25);
		frmGestisciStaff.getContentPane().add(btnEliminaStaff);
	}
	
	
}
