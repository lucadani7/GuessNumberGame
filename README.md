# GuessNumberGame
A simple **Java console game** where the player must guess a randomly chosen number within a limited number of attempts and under a strict time constraint.  
The player chooses a **difficulty level** at the start, and plays multiple rounds until he / she decides to quit.

---

## 🎮 Game Rules

1. At the start, you choose a difficulty:
   - **Amator**: Large range, many attempts, generous time limit.
   - **Easy**: Easy range, plenty of time.
   - **Medium**: Balanced challenge.
   - **Hard**: Smaller time limit, fewer attempts.
   - **Legendary**: Very strict time and attempts – only for the brave!

2. For each round:
   - The program picks a secret number within the interval of the chosen difficulty.
   - You have a limited number of **attempts** and a **time limit** (in seconds).
   - After each guess:
     - If your guess is too low or too high, you get a hint.
     - If you guess correctly, you win the round.
   - If time runs out or you use all attempts, you lose the round.

3. After each round, you can choose to:
   - Play again with the same difficulty.
   - Quit the game (a summary of your wins/losses is shown).

---

## ⚙️ Features

- Five difficulty levels (Amator → Legendary).
- Time-limited input (game ends if you don’t guess in time).
- Tracks wins and losses across rounds.
- Works entirely in the console.

---

## 🚀 How to Run

1. Clone this repository into your favourite IDE:
   ```bash
   git clone https://github.com/lucadani7/GuessNumberGame
   ```
2. Run the project
3. Play the game. Enjoy!
