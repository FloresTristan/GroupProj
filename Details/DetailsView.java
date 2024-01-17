package github.group.details;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

class DetailsView extends AbstractDetailsView{

    protected RoundedPanel detailspanel;
    protected RoundedArea areapanel;
    protected RoundedButton backbutton;

    public void setDetailsController(DetailsController detcon) {
        this.detcon = detcon;
    }

    public void detailsView(JFrame frame, String username, String make, String type, String color, String regDate, String expDate, String sticker) {
        this.frame = frame;
        System.out.println("Details from detView: " + make + ", " + type + ", " + color + ", " + regDate + ", " + expDate+ ", "+sticker);
        
       initView(username,make,type,color,regDate,expDate,sticker);
       checkExpiration(expDate);
    }

    private class RoundedPanel extends JPanel {
        private int cornerRadius;

        public RoundedPanel(int cornerRadius) {
            this.cornerRadius = cornerRadius;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Set the background color for the RoundedPanel
            g2d.setColor(new Color(109, 198, 248));
            g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius));

            g2d.dispose();
        }
    }

    private class RoundedButton extends JButton {
        private int cornerRadius;
        private String label;

        public RoundedButton(int cornerRadius, String label) {
            this.cornerRadius = cornerRadius;
            this.label = label;
            setContentAreaFilled(false); // Ensure that the button does not paint its background
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Set the background color for the RoundedButton
            g2d.setColor(new Color(248, 217, 109));
            g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius));

            super.paintComponent(g2d);

            g2d.dispose();
        }

        @Override
        protected void paintBorder(Graphics g) {
            // Optional: You can customize the border painting if needed
        }

        @Override
        protected void paintChildren(Graphics g) {
            super.paintChildren(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(Color.BLACK);
            g2d.drawString(label, getWidth() / 2 - g2d.getFontMetrics().stringWidth(label) / 2, getHeight() / 2 + g2d.getFontMetrics().getHeight() / 4);
            g2d.dispose();
        }
    }

    private class RoundedArea extends JPanel {
        private int cornerRadius;

        public RoundedArea(int cornerRadius) {
            this.cornerRadius = cornerRadius;
            setOpaque(false);  // Ensure that the JTextArea is not opaque
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            super.paintComponent(g2d);

            // Set the background color for the RoundedTextArea
            g2d.setColor(new Color(0, 153, 153));
            g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius));

            g2d.dispose();
        }
    }

    public void initView(String username, String make, String type, String color, String regDate, String expDate, String sticker) {
        try {
           System.out.println("Details in InitView: " + make + ", " + type + ", " + color + ", " + regDate + ", " + expDate );
            detailspanel = new RoundedPanel(15);
            detailspanel.setBounds(0, 0, 400, 700);
            detailspanel.setLayout(null);
            frame.add(detailspanel);

            areapanel = new RoundedArea(20);
            areapanel.setBounds(45, 70, 300, 500);
            areapanel.setLayout(null);
            detailspanel.add(areapanel);

            linearea = new JTextArea();
            linearea.setBounds(0, 42, 400, 2);
            linearea.setBackground(new Color(0, 0, 0));
            linearea.setEditable(false);
            areapanel.add(linearea);
		
            detaillabel = new JLabel("Vehicle Details");
            detaillabel.setBounds(80, 4, 300, 40);
            detaillabel.setFont(new Font("Abyssinica SIL", Font.BOLD, 20));
            areapanel.add(detaillabel);

            makearea = new JTextArea(make);
            makearea.setBounds(27, 75, 150, 27);
            makearea.setForeground(Color.WHITE);
            makearea.setFont(new Font("Abyssinica SIL", Font.BOLD, 20));
            makearea.setBackground(new Color(0, 153, 153));
            makearea.setEditable(false);
            areapanel.add(makearea);

            makelabel = new JLabel("Make");
            makelabel.setBounds(20, 40, 100, 40);
            makelabel.setFont(new Font("Abyssinica SIL", Font.BOLD, 18));
            areapanel.add(makelabel);

            typelabel = new JLabel("Type");
            typelabel.setBounds(20, 100, 100, 40);
            typelabel.setFont(new Font("Abyssinica SIL", Font.BOLD, 18));
            areapanel.add(typelabel);

            typearea = new JTextArea(type);
            typearea.setBounds(27, 135, 150,27);
            typearea.setForeground(Color.WHITE);
            typearea.setFont(new Font("Abyssinica SIL", Font.BOLD, 20));
            typearea.setBackground(new Color(0, 153, 153));
            typearea.setEditable(false);
            areapanel.add(typearea);

            colorlabel = new JLabel("Color");
            colorlabel.setBounds(20, 160, 100, 40);
            colorlabel.setFont(new Font("Abyssinica SIL", Font.BOLD, 18));
            areapanel.add(colorlabel);

            colorarea = new JTextArea(color);
            colorarea.setBounds(27, 195, 150,27);
            colorarea.setForeground(Color.WHITE);
            colorarea.setFont(new Font("Abyssinica SIL", Font.BOLD, 20));
            colorarea.setBackground(new Color(0, 153, 153));
            colorarea.setEditable(false);
            areapanel.add(colorarea);

            stickerlabel = new JLabel("Sticker No.");
            stickerlabel.setBounds(20, 340, 150, 40);
            stickerlabel.setFont(new Font("Abyssinica SIL", Font.BOLD, 18));
            areapanel.add(stickerlabel);

            stickerarea = new JTextArea(sticker);
            stickerarea.setBounds(27, 375, 150,27);
            stickerarea.setForeground(Color.WHITE);
            stickerarea.setFont(new Font("Abyssinica SIL", Font.BOLD, 20));
            stickerarea.setBackground(new Color(0, 153, 153));
            stickerarea.setEditable(false);
            areapanel.add(stickerarea);

            JLabel registrationdatelabel = new JLabel("Registration Date");
            registrationdatelabel.setBounds(20, 220, 200, 40);
            registrationdatelabel.setFont(new Font("Abyssinica SIL", Font.BOLD, 18));
            areapanel.add(registrationdatelabel);

            registrationarea = new JTextArea(regDate);
            registrationarea.setBounds(27,255, 150,27);
            registrationarea.setForeground(Color.WHITE);
            registrationarea.setFont(new Font("Abyssinica SIL", Font.BOLD, 20));
            registrationarea.setBackground(new Color(0, 153, 153));
            registrationarea.setEditable(false);
            areapanel.add(registrationarea);

            JLabel expirydatelabel = new JLabel("Expiration Date");
            expirydatelabel.setBounds(20, 280, 200, 40);
            expirydatelabel.setFont(new Font("Abyssinica SIL", Font.BOLD, 18));
            areapanel.add(expirydatelabel);

            expiryarea = new JTextArea(expDate);
            expiryarea.setBounds(27, 315, 310,27);
            expiryarea.setFont(new Font("Abyssinica SIL", Font.BOLD, 20));
            expiryarea.setForeground(Color.WHITE);
            expiryarea.setBackground(new Color(0, 153, 153));
            expiryarea.setEditable(false);
            areapanel.add(expiryarea);

           /*backbutton = new RoundedButton(40, "Add new Vehicle");
            backbutton.setBounds(120, 590, 150, 40);
            backbutton.setFont(new Font("Abyssinica SIL", Font.BOLD, 14));
            backbutton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.getContentPane().removeAll();
                    frame.repaint();
                    detcon.regAnotherVehicle();
                }
            });
            detailspanel.add(backbutton);*/

            JPanel labelPanel = new JPanel();
            labelPanel.setBounds(0, 0, 386, 50);
            labelPanel.setLayout(null);
            labelPanel.setBackground(new Color(40, 145, 242));
            detailspanel.add(labelPanel);

            ImageIcon originalIcon = new ImageIcon("database/citelogo.png");

            // Resize the image
            Image originalImage = originalIcon.getImage();
            Image resizedImage = originalImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Set the desired width and height

            // Create a new ImageIcon with the resized image
            ImageIcon resizedIcon = new ImageIcon(resizedImage);
            // Create a JLabel to display the image
            JLabel imageLabel = new JLabel(resizedIcon);
            imageLabel.setBounds(10, 0, 50, 50); // Set the bounds as per your requirement
            labelPanel.add(imageLabel);

            userLabel = new JLabel(username);
            userLabel.setBounds(75, 15, 200, 20);
            userLabel.setFont(new Font("Abyssinica SIL", Font.BOLD, 18));
            userLabel.setForeground(Color.BLACK);
            labelPanel.add(userLabel);

            backLabel = new JLabel("log out");
            backLabel.setBounds(300, 15, 60, 20);
            backLabel.setFont(new Font("Abyssinica SIL", Font.PLAIN, 12));
            backLabel.setForeground(Color.BLACK); // Blue foreground color
            backLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        backLabel.setForeground(Color.WHITE);
                        backLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    }
        
                    @Override
                    public void mouseExited(MouseEvent e) {
                        backLabel.setForeground(Color.BLACK);
                        backLabel.setCursor(Cursor.getDefaultCursor());
                    }
        
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        backLabel.setForeground(Color.RED);

                        int choice = JOptionPane.showConfirmDialog(frame, "Proceed to Log out?", "Logout Confirmation", JOptionPane.YES_NO_OPTION);
                        if (choice == JOptionPane.YES_OPTION) {
                            frame.getContentPane().removeAll();
                            frame.repaint();
                            detcon.showLoginView();
                        } else {
                            backLabel.setForeground(Color.BLACK);
                        }
                    }
                });
                
            labelPanel.add(backLabel);

            JLabel pipeLabel = new JLabel(" | ");
            pipeLabel.setBounds(335, 18, 20, 14);
            pipeLabel.setFont(new Font("Abyssinica SIL", Font.PLAIN, 18));
            labelPanel.add(pipeLabel);

            quitLabel = new JLabel("Quit");
            quitLabel.setBounds(350, 19, 60, 12);
            quitLabel.setFont(new Font("Abyssinica SIL", Font.PLAIN, 12));
            quitLabel.setForeground(Color.BLACK); // Blue foreground color
            quitLabel.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    quitLabel.setForeground(Color.WHITE);
                    quitLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }
    
                @Override
                public void mouseExited(MouseEvent e) {
                    quitLabel.setForeground(Color.BLACK);
                    quitLabel.setCursor(Cursor.getDefaultCursor());
                }
                @Override
                public void mouseClicked(MouseEvent e) {
                    quitLabel.setForeground(Color.RED);
                    int choice = JOptionPane.showConfirmDialog(frame, "Close Application?", "Close Application", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        frame.dispose();
                    } else {
                        backLabel.setForeground(Color.BLACK);
                    }
                    
                }
            });
            labelPanel.add(quitLabel);

            
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkExpiration(String expDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date expirationDate = dateFormat.parse(expDate);
            Date currentDate = new Date();
    
            // Calculate the date two months in the future
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);
            calendar.add(Calendar.MONTH, 2);
            Date twoMonthsLater = calendar.getTime();
    
            if (expirationDate.before(twoMonthsLater) && expirationDate.after(currentDate)) {
                JOptionPane.showMessageDialog(frame, "Your sticker is nearing expiration");
            } else if (currentDate.after(expirationDate)) {
                JOptionPane.showMessageDialog(frame, "Your sticker has expired");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }    
    
    
	
}
