package com.mycompany.semaforogui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SemaforoGUI extends JFrame {

    private JPanel panelSemaforo;
    private JPanel panelCarro;
    private JLabel labelCarro;
    private JButton btnIniciar;
    
    private Color colorRojo = Color.RED;
    private Color colorAmarillo = Color.YELLOW;
    private Color colorVerde = Color.GREEN;

    private Timer timer;
    private String estadoActual = "ROJO";

    public SemaforoGUI() {
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Simulación de Semáforo");
        setSize(400, 400);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void inicializarComponentes() {
        panelSemaforo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dibujarSemaforo(g);
            }
        };

        panelSemaforo.setPreferredSize(new Dimension(100, 300));
        add(panelSemaforo, BorderLayout.CENTER);

        panelCarro = new JPanel();
        panelCarro.setPreferredSize(new Dimension(400, 50));
        labelCarro = new JLabel("🚗");
        labelCarro.setFont(new Font("Serif", Font.PLAIN, 30));
        panelCarro.add(labelCarro);
        add(panelCarro, BorderLayout.SOUTH);

        btnIniciar = new JButton("Iniciar Simulación");
        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSimulacion();
            }
        });
        add(btnIniciar, BorderLayout.NORTH);
    }

    private void dibujarSemaforo(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(50, 50, 100, 300);

        g.setColor(estadoActual.equals("ROJO") ? colorRojo : Color.DARK_GRAY);
        g.fillOval(75, 75, 50, 50);

        g.setColor(estadoActual.equals("AMARILLO") ? colorAmarillo : Color.DARK_GRAY);
        g.fillOval(75, 150, 50, 50);

        g.setColor(estadoActual.equals("VERDE") ? colorVerde : Color.DARK_GRAY);
        g.fillOval(75, 225, 50, 50);
    }

    private void iniciarSimulacion() {
        timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarEstado();
                moverCarro();
                panelSemaforo.repaint();
            }
        });
        timer.start();
    }

    private void cambiarEstado() {
        switch (estadoActual) {
            case "ROJO":
                estadoActual = "VERDE";
                break;
            case "VERDE":
                estadoActual = "AMARILLO";
                break;
            case "AMARILLO":
                estadoActual = "ROJO";
                break;
        }
    }

    private void moverCarro() {
        if (estadoActual.equals("VERDE")) {
            labelCarro.setText("← 🚗");
        } else {
            labelCarro.setText("🚗");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SemaforoGUI().setVisible(true);
            }
        });
    }
}

