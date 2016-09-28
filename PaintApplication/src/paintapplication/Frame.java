/**
 * 
 * NOTE: both the saveButtonAction method and loadButtonAction method utilize
 * code from...
 * 
 * 1) https://www.mkyong.com/java/how-to-create-xml-file-in-java-dom/,
 * 2) https://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
 * 
 * for XML formatting purposes.
 * 
 */


package paintapplication;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.io.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Frame extends javax.swing.JFrame {

    ArrayList<Point> points = new ArrayList<Point>();
    int current_x;
    int current_y;
    
    // Frame Constructor
    public Frame() 
    {
        initComponents();
    }

    // Generated Code
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new Panel();
        saveButton = new javax.swing.JButton();
        loadButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Paint App");
        setMaximumSize(new java.awt.Dimension(900, 900));
        setMinimumSize(new java.awt.Dimension(900, 900));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setMaximumSize(new java.awt.Dimension(900, 600));
        jPanel1.setMinimumSize(new java.awt.Dimension(900, 600));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        saveButton.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        loadButton.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        loadButton.setText("Load");
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });

        clearButton.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        clearButton.setText("Clear");
        clearButton.setMaximumSize(new java.awt.Dimension(100, 40));
        clearButton.setMinimumSize(new java.awt.Dimension(100, 40));
        clearButton.setPreferredSize(new java.awt.Dimension(100, 40));
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(saveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(90, 90, 90)
                .addComponent(clearButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(89, 89, 89)
                .addComponent(loadButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(111, 111, 111)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(clearButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(loadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(saveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(85, 85, 85))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // What happens when you click the load button
    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed
        System.out.println("LOAD!");
        
        // clear out current ArrayList of points
        points.clear();
        
        try{
            File data_file = new File("data.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document d = db.parse(data_file);
            
            System.out.println("Root element: " + d.getDocumentElement().getNodeName());
            
            NodeList nl = d.getElementsByTagName("point");
            
            System.out.println("----------------------------");
            
            for(int temp = 0; temp < nl.getLength(); ++temp)
            {
                Node n = nl.item(temp);
                
                System.out.println("\nCurrent Element: " + n.getNodeName());
                
                if(n.getNodeType() == Node.ELEMENT_NODE) 
                {
                    
                    Element e = (Element) n;
                    
                    // just for output...
                    System.out.println("x_coordinate: " + e.getElementsByTagName("x").item(0).getTextContent());
                    System.out.println("y_coordinate: " + e.getElementsByTagName("y").item(0).getTextContent());
                    
                    int x = Integer.parseInt(e.getElementsByTagName("x").item(0).getTextContent());
                    int y = Integer.parseInt(e.getElementsByTagName("y").item(0).getTextContent());
                    
                    points.add(new Point(x,y));
                }
            };
        }catch(Exception e){
            e.printStackTrace();
        }
        
        repaint();
    }//GEN-LAST:event_loadButtonActionPerformed

    // What happens when you click the jPanel
    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked

        // Whenever the user clicks the screen, 
        this.points.add(new Point(this.getMousePosition().x, this.getMousePosition().y - 20));
        
        System.out.println(this.getMousePosition().x);
        System.out.println(this.getMousePosition().y);
        
        jPanel1.repaint();
    }//GEN-LAST:event_jPanel1MouseClicked

    // What happens when you click the save button
    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        System.out.println("SAVE!");
        
        File data_file = new File("data.xml");
        
        try{
            
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            
            // root element
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("points");
            doc.appendChild(rootElement);
            
            // loop through the points ArrayList
            for(int temp = 0; temp < points.size(); ++temp)
            {
                // start with grabbing element
                Element point = doc.createElement("point");
                rootElement.appendChild(point);
                
                // grab the element's x coordinate
                Element x_coordinate = doc.createElement("x");
                Integer x_coordinate_Integer_form = points.get(temp).x;
                x_coordinate.appendChild(doc.createTextNode(x_coordinate_Integer_form.toString()));
                point.appendChild(x_coordinate);
                
                // now grab the element's y coordinate
                Element y_coordinate = doc.createElement("y");
                Integer y_coordinate_Integer_form = points.get(temp).y;
                y_coordinate.appendChild(doc.createTextNode(y_coordinate_Integer_form.toString()));
                point.appendChild(y_coordinate);
            };
            
            /*
            // first point element
            Element pt = doc.createElement("point");
            rootElement.appendChild(pt);
            
            // x element
            Element x_coordinate = doc.createElement("x");
            x_coordinate.appendChild(doc.createTextNode("100"));
            pt.appendChild(x_coordinate);
            
            // y element
            Element y_coordinate = doc.createElement("y");
            y_coordinate.appendChild(doc.createTextNode("500"));
            pt.appendChild(y_coordinate);
            
            // second point element
            Element pt2 = doc.createElement("point");
            rootElement.appendChild(pt2);
            
            // x element
            Element x_coordinate_2 = doc.createElement("x");
            x_coordinate_2.appendChild(doc.createTextNode("400"));
            pt2.appendChild(x_coordinate_2);
            
            // y element
            Element y_coordinate_2 = doc.createElement("y");
            y_coordinate_2.appendChild(doc.createTextNode("200"));
            pt2.appendChild(y_coordinate_2);
            */            

            // write content into xml file
            TransformerFactory transformer_factory = TransformerFactory.newInstance();
            Transformer transformer = transformer_factory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(data_file);
            transformer.transform(source, result);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        System.out.println("CLEAR!");
        points.clear();
        repaint();
    }//GEN-LAST:event_clearButtonActionPerformed

    public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() {
                new Frame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearButton;
    javax.swing.JPanel jPanel1;
    private javax.swing.JButton loadButton;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables
    
    class Panel extends JPanel
    {
        
        public void paintComponent(Graphics g)
        {   
            // We want to use the points vector which we updated in the frame stuff to draw the lines here!   
            Graphics2D g2 = (Graphics2D) g;
            
            // Clear the screen
            g2.clearRect(0,0,this.getX(), this.getY());
            
            // Set the color to be black
            g2.setColor(Color.BLACK);
            
            // Draw the background
            g2.fillRect(0, 0, this.getWidth(), this.getHeight());
            
            // Set the line color
            g2.setColor(Color.PINK);
            
            // Set line stroke
            g2.setStroke(new BasicStroke(3));
            
            // now we want to draw our lines
            for(int i = 0; i < points.size(); ++i)
            {
                // so every time we hit an odd number, we know we have at least two points to draw a line
                if(i%2 == 1)
                {   
                    g2.drawLine(points.get(i-1).x, points.get(i-1).y, points.get(i).x, points.get(i).y);
                }
                // otherwise we just draw a point to denote where the next line will start
                else 
                {
                    g2.fillOval(points.get(i).x, points.get(i).y, 3, 3);
                }
            }
        }
    }
}