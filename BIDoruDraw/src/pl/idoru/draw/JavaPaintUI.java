package pl.idoru.draw;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.border.*;

import pl.idoru.draw.Male;
import pl.idoru.draw.Female;
import java.util.ArrayList;
import java.util.Random;

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
        public Male male = new Male("Chaton");
        public Female female = new Female("Biche");
        public Female deer= new Female("Deer");
        public People ppl;
        private Random rnd = new Random();
   
        
    	private int size = Config.size;
    	private Color colors[] = {new Color(0,240, 0),new Color(200,10, 0)};
    	
    	public Human people2[] = {male, female, deer};
    	public ArrayList <Human> people = new ArrayList<Human>();
        Panel2() {
            // set a preferred size for the custom panel.
            ppl = new People();
            people.add(male);
            people.add(female);
            people.add(deer);
            //this.ppl.people.add(this.male);
            //this.ppl.people.add(this.female);
            this.buffer = new BufferedImage(this.size, this.size, BufferedImage.TYPE_INT_ARGB);
            setBackground(Color.BLACK); 
        	setPreferredSize(new Dimension(Config.size+100,Config.size+100));
        	setDoubleBuffered(false);
            new Timer(1, new ActionListener() {
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
        	int colormod=0;
            for (int i=0;i < people.size();i++) {
               
            	people.get(i).update();            
            
        	
         
            //System.out.println("W"+this.size +"H"+this.size);
            if (people.get(i).locX > this.size-40)
            	people.get(i).locX = this.size-40;
            
            
            if (people.get(i).locY > this.size-40)
            	people.get(i).locY = this.size-40;
          
            if (people.get(i).locX-40<0)
            	people.get(i).locX = 40;
            
            
            if (people.get(i).locY-40<0)
            	people.get(i).locY = 40;
            }
            
            g.clearRect(0, 0, getWidth(), getHeight());
            
            
            for (int i=0;i < people.size();i++) {
                   g.setColor(colors[people.get(i).gender]);
                   
                   //Color c= new Color(0,240, 0);
                   //c.
                   
                   if (i<150) {
                	   colormod=i;
                	   
                   } else
                	   
                   {
                	   colormod = rnd.nextInt(120) + 100-1;
                   }
                   
                   
                  Color c = new Color(0,0,0); 
                  if (people.get(i).gender==0) {
                	  c= new Color(100,100+colormod/4, 255-colormod);
               }
                  if (people.get(i).gender==1) {
                	  c= new Color(255-colormod,255-colormod,50);
                	 
                  } 
                  
                  g.setColor(c);
                   
                   
            	g.fillRect(people.get(i).locX, people.get(i).locY, 7, 7);
        	
            	//System.out.println(i + " locX " + people.get(i).locX + " locY " + people.get(i).locY);
            	
            
            }
            
            int distance;
            
            //distance = (int) ppl.getDistance(male, female);
            
            
            //if (distance<20) {
            //	people.add(ppl.getRandomHuman());
            	
            	
            //}
            
            
            for (int cc=0;cc<people.size();cc++) {
            	System.out.println("cc: "+cc);
            	for (int dd=0;dd<people.size();dd++) {
                	if (cc!=dd) {
                		System.out.println("dd:"+dd);
                		System.out.println(people.get(cc).gender + " " + people.get(dd).gender);
                		if (people.get(cc).gender != people.get(dd).gender) {
                  			distance = (int) ppl.getDistance(people.get(cc), people.get(dd));
                			System.out.println("distance: "+distance);                            
                	        if (distance<20) {
                            	people.add(ppl.getRandomHuman());
                            	cc= people.size();
                            	dd = cc;
                            	break;
                            }
                		}
                	}
                } 
            	
            	
            	if (people.size()>10000) {
            		if (cc<people.size() && people.get(cc).age>Config.ageMax/100) {
                		people.remove(people.get(cc));            		
                	}	
            		
            		
            	}
            	else {
            	if (cc<people.size() && people.get(cc).age>Config.ageMax) {
            		people.remove(people.get(cc));            		
            	}
            	}
            }
            
            
            
            
            
            repaint();
        }
    }
}