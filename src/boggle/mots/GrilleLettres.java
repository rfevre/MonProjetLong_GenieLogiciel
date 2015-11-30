package boggle.mots;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GrilleLettres {
    
    private int dimension;				// taille de la grille
    private De[][] grille;			

    
    // CONSTRUCTEUR ///////////////////////////////////////////////////////////
       
  
    public GrilleLettres(int dimension){
    	this.dimension = dimension;
    	this.grille = new De[dimension][dimension];
    }
    
    public GrilleLettres(int dimension, String chemin){
    	this(dimension);
    	this.initGrilleDepuisFichier(chemin);
    }
    

    // GET-SET ////////////////////////////////////////////////////////////////
    
    public int getDimension() { return dimension; }
    public De[][] getGrille() { return grille; }
    
    public void setDimension(int dimension) { this.dimension = dimension; }
    public void setGrille(De[][] grille) { this.grille = grille; }
        

	public String toString() {
    	final StringBuilder res = new StringBuilder();
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				res.append(grille[i][j]+ "  ");
			}
			res.append("\n");
		}
		return res.toString();
	}
    
    // PUBLIC METHODS /////////////////////////////////////////////////////////
    /** Permet d'initialiser la grille depuis un fichier */
    public void initGrilleDepuisFichier(String chemin){
    	int rx, ry;
		try {
			final File f = new File(chemin);
			final FileInputStream fis = new FileInputStream(f);
	    	final BufferedReader br = new BufferedReader(new InputStreamReader(fis));
	    	
	    	String line;
			line = br.readLine();
			int nbDes = this.dimension * this.dimension;
	    	while (line != null && nbDes > 0) {
	    		if(line.equals("")){
	    			line = br.readLine();
	    			continue;
	    		}
	        	do{
	        		rx = (int) (Math.random()*this.dimension);
	        		ry = (int) (Math.random()*this.dimension);
	            }while(this.grille[rx][ry] != null);

	        	this.grille[rx][ry] = new De(rx, ry, line);
	    		line = br.readLine();
	    		nbDes--;
	    	}
	     
	    	br.close();

		} catch (FileNotFoundException e) {
			System.err.println("Erreur : " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Erreur : " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Erreur : " + e.getMessage());
		}        
    }

    /** Permet de connaitre les dès situé autour d'un dè sélectionné*/
    public ArrayList<De> getListeDesAdjacents(int x, int y){
    	ArrayList<De> desAdjacents = new ArrayList<De>();
    	if (x>0 && y>0 && grille[x-1][y-1]!=null )
    		desAdjacents.add(grille[x-1][y-1]);
    	if (x>0 && grille[x-1][y]!=null)
    		desAdjacents.add(grille[x-1][y]);
    	if (x>0 && y<dimension-1 && grille[x-1][y+1]!=null)
    		desAdjacents.add(grille[x-1][y+1]);
    	if (y>0 && grille[x][y-1]!=null)
    		desAdjacents.add(grille[x][y-1]);
    	if (y<dimension-1 && grille[x][y+1]!=null)
    		desAdjacents.add(grille[x][y+1]);
    	if (x<dimension-1 && y>0 && grille[x+1][y-1]!=null)
    		desAdjacents.add(grille[x+1][y-1]);
    	if (x<dimension-1 && grille[x+1][y]!=null)
    		desAdjacents.add(grille[x+1][y]);
    	if (x<dimension-1 && y<dimension-1 && grille[x+1][y+1]!=null)
    		desAdjacents.add(grille[x+1][y+1]);
    	return desAdjacents;
    }
    
    



    
    


	// PRIVATE METHODS ////////////////////////////////////////////////////////
    
    public static void main(String[] args) throws IOException {

    	GrilleLettres g = new GrilleLettres(4);    	
    	System.out.println(g);
    	g.initGrilleDepuisFichier("config/des-4x4.csv");
    	System.out.println(g);
    	ArrayList<De> list = g.getListeDesAdjacents(3,2);
    	System.out.println(list);
    	
	}
    
}
