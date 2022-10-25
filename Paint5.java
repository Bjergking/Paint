import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowListener;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import java.awt.event.WindowEvent;
public class Paint5 extends Frame implements MouseListener,MouseMotionListener,WindowListener{
    Thread th ;
   int mx,my,oldx,oldy;
   int pw=1;
   int dm =0;
   Color cr = new Color(0,0,0); 
   Color tmpcr = new Color(0,0,0);
   Button bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8;

   BufferedImage buf;
   Graphics gp;

    Paint5(int x,int y,int w,int h)
    {
        this.setSize(w,h); this.setLocation(x,y);
        this.setLayout(null); this.setVisible(true);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        buf = new BufferedImage(600,500,BufferedImage.TYPE_INT_RGB);
        gp = buf.getGraphics();
        gp.setColor(new Color(255,255,255));
        gp.fillRect(0,0,600,500);
        gp.setColor(new Color(0,0,0));

        bt1 =new Button("Red");
        bt1.setSize(50, 30);
        bt1.setLocation(20, 50);
        this.add(bt1);
        bt1.addMouseListener(this);

        bt2 =new Button("Green");
        bt2.setSize(50, 30);
        bt2.setLocation(20, 100);
        this.add(bt2);
        bt2.addMouseListener(this);

        bt3 =new Button("Blue");
        bt3.setSize(50, 30);
        bt3.setLocation(20, 150);
        this.add(bt3);
        bt3.addMouseListener(this);

        bt4 =new Button("Yellow");
        bt4.setSize(50, 30);
        bt4.setLocation(20, 200);
        this.add(bt4);
        bt4.addMouseListener(this);

        bt5 =new Button("X2");
        bt5.setSize(50, 30);
        bt5.setLocation(20, 250);
        this.add(bt5);
        bt5.addMouseListener(this);

        bt6 =new Button("reset");
        bt6.setSize(50, 30);
        bt6.setLocation(20, 300);
        this.add(bt6);
        bt6.addMouseListener(this);

        bt7 =new Button("Clean");
        bt7.setSize(50, 30);
        bt7.setLocation(20, 350);
        this.add(bt7);
        bt7.addMouseListener(this);

        this.addWindowListener(this);

        bt8 =new Button("Save");
        bt8.setSize(120, 30);
        bt8.setLocation(20, 400);
        this.add(bt8);
        bt8.addMouseListener(this);
        // th = new Thread(this);
        // th.start();
    }
//     static int Random(int n)
//    {  return( (int)(Math.random()*n) ); }
//     static int Random(int n, int m)
//    {  return( n+Random(m-n+1) ); } 
    public static void main(String[] args)
    {
        Paint5 p = new Paint5(100,50,600,500);
    }
    public void mouseClicked(MouseEvent e)
    {
        if(e.getSource()==bt1)
        {
            cr = new Color(255,0,0);
        }
        else if(e.getSource()==bt2)
        {
            cr = new Color(0,255,0);
        }
        else if(e.getSource()==bt3)
        {
            cr = new Color(0,0,255);
        }
        else if(e.getSource()==bt4)
        {
            cr = new Color(255,255,0);
        }
        else if(e.getSource()==bt5)
        {
            pw = pw*2;
        }
        else if(e.getSource()==bt6)
        {
            pw =1;
        }
        else if(e.getSource() ==bt7)
        {
            // BufferedImage buf = new BufferedImage(800,600,BufferedImage.TYPE_INT_RGB);
            // Graphics gp = buf.getGraphics();
            // gp.setColor(new Color(255,0,0));
            // gp.fillOval(100, 100, 50, 50);
            dm=1;
            repaint();
        }
        else if(e.getSource()==bt8)
        {
            File f = new File("D:\\sssss.jpg");
            try{ImageIO.write(buf, "jpg", f);}catch(Exception a){}
            bt8.setLabel("Graph save as D:/");
            try{th.sleep(2500);}catch(Exception b){}
            bt8.setLabel("Save");
        }
    }

    public void mousePressed(MouseEvent e)
    {
       mx = e.getX();
       my = e.getY();
       if(e.getButton()==MouseEvent.BUTTON3)
       {
            tmpcr = cr;
            cr = new Color(255,255,255);
       }
    }
    public void mouseReleased(MouseEvent e)
    {
        if(e.getButton()==MouseEvent.BUTTON3)
        {
             cr = tmpcr;
        }
    }
    public void mouseEntered(MouseEvent e)
    {
        // bt1.setLocation(Random(0, 550),Random(0, 450));
    }
    public void mouseExited(MouseEvent e)
    {

    }
    //---------------------------------------
    public void mouseDragged(MouseEvent arg0)
    {
        oldx = mx;
        oldy = my;
        mx = arg0.getX();
        my = arg0.getY();
        repaint();
    }
    public void paint(Graphics g)
    {   
        if(dm ==0)
        {
            g.setColor(cr);
            gp.setColor(cr);
            if (pw==1)
            {
                g.drawLine(oldx, oldy, mx, my);
                gp.drawLine(oldx, oldy, mx, my);
            }
            else
            {
                int k = Math.abs(mx-oldx);
                int p = Math.abs(my-oldy);
                if(p<k)
                {
                    p=k;
                }
        
                float dx = (mx-oldx)/10;
                float dy = (my-oldy)/10;
                int cx,cy;
                for(int i=0;i<p;i++)
                {
                    cx = (int)((dx*i)+oldx);
                    cy = (int)((dy*i)+oldy);
                    g.fillOval(cx-pw, cy-pw, 2*pw+1, 2*pw+1);
                    gp.fillOval(cx-pw, cy-pw, 2*pw+1, 2*pw+1);
                } 
            }
        }    
        else if(dm ==1)
        {
            g.setColor(new Color(255,255,255));
            g.fillRect(0, 0, 600, 500);
            gp.setColor(new Color(255,255,255));
            gp.fillRect(0, 0, 600, 500);
            dm =0;
        } 
        
        // g.drawLine(oldx, oldy, mx, my);
    }
    public void update(Graphics g)
    {
        paint(g);
    }
    public void mouseMoved(MouseEvent arg0)
    {
      
    }

    public void run()
    {

    }
    public void windowActivated(WindowEvent arg0)
    {

        
    }
    public void windowClosed(WindowEvent arg0)
    {

    }
    public void windowClosing(WindowEvent arg0)
    {
        this.dispose();
    }
    public void windowDeactivated(WindowEvent arg0)
    {

    }
    public void windowDeiconified(WindowEvent arg0)
    {

    }
    public void  windowIconified(WindowEvent arg0)
    {

    }
    public void windowOpened(WindowEvent arg0)
    {

    }

}
