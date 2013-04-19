package pl.idoru.draw;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.border.*;

import pl.idoru.draw.Male;
import pl.idoru.draw.Female;


class JavaPaintUI extends JFrame {

	
	
	
	
    private int tool = 1;
    int currentX, currentY, oldX, oldY;

    public JavaPaintUI() {
        initComponents();
    }

    private void initComponents() {
        // we want a custom Panel2, not a generic JPanel!
        jPanel2 = new Panel2();

        jPanel2.setBackground(new java.awt.Color(200, 200, 200));
        jPanel2.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        jPanel2.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                jPanel2MousePressed(evt);
            }
            public void mouseReleased(MouseEvent evt) {
                jPanel2MouseReleased(evt);
            }
        });
        jPanel2.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent evt) {
                jPanel2MouseDragged(evt);
            }
        });

        // add the component to the frame to see it!
        this.setContentPane(jPanel2);
        // be nice to testers..
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }// </editor-fold>

    private void jPanel2MouseDragged(MouseEvent evt) {
        if (tool == 1) {
            currentX = evt.getX();
            currentY = evt.getY();
            oldX = currentX;
            oldY = currentY;
            System.out.println(currentX + " " + currentY);
            System.out.println("PEN!!!!");
        }
    }

    private void jPanel2MousePressed(MouseEvent evt) {
        oldX = evt.getX();
        oldY = evt.getY();
        System.out.println(oldX + " " + oldY);
    }


    //mouse released//
    private void jPanel2MouseReleased(MouseEvent evt) {
        if (tool == 2) {
            currentX = evt.getX();
            currentY = evt.getY();
            System.out.println("line!!!! from" + oldX + "to" + currentX);
        }
    }

    //set ui visible//
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JavaPaintUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private JPanel jPanel2;
    // End of variables declaration

    // This class name is very confusing, since it is also used as the
    // name of an attribute!
    //class jPanel2 extends JPanel {
    class Panel2 extends JPanel {
        private BufferedImage buffer;
        private Male male = new Male("Chaton");
        private Female female = new Female("Biche");
   
        
        
    	private int size = 400;
    	private Color colors[] = {new Color(0,40, 0),new Color(60,10, 0)};
    	
    	private Human people[] = {male, female};
    	
        Panel2() {
            // set a preferred size for the custom panel.
            
        	
            this.buffer = new BufferedImage(this.size, this.size, BufferedImage.TYPE_INT_ARGB);
            setBackground(Color.BLACK); 
        	setPreferredSize(new Dimension(820,620));
        	setDoubleBuffered(false);
            new Timer(200, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    moveNext(buffer.getGraphics());
                }
            }).start();
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(buffer, 0, 0, this);


            //g.drawRect(200, 200, 200, 200);
        }
        
        
        public void moveNext(Graphics g){ 
            for (int i=0;i < people.length;i++) {
                
            	people[i].update();            
            
        	
         
            //System.out.println("W"+this.size +"H"+this.size);
            if (people[i].locX > this.size-40)
            	people[i].locX = this.size-40;
            
            
            if (people[i].locY > this.size-40)
            	people[i].locY = this.size-40;
          
            if (people[i].locX-40<0)
            	people[i].locX = 40;
            
            
            if (people[i].locY-40<0)
            	people[i].locY = 40;
            }
            
            g.clearRect(0, 0, getWidth(), getHeight());
            
            
            for (int i=0;i < people.length;i++) {
                   g.setColor(colors[people[i].sex]);
            	g.fillRect(people[i].locX, people[i].locY, 20, 20);
        	
            	System.out.println(i + " locX " + people[i].locX + " locY " + people[i].locY);
            }
            
        	/*
            int r = rand.nextInt(4);        

            switch (r) {
            case 1:
                h += ruut;
                wallTest();
                break;
            case 2:
                h -= ruut;
                wallTest();
                break;
            case 3:
                w -= ruut;
                wallTest();
                break;
            case 4:
                w -= ruut;
                wallTest();
                break;
            }

            color = new Color(0, rand.nextInt(255-50)+50, 0);
            g.setColor(color);
            g.fillRect(w, h, ruut, ruut);
            */
            repaint();
        }
    }
}