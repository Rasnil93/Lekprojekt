package Appen;

import javax.swing.*;

public class App {

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Rasmus på äventyr");

        frame frame = new frame();

        window.add(frame);


        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);



    }

}
