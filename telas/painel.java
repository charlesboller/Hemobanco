package telas;

import javax.swing.*;
import java.awt.*;

public class painel {
    private JFrame frame;

    private painel create() {
        frame = createFrame();
        frame.getContentPane().add(createContent());

        return this;
    }

    private JFrame createFrame() {
        JFrame frame = new JFrame(getClass().getName());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        return frame;
    }

    private void show() {
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private Component createContent() {
        final Image image = requestImage();

        JPanel panel = new JPanel() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, null);
            }
        };

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for (String label : new String[]{"One", "Dois", "Drei", "Quatro", "Peace"}) {
            JButton button = new JButton(label);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(Box.createRigidArea(new Dimension(15, 15)));
            panel.add(button);
        }

        panel.setPreferredSize(new Dimension(500, 500));

        return panel;
    }

    private Image requestImage() {
        Image image = null;

        image = Toolkit.getDefaultToolkit().getImage("image/hemolife_icon.png");               
			//	read(new URL("image/hemolife_icon.png"));

        return image;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new painel().create().show();
            }
        });
    }
}