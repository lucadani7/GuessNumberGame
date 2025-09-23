package com.lucadani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.concurrent.*;

public class GuessNumber {
    private static final BlockingQueue<String> INPUT = new LinkedBlockingQueue<>();
    private static boolean numberGuessed = false;
    private static int wins = 0;
    private static int losses = 0;
    private static int round = 1;

    private static String readLineWithin(long timeout) {
        try {
            return INPUT.poll(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            return null;
        }
    }

    private static Difficulty parseDifficulty(String s) {
        String k = s.trim().toLowerCase().replace(" ", "");
        return switch (k) {
            case "amator" -> Difficulty.AMATOR;
            case "easy" -> Difficulty.EASY;
            case "medium" -> Difficulty.MEDIUM;
            case "hard" -> Difficulty.HARD;
            case "legendary" -> Difficulty.LEGENDARY;
            default -> {
                System.err.println("Unknown difficulty: " + k + ". Choosing MEDIUM difficulty!");
                yield Difficulty.MEDIUM;
            }
        };
    }

    public static void main(String[] args) {
        // thread who reads permanently from console
        Thread t = new Thread(() -> {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
                String line;
                while ((line = br.readLine()) != null) {
                    INPUT.put(line);
                }
            } catch (Exception ignored) { }
        }, "Console-Input-Reader");
        t.setDaemon(true);
        t.start();

        System.out.println("Choose difficulty: amator / easy / medium / hard / legendary");
        Difficulty difficulty;
        try {
            difficulty = parseDifficulty(INPUT.take());
        } catch (InterruptedException e) {
            return;
        }

        System.out.println("\nYou chose " + difficulty.name() + ":");
        System.out.println("- Interval: " + difficulty.getMini() + " – " + difficulty.getMaxi());
        System.out.println("- Attempts: " + difficulty.getAttempts());
        System.out.println("- Time: " + difficulty.getTimeLimit() + " seconds");
        System.out.println("\n" + difficulty.getStartMessage());

        Random rnd = new Random();

        while (true) {
            System.out.println("\n--- Round " + round + " | Difficulty: " + difficulty.name() + " ---");
            playGame(rnd, difficulty);
            System.out.print("Do you want to play again? (YES / NO) ");
            String answer;
            try {
                answer = INPUT.take();
            } catch (InterruptedException e) {
                return;
            }
            if (answer.equalsIgnoreCase("NO")) {
                System.out.println();
                System.out.println("Session statistics for " + difficulty.name() + ":");
                System.out.println("Rounds won: " + wins);
                System.out.println("Rounds lost: " + losses);
                System.out.println("Thank you for playing! Goodbye!");
                break;
            }
            ++round;
            numberGuessed = false;
        }
    }

    private static void playGame(Random rnd, Difficulty difficulty) {
        int secretNumber = rnd.nextInt(difficulty.getMaxi() - difficulty.getMini() + 1) + difficulty.getMini();
        System.out.println("I chose a number between " + difficulty.getMini() + " and " + difficulty.getMaxi() + ".");
        System.out.println("For guessing the number chosen by me, you have " + difficulty.getAttempts() + " attempts and " + difficulty.getTimeLimit() + " seconds to do so.");
        long deadline = System.currentTimeMillis() + difficulty.getTimeLimit() * 1000L;
        for (int i = 1; i <= difficulty.getAttempts(); ++i) {
            long remaining = deadline - System.currentTimeMillis();
            if (remaining <= 0) {
                System.out.println("\nTime is up!");
                System.out.println(difficulty.getLoseMessage());
                ++losses;
                return;
            }

            System.out.print("Attempt " + i + ": ");
            String line = readLineWithin(remaining);

            if (line == null) {
                System.out.println("\nTime is up!");
                System.out.println(difficulty.getLoseMessage());
                ++losses;
                return;
            }

            int guessNumber;
            try {
                guessNumber = Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("(Not a valid number)");
                --i;
                continue;
            }

            if (guessNumber < secretNumber) {
                System.out.println("Your number is too low!");
            } else if (guessNumber > secretNumber) {
                System.out.println("Your number is too high!");
            } else {
                System.out.println("Congrats! You guessed the number " + guessNumber + ".");
                System.out.println(difficulty.getWinMessage());
                ++wins;
                numberGuessed = true;
                return;
            }
        }
        if (!numberGuessed) {
            long remainingMs = TimeUnit.NANOSECONDS.toMillis(deadline - System.nanoTime());
            System.out.println(remainingMs <= 0 ? "\nTime is up!" : "\nYou lost! The secret number was " + secretNumber + ".");
            System.out.println(difficulty.getLoseMessage());
            ++losses;
        }
    }
}