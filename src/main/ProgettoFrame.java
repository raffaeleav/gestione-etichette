package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ProgettoFrame extends JFrame{
	private static final long serialVersionUID = 782168712L;
	  
	  private JTextField text;
	  private JComboBox<String> combo;
	  private JButton button;
	  private JTextArea area;
	  private JScrollPane scroll;
	  private JPanel bigPanel;
	  private JPanel northPanel;
	  static final String dbUrl = "jdbc:mysql://localhost/gestioneEtichette2022";
	  static final String user = "root";
	  static final String password = "Root2022";
	  
	  public ProgettoFrame() {
	    setSize(700, 400);
	    createCombo();
	    createField();
	    createArea();
	    createScroll();
	    createButton();
	    createPanel();
	    add(this.bigPanel);
	  }
	  
	  public void createField() {
	    this.text = new JTextField(15);
	  }
	  
	  public void createArea() {
	    this.area = new JTextArea(10, 10);
	  }
	  
	  public void createScroll() {
	    this.scroll = new JScrollPane(this.area);
	  }
	  
	  public void createCombo() {
	    this.combo = new JComboBox<>();
	    this.combo.addItem("Insert");
	    this.combo.addItem("Update");
	    this.combo.addItem("Delete");
	    this.combo.addItem("Query");
	  }
	  
	  public void createButton() {
	    this.button = new JButton("Esegui");
	    class ButtonListener implements ActionListener {
	    	
	      public void actionPerformed(ActionEvent e) {
	    	  
	        if (((String)ProgettoFrame.this.combo.getSelectedItem()).equalsIgnoreCase("Query")) {
	          ProgettoFrame.this.area.setText("");
	          String query = ProgettoFrame.this.text.getText();
	          
	          try {
	            Connection connection = DriverManager.getConnection(dbUrl, user, password);
	            Statement statement = connection.createStatement();
	            ResultSet result = statement.executeQuery(query);
	            
	            // all strings should be select*from
	            if(!query.substring(0, 13).equalsIgnoreCase("select * from")) {
	            	ProgettoFrame.this.area.append("Query eseguita.");
	            }
	            
	            else {
	            	String table = query.substring(14);
	            	
	            	switch(table) {
		            	case "Artista":
		            		while (result.next()) {
			      	         	ProgettoFrame.this.area.append("Nome artista: " + result.getString("nomeArtista") + " ");
			      	         	ProgettoFrame.this.area.append("Dischi d'oro: " + result.getString("dischiOro") + " ");
			      	           	ProgettoFrame.this.area.append("Dischi di platino: " + result.getString("dischiPlatino"));
			      	           	ProgettoFrame.this.area.append("\n");
			      	        }
		            		
		            		break;
		            		
		            	case "Etichetta":
		            		while (result.next()) {
			      	         	ProgettoFrame.this.area.append("Etichetta: " + result.getString("trademark") + " ");
			      	          	ProgettoFrame.this.area.append("Sede: " + result.getString("sede") + " ");
			      	         	ProgettoFrame.this.area.append("\n");
			      	        }
		            		
		            		break;
		            		
		            	case "Progetto":
		            		while (result.next()) {
			      	        	ProgettoFrame.this.area.append("Id: " + result.getString("idProgetto") + " ");
			      	        	ProgettoFrame.this.area.append("#canzoni: " + result.getInt("nCanzoni") + " ");
			      	         	ProgettoFrame.this.area.append("Nome artista: " + result.getString("nomeArtista") + " ");
			      	          	ProgettoFrame.this.area.append("Etichetta: " + result.getString("trademark"));
			      	          	ProgettoFrame.this.area.append("\n");
		      	            }
		           
		            		break;
		            	
		            	case "Fisico":
		            		while (result.next()) {
			      	        	ProgettoFrame.this.area.append("Id distributore: " + result.getString("idDistributore") + " ");
			      	        	ProgettoFrame.this.area.append("Indirizzo: " + result.getString("indirizzo"));
			      	        	ProgettoFrame.this.area.append("\n");
			      	        }
		            		
		            		break;
		            		
		            	case "Digitale":
		            		while (result.next()) {
		            			ProgettoFrame.this.area.append("Id distributore: " + result.getString("idDistributore") + " ");
			      	        	ProgettoFrame.this.area.append("Indirizzo: " + result.getString("indirizzo"));
			      	        	ProgettoFrame.this.area.append("\n");
			      	        }
		            		
		            		break;
		            		
		            	case "LicenzaFisico":
		            		while (result.next()) {
		            			ProgettoFrame.this.area.append("Tipo licenza: " + result.getString("tipoLicenza") + " ");
			      	            ProgettoFrame.this.area.append("idDistributore: " + result.getString("idDistributore"));
			      	            ProgettoFrame.this.area.append("\n");
			      	        }
		            		
		            		break;
		            		
		            	case "LicenzaDigitale":
		            		while (result.next()) {
			      	            ProgettoFrame.this.area.append("Tipo licenza: " + result.getString("tipoLicenza") + " ");
			      	            ProgettoFrame.this.area.append("idDistributore: " + result.getString("idDistributore"));
			      	            ProgettoFrame.this.area.append("\n");
			      	        }
		            		
		            		break;
		            	
		            	case "Consegnare":
		            		while (result.next()) {
		            			ProgettoFrame.this.area.append("idDistributore: " + result.getString("idDistributore") + " ");
		            			ProgettoFrame.this.area.append("Etichetta: " + result.getString("trademark") + " ");
		            			ProgettoFrame.this.area.append("Data di consegna: " + result.getString("dataConsegna"));
		            			ProgettoFrame.this.area.append("\n");
		      	            }
		            	
		            		break;
		            		
		            	case "Fornire":
		            		while (result.next()) {
		            			ProgettoFrame.this.area.append("idDistributore: " + result.getString("idDistributore") + " ");
		            			ProgettoFrame.this.area.append("Etichetta: " + result.getString("trademark") + " ");
		            			ProgettoFrame.this.area.append("Data di consegna: " + result.getString("dataConsegna"));
		            			ProgettoFrame.this.area.append("\n");
			      	        }
		            		
		            		break;
		            		
		            	default: 
		            		ProgettoFrame.this.area.append("Non esiste una tabella con questo nome.");
		            		
		            		break;
	            	}
	            }
	            
	          } catch (SQLException e1) {
	        	ProgettoFrame.this.area.append("Errore nella sintassi della query.");
	            e1.printStackTrace();
	          } 
	        } 
	        
		        else if (((String)ProgettoFrame.this.combo.getSelectedItem()).equalsIgnoreCase("Insert")) {
		          ProgettoFrame.this.area.setText("");
		          String query = ProgettoFrame.this.text.getText();
		          try {
		            Connection connection = DriverManager.getConnection(dbUrl, user, password);
		            Statement statement = connection.createStatement();
		            statement.executeUpdate(query);
		          } catch (SQLException e2) {
		        	ProgettoFrame.this.area.append("Errore nella sintassi della query.");
		            e2.printStackTrace();
		          } 
		        } 
		        
			        else if (((String)ProgettoFrame.this.combo.getSelectedItem()).equalsIgnoreCase("Delete")) {
			          ProgettoFrame.this.area.setText("");
			          String query = ProgettoFrame.this.text.getText();
			          try {
			            Connection connection = DriverManager.getConnection(dbUrl, user, password);
			            Statement statement = connection.createStatement();
			            statement.executeUpdate(query);
			          } catch (SQLException e3) {
			        	ProgettoFrame.this.area.append("Errore nella sintassi della query.");
			            e3.printStackTrace();
			          } 
			        } 
			        
				        else if (((String)ProgettoFrame.this.combo.getSelectedItem()).equalsIgnoreCase("Update")) {
				          ProgettoFrame.this.area.setText("");
				          String query = ProgettoFrame.this.text.getText();
				          try {
				            Connection connection = DriverManager.getConnection(dbUrl, user, password);
				            Statement statement = connection.createStatement();
				            statement.executeUpdate(query);
				          } catch (SQLException e4) {
				        	ProgettoFrame.this.area.append("Errore nella sintassi della query.");
				            e4.printStackTrace();
				          } 
				        } 
	      }
	    };
	    this.button.addActionListener(new ButtonListener());
	  }
	  
	  public void createPanel() {
	    this.bigPanel = new JPanel();
	    this.northPanel = new JPanel();
	    this.northPanel.add(this.combo);
	    this.northPanel.add(this.text);
	    this.northPanel.add(this.button);
	    this.bigPanel.setLayout(new BorderLayout());
	    this.bigPanel.add(this.northPanel, "North");
	    this.bigPanel.add(this.scroll);
	  }
}
