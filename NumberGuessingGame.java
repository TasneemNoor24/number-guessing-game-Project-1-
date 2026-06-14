import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class NumberGuessingGame extends JFrame {

    private int secretNumber;
    private int attemptsLeft;
    private int score = 0;
    private int highScore = 0;

    private JLabel titleLabel;
    private JLabel infoLabel;
    private JLabel attemptsLabel;
    private JLabel scoreLabel;
    private JComboBox<String> difficultyBox;
    private JTextField inputField;
    private JButton guessButton;
    private JButton restartButton;

    private Random random = new Random();

    public NumberGuessingGame() {

        setTitle("Number Guessing Game");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(20, 20, 20));

        // Title
        titleLabel = new JLabel("NUMBER GUESSING GAME", SwingConstants.CENTER);
        titleLabel.setBounds(50, 10, 400, 30);
        titleLabel.setForeground(Color.CYAN);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel);

        // Difficulty
        difficultyBox = new JComboBox<>(new String[]{"Easy (1-50)", "Medium (1-100)", "Hard (1-200)"});
        difficultyBox.setBounds(160, 50, 180, 25);
        add(difficultyBox);

        // Info
        infoLabel = new JLabel("Select difficulty and start guessing!", SwingConstants.CENTER);
        infoLabel.setBounds(50, 80, 400, 25);
        infoLabel.setForeground(Color.WHITE);
        add(infoLabel);

        // Input
        inputField = new JTextField();
        inputField.setBounds(180, 120, 120, 30);
        add(inputField);

        // Guess Button
        guessButton = new JButton("GUESS");
        guessButton.setBounds(120, 170, 100, 30);
        guessButton.setBackground(Color.GREEN);
        add(guessButton);

        // Restart Button
        restartButton = new JButton("RESTART");
        restartButton.setBounds(260, 170, 100, 30);
        restartButton.setBackground(Color.ORANGE);
        add(restartButton);

        // Attempts
        attemptsLabel = new JLabel("Attempts: -");
        attemptsLabel.setBounds(120, 220, 150, 25);
        attemptsLabel.setForeground(Color.YELLOW);
        add(attemptsLabel);

        // Score
        scoreLabel = new JLabel("Score: 0 | High Score: 0");
        scoreLabel.setBounds(120, 250, 250, 25);
        scoreLabel.setForeground(Color.PINK);
        add(scoreLabel);

        guessButton.addActionListener(e -> checkGuess());
        restartButton.addActionListener(e -> startGame());

        startGame();
        setVisible(true);
    }

    private void startGame() {

        int choice = difficultyBox.getSelectedIndex();

        if (choice == 0) {
            secretNumber = random.nextInt(50) + 1;
            attemptsLeft = 8;
        } else if (choice == 1) {
            secretNumber = random.nextInt(100) + 1;
            attemptsLeft = 7;
        } else {
            secretNumber = random.nextInt(200) + 1;
            attemptsLeft = 6;
        }

        inputField.setText("");
        guessButton.setEnabled(true);

        infoLabel.setText("Game Started! Make your guess...");
        attemptsLabel.setText("Attempts: " + attemptsLeft);
    }

    private void checkGuess() {

        if (attemptsLeft <= 0) return;

        try {
            int guess = Integer.parseInt(inputField.getText());

            if (guess == secretNumber) {

                score += attemptsLeft * 10;
                infoLabel.setText("🎉 Correct! You Win!");

                JOptionPane.showMessageDialog(this,
                        "Correct Number: " + secretNumber +
                        "\nScore Earned: " + (attemptsLeft * 10));

                updateScore();
                guessButton.setEnabled(false);
                return;
            }

            if (guess < secretNumber) {
                infoLabel.setText("📉 Too Low!");
            } else {
                infoLabel.setText("📈 Too High!");
            }

            attemptsLeft--;
            attemptsLabel.setText("Attempts: " + attemptsLeft);

            if (attemptsLeft <= 0) {
                infoLabel.setText("💀 Game Over! Number was " + secretNumber);
                guessButton.setEnabled(false);
            }

        } catch (Exception e) {
            infoLabel.setText("⚠ Enter a valid number!");
        }

        inputField.setText("");
    }

    private void updateScore() {

        if (score > highScore) {
            highScore = score;
        }

        scoreLabel.setText("Score: " + score + " | High Score: " + highScore);
    }

    public static void main(String[] args) {
        new NumberGuessingGame();
    }
}