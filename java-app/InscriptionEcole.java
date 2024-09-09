import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class InscriptionEcole {
    private JFrame frame;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField classField;
    private JButton submitButton;
    private JTextArea outputArea;

    public InscriptionEcole() {
        frame = new JFrame("Inscription à Khalid School");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panneau d'inscription
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nameLabel = new JLabel("Nom:");
        nameField = new JTextField();
        JLabel emailLabel = new JLabel("E-mail:");
        emailField = new JTextField();
        JLabel phoneLabel = new JLabel("Téléphone:");
        phoneField = new JTextField();
        JLabel classLabel = new JLabel("Classe:");
        classField = new JTextField();
        submitButton = new JButton("Soumettre");

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(phoneLabel);
        formPanel.add(phoneField);
        formPanel.add(classLabel);
        formPanel.add(classField);
        formPanel.add(new JLabel());  // Placeholder
        formPanel.add(submitButton);

        frame.add(formPanel, BorderLayout.CENTER);

        // Panneau de sortie
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Informations d'inscription"));

        frame.add(scrollPane, BorderLayout.SOUTH);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitForm();
            }
        });

        frame.setVisible(true);
    }

    private void submitForm() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        String className = classField.getText().trim();

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || className.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Tous les champs doivent être remplis.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            // Afficher les informations dans le TextArea
            String info = String.format("Nom: %s\nE-mail: %s\nTéléphone: %s\nClasse: %s", name, email, phone, className);
            outputArea.setText(info);

            // Insérer les données dans la base de données
            DatabaseConnector.insertData(name, email, phone, className);

            clearForm();
        }
    }

    private void clearForm() {
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        classField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InscriptionEcole();
            }
        });
    }
}
