package Controller;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

abstract public class Game implements WindowListener {
	
	// referente a atualização do jogo
	private boolean active;
	private static final int UPDATE_TIME = 50, DELAYS = 16;
	long beforeTime, afterTime, totalTime, overTime;
    int noDelays = 0, excess = 0;
	
	// tela
    public static int WINDOW_HEIGHT = 480, WINDOW_WIDTH = 854;
    private JFrame mainWindow;
    private Tela tela;

    public Game() {
    	
        mainWindow = new JFrame("WBOnline");
        mainWindow.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        mainWindow.addWindowListener(this);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setResizable(false);
        
        tela = new Tela();
        mainWindow.add(tela);
        
    }
    
    @SuppressWarnings("serial")
	private class Tela extends JPanel {
    	
    	public Tela() {
    		setDoubleBuffered(true);
    	}
    	
    	public void paint(Graphics g) {
    		onRender( (Graphics2D)g );
    	}
    	
    }

    public void terminate() {
    	active = false;
    }

    public void run() {
        load();
        active = true;
        
        overTime = 0;
        while(active) {
        	beforeTime = System.currentTimeMillis();
        	
        	if(excess > UPDATE_TIME) {
        		update();
        		excess -= UPDATE_TIME;
        	}
        	
        	update();
        	render();
        	afterTime = System.currentTimeMillis();
        	
        	totalTime = afterTime - beforeTime;
        	//System.out.println( UPDATE_TIME - totalTime );
        	
        	if(totalTime < UPDATE_TIME) {
        		sleep( UPDATE_TIME - totalTime );
        	} else {
        		excess += totalTime - UPDATE_TIME;
        		
        		if(++noDelays == DELAYS)
        			Thread.yield();
        	}
        }
        
        unload();
    }
    
	private void sleep(long nanos) {
    	try {
        	long beforeSleep = System.nanoTime();
            Thread.sleep(nanos);
            overTime = System.nanoTime() - beforeSleep - nanos;
    	} catch (Exception e) {
    		active = false;
    	}
	}

    public void load() {
    	
        onLoad();
        
        mainWindow.setVisible(true);
        
    }

    public void unload() { // finaliza
    	
        onUnload();
        mainWindow.dispose();
        
    }

    public void update() {
    	
        onUpdate(); // jogo
        Thread.yield();
        
    }

    public void render() {
    	
    	tela.repaint();
    	
    }
    
    // métodos que devem ser implementados no game
    abstract public void onLoad();
    abstract public void onUnload();
    abstract public void onUpdate();
    abstract public void onRender(Graphics2D g);

    // métodos úteis relativos ao JFrame
    public int getWidth() {
        return mainWindow.getWidth();
    }
    public int getHeight() {
    	return mainWindow.getHeight();
	}

    // métodos relativos ao windows listener
	public void windowClosing(WindowEvent e) { terminate(); }
    public void windowOpened(WindowEvent e) {}
	public void windowClosed(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}
}